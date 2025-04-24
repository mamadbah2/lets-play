package sn.dev.letsplay.exceptions;

public class JwtNotMatchException extends RuntimeException {
    public JwtNotMatchException(String message) {
        super(message);
    }
}
