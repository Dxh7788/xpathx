package org.diro.mybatis.builder.xml;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.InputStream;
import java.util.Locale;

/**
 * XML配置文件解析器
 *
 * @author xh.d
 * @since 2018/7/24 9:57
 */
public class XMLMapperEntityResovler implements EntityResolver {
    //不保留ibatis配置
    private static final String MYBATIS_CONFIG_SYSTEM = "mybatis-3-config.dtd";
    private static final String MYBATIS_MAPPER_SYSTEM = "mybatis-3-mapper.dtd";

    private static final String MYBATIS_CONFIG_DTD = "org/diro/mybatis/builder/xml/mybatis-3-config.dtd";
    private static final String MYBATIS_MAPPER_DTD = "org/diro/mybatis/builder/xml/mybatis-3-mapper.dtd";

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
        try {
            if (systemId != null) {
                String lowerCaseSystemId = systemId.toLowerCase(Locale.ENGLISH);
                if (lowerCaseSystemId.contains(MYBATIS_CONFIG_SYSTEM)) {
                    return getResource(MYBATIS_CONFIG_DTD, systemId, publicId);
                } else if (lowerCaseSystemId.contains(MYBATIS_MAPPER_SYSTEM)) {
                    return getResource(MYBATIS_MAPPER_DTD, systemId, publicId);
                }
            }
        } catch (Exception e) {
            throw new SAXException(e.toString());
        }
        return null;
    }

    private InputSource getResource(String resource, String systemId, String publicId) {
        InputSource source = null;
        if (resource != null) {
            ClassLoader loader = getClass().getClassLoader();
            InputStream inputStream = loader.getResourceAsStream(resource);
            source = new InputSource(inputStream);
            source.setPublicId(publicId);
            source.setSystemId(systemId);
        }
        return source;
    }
}
