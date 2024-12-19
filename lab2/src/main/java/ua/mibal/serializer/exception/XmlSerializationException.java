package ua.mibal.serializer.exception;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class XmlSerializationException extends RuntimeException {

    public XmlSerializationException(String message) {
        super(message);
    }

    public XmlSerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
