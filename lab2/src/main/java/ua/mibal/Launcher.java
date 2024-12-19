package ua.mibal;

import ua.mibal.demo.Book;
import ua.mibal.serializer.XmlSerializer;
import ua.mibal.serializer.model.XmlModel;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class Launcher {
    private static final XmlSerializer serializer = new XmlSerializer();

    public static void main(String[] args) {
        Book cleanCode = Book.builder()
                .title("Clean Code")
                .author("Robert C. Martin")
                .year(2008)
                .build();

        XmlModel cleanCodeXml = serializer.serialize(cleanCode); 
        System.out.println(cleanCodeXml);
    }
}
