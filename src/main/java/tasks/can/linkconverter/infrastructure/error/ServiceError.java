package tasks.can.linkconverter.infrastructure.error;

/**
 * Application service specific errors with specific code and message.
 */
public final class ServiceError {

    private ServiceError() {
    }

    public static LinkConverterException GENERIC(String message) {
        return LinkConverterException.of("LC_100", message);
    }

    public static LinkConverterException UNKNOWN_SERVICE_EXCEPTION
            = LinkConverterException.of("LC_101", "Unknown Error");

    public static LinkConverterException INVALID_INPUT
            = LinkConverterException.of("LC_102", "Invalid Input Format");

}
