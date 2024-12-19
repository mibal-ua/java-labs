package ua.mibal.serializer;

import ua.mibal.demo.model.Book;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class PlainBookSerializer {

    public String json(Book book) {
        return "{" +
                "\"title\":\"" + book.getTitle() + "\"," +
                "\"author\":\"" + book.getAuthor() + "\"," +
                "\"year\":" + book.getYear() +
                "}";
    }

    public String xml(Book book) {
        return "<book>" +
               "<title>" + book.getTitle() + "</title>" +
               "<author>" + book.getAuthor() + "</author>" +
               "<year>" + book.getYear() + "</year>" +
               "</book>";
    }
}
