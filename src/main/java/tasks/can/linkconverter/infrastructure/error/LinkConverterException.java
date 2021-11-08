package tasks.can.linkconverter.infrastructure.error;

import lombok.Getter;

/**
 * Customized application exception type.
 */
@Getter
public class LinkConverterException extends RuntimeException {

    private final String code;


    private LinkConverterException(String code, String message) {
        super(message);
        this.code = code;
    }


    public static LinkConverterException of(String code, String message) {
        return new LinkConverterException(code, message);
    }


}
