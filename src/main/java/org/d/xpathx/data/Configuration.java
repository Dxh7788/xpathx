package org.d.xpathx.data;

/**
 *
 * @author xh.d
 * @since 2018/7/23 10:14
 */
public class Configuration {
    private String encode;
    private String author;
    private Integer year;
    private Double price;

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "encode='" + encode + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}
