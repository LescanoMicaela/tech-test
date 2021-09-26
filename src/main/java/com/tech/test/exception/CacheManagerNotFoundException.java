package com.tech.test.exception;

/**
 * Class CacheManagerNotFoundException
 * extends RuntimeException
 */
public class CacheManagerNotFoundException extends RuntimeException {

    /**
     * All args contructor
     * @param msg execption message
     */
    public CacheManagerNotFoundException(String msg){super(msg);}
}
