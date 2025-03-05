package com.org.security.exception;

import static com.org.security.utils.ActorConstant.HTTP_CODE_500;
import static com.org.security.utils.ActorConstant.HYPHEN;

public class ActorException extends RuntimeException {
    private static final long serialVersionUID = -4711404730678356597L;

    private String code;
    private String message;

    public ActorException(Exception exception) {
        super(exception);
        StringBuilder builder = new StringBuilder(ErrorCode.HTTP_CODE_500.getErrorCode()).append(HYPHEN)
                .append(ErrorSeverity.FATAL).append(HYPHEN).append(ErrorCode.HTTP_CODE_500.getErrorMessage());
        this.code = HTTP_CODE_500;
        this.message = builder.toString();
    }

    public ActorException(String code, String message) {
        super(message);
        StringBuilder builder = new StringBuilder(code).append(HYPHEN).append(message);
        this.code = code;
        this.message = builder.toString();
    }

    public ActorException(String code, ErrorSeverity severity, String message) {
        super(message);
        StringBuilder builder = new StringBuilder(code).append(HYPHEN).append(severity).append(HYPHEN).append(message);
        this.code = code;
        this.message = builder.toString();
    }

    public ActorException(String code, ErrorSeverity severity, String message, Exception exception) {
        super(exception);
        StringBuilder builder = new StringBuilder(code).append(HYPHEN).append(severity).append(HYPHEN).append(message);
        this.code = code;
        this.message = builder.toString();
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
