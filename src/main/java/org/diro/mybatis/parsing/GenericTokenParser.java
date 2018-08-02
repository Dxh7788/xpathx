package org.diro.mybatis.parsing;

/**
 * 通用Token解析器
 *
 * @author xh.d
 * @since 2018/8/2 16:58
 */
public class GenericTokenParser {
    private String openToken;
    private String closeToken;
    //token处理器
    private TokenHandler handler;

    public GenericTokenParser(String openToken, String closeToken, TokenHandler handler) {
        this.openToken = openToken;
        this.closeToken = closeToken;
        this.handler = handler;
    }

    public String parse(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        //找到openToken占位符
        int start = text.indexOf(openToken);
        if (start == -1) {//如果找不到则返回text原文
            return text;
        }
        StringBuilder builder = new StringBuilder();
        StringBuilder expression = null;
        int offset = 0;
        while (start > -1) {
            if (expression == null) {
                expression = new StringBuilder();
            } else {
                expression.setLength(0);
            }
            builder.append(text, offset, start - 1);//将openToken前的字符串切割出来
            offset = start + openToken.length();
            int end = text.indexOf(closeToken, offset);
            //end处理一次
            while (end > -1) {
                expression.append(text, offset, end);
                break;
            }
            if (end == -1) {
                builder.append(text, offset, end);
                offset = text.length();
            } else {
                builder.append(handler.handleToken(expression.toString()));
                offset = end + closeToken.length();
            }
            start = text.indexOf(openToken, offset);
        }
        return builder.toString();
    }
}
