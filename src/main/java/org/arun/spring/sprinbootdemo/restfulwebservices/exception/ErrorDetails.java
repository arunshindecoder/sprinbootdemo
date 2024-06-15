package org.arun.spring.sprinbootdemo.restfulwebservices.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
    private String fieldName="";
    private String fieldMessage="";
    private String validationMessage="";
    private String exceptionMessage="";

    private String requestDescription="";
    private LocalDateTime timestamp;

    public ErrorDetails(String fieldName, String fieldMessage, String validationMessage, String exceptionMessage,String requestDescription) {
        this.fieldName = fieldName;
        this.fieldMessage = fieldMessage;
        this.validationMessage = validationMessage;
        this.exceptionMessage = exceptionMessage;
        this.requestDescription = requestDescription;
        this.timestamp = LocalDateTime.now();
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}