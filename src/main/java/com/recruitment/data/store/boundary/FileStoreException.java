package com.recruitment.data.store.boundary;

/**
 * Created by mcholka on 2014-03-23. Enjoy!
 */
public class FileStoreException extends Exception {
    public FileStoreException(String message, Exception e){
        super(message, e);
    }
}
