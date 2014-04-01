package com.recruitment.data.extract.control;

import com.recruitment.common.KnowledgeCommon;
import com.recruitment.entity.CustomerData;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by mcholka on 2014-03-26. Enjoy!
 */
public class CustomerDataExtractor {
    private static final Logger logger = Logger.getLogger(CustomerDataExtractor.class);

    @Inject
    KnowledgeLoader knowledgeLoader;
    @Inject
    DataExtractor dataExtractor;

    public KnowledgeCommon extractData(CustomerData customerData) throws IOException {
        logger.info("Extracting data for customer: " + customerData.getId());
        String document = getDocument(customerData.getStoredCv());
        logger.info("Parse document:\n" + document);
        KnowledgeCommon customerExtractedData = tryToFoundDataInDocument(document);
        logger.info("\nFound: " +
                "\nExperience data: " + customerExtractedData.getExperience().size() +
                "\nEducation data: " + customerExtractedData.getEducation().size() +
                "\nSkills data: " + customerExtractedData.getSkills().size() +
                "\nInterest data: " + customerExtractedData.getInterest().size());
        return customerExtractedData;
    }

    private String getDocument(byte[] storedCv) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(storedCv);
        PDDocument pdDocument = PDDocument.load(inputStream);
        PDFTextStripper textStripper = new PDFTextStripper();
        StringWriter writer = new StringWriter();
        textStripper.writeText(pdDocument, writer);
        String pdf = writer.toString();
        writer.flush();
        writer.close();
        inputStream.close();
        return pdf;
    }

    private KnowledgeCommon tryToFoundDataInDocument(String document) {
        KnowledgeCommon customerKnowledge = new KnowledgeCommon();
        customerKnowledge.setExperience(dataExtractor.extractData(document, knowledgeLoader.getAllExperience()));
        customerKnowledge.setEducation(dataExtractor.extractData(document, knowledgeLoader.getAllEducation()));
        customerKnowledge.setSkills(dataExtractor.extractData(document, knowledgeLoader.getAllSkills()));
        customerKnowledge.setInterest(dataExtractor.extractData(document, knowledgeLoader.getAllInterest()));
        return customerKnowledge;
    }
}
