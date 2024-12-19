package ua.mibal.serializer;

import ua.mibal.demo.model.Book;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class PlainBookJsonSerializer {

    public String serialize(Book book) {
        return "{" +
                "\"title\":\"" + book.getTitle() + "\"," +
                "\"author\":\"" + book.getAuthor() + "\"," +
                "\"year\":" + book.getYear() +
                "}";
    }
}
