package com.recruitment.data.store.control;

import com.recruitment.common.RecruitmentUtils;
import com.recruitment.data.store.boundary.FileStoreException;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.io.File;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mcholka on 2014-03-23. Enjoy!
 */
public class FileCreator {
    private static final Logger logger = Logger.getLogger(FileCreator.class);

    @Inject
    FileExistChecker fileExistChecker;

    public String persistFile(String fileName, byte[] cv) throws FileStoreException {
        logger.info("Store datastore: " + fileName + " on disk");
        if(fileExistChecker.check(fileName)){
            return persistExistingFile(fileName, cv);
        }
        persistFileToDisk(fileName, cv);
        logger.info("File stored");
        return fileName;
    }

    private String persistExistingFile(String fileName, byte[] cv) throws FileStoreException {
        String overrideFileName = overrideExistingFileName(fileName);
        logger.info("File with name: " + fileName + " already exists! Store with name: " + overrideFileName);
        persistFileToDisk(overrideFileName, cv);
        logger.info("File stored");
        return overrideFileName;
    }

    private String overrideExistingFileName(String fileName) {
        Pattern pattern = Pattern.compile("(\\.\\S+)$");
        Matcher matcher = pattern.matcher(fileName);
        String type;
        if(matcher.find()){
            type = matcher.group(1);
        } else {
            logger.error("Type not found! Store with default type: " + RecruitmentUtils.DEFAULT_FILE_SUFFIX);
            type = RecruitmentUtils.DEFAULT_FILE_SUFFIX;
        }
        return fileName.replaceAll("(\\..+)$", "") + System.currentTimeMillis() + type;
    }

    private void persistFileToDisk(String fileName, byte[] file) throws FileStoreException {
        FileOutputStream outputStream = null;
        try{
            outputStream = new FileOutputStream(
                    new File(RecruitmentUtils.FILE_STORE_PATH + fileName)
            );
            IOUtils.write(file, outputStream);
        } catch (Exception e){
            throw new FileStoreException("Can't store datastore: " + fileName, e);
        } finally {
            if(outputStream != null) {
                IOUtils.closeQuietly(outputStream);
            }
        }
    }
}
