package ua.mibal.serializer.reflection;

import ua.mibal.demo.model.Book;

/**
 * PlainBookSerializer class provides methods to serialize a Book object into JSON and XML formats.
 * This implementation performs basic serialization by manually constructing JSON and XML strings.
 *
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class PlainBookSerializer {

    /**
     * Converts a Book object to its JSON string representation.
     *
     * @param book the Book object to be serialized
     * @return the JSON string representation of the book
     */
    public String json(Book book) {
        return "{" +
               "\"title\":\"" + book.getTitle() + "\"," +
               "\"author\":\"" + book.getAuthor() + "\"," +
               "\"year\":" + book.getYear() +
               "}";
    }

    /**
     * Converts a Book object to its XML string representation.
     *
     * @param book the Book object to be serialized
     * @return the XML string representation of the book
     */
    public String xml(Book book) {
        return "<book>" +
               "<title>" + book.getTitle() + "</title>" +
               "<author>" + book.getAuthor() + "</author>" +
               "<year>" + book.getYear() + "</year>" +
               "</book>";
    }
}
