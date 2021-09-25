package com.tech.test.exception;

/**
 * Class NoResourceFoundException
 * extends RuntimeException
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * All args contructor
     * @param msg execption message
     */
    public ResourceNotFoundException(String msg){super(msg);}
}
