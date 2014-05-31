package com.recruitment.common;

/**
 * Created by mcholka on 2014-03-23. Enjoy!
 */
public enum ProcessStatus {
    NEW("NOWY"),
    EXTRACTED("WYEKSTRAKTOWANY"),
    FILTERED("PRZEFILTROWANY"),
    RATED("PRZETWORZONY");

    private final String desc;

    private ProcessStatus(String desc){
        this.desc = desc;
    }

    public String getDescription(){
        return desc;
    }
}
