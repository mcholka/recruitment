package com.recruitment.common;

/**
 * Created by mcholka on 2014-05-31. Enjoy!
 */
public enum VerifyStatus {
    NOT_VERIFIED("Nie zweryfikowany"),
    WEAK("Słaby"),
    JUNIOR("Młody"),
    MID("Średni"),
    GOOD("Dobry"),
    SENIOR("Starszy");

    private final String status;

    private VerifyStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
