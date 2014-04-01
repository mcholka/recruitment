package com.recruitment.data.store.control;

import com.recruitment.common.RecruitmentUtils;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by mcholka on 2014-03-23. Enjoy!
 */
public class FileExistChecker {

    public boolean check(String fileName){
        return Files.exists(Paths.get(RecruitmentUtils.FILE_STORE_PATH + fileName));
    }
}
