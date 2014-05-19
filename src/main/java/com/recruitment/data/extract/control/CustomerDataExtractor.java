package com.recruitment.data.extract.control;

import com.recruitment.common.KnowledgeHolder;
import com.recruitment.crud.ArchetypeFinder;
import com.recruitment.crud.KnowledgeFinder;
import com.recruitment.entity.Archetype;
import com.recruitment.entity.CustomerData;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by mcholka on 2014-03-26. Enjoy!
 */
public class CustomerDataExtractor {
    private static final Logger logger = Logger.getLogger(CustomerDataExtractor.class);

    @Inject
    DataExtractor dataExtractor;
    @Inject
    KnowledgeFinder knowledgeFinder;
    @Inject
    ArchetypeFinder archetypeFinder;

    public KnowledgeHolder extractData(CustomerData customerData) throws IOException {
        logger.info("Extracting data for customer: " + customerData.getId());
        String document = getDocument(customerData.getStoredCv());
        List<Archetype> archetypes = getAllArchetypes();
        logger.info("Parse document:\n" + document);
        KnowledgeHolder knowledgeHolder = tryToFoundDataInDocument(document, archetypes);
        logger.info("\nFound data:\n" + knowledgeHolder.toString());
        return knowledgeHolder;
    }

    private List<Archetype> getAllArchetypes() {
        return archetypeFinder.findAllArchetypes();
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
        pdDocument.close();
        inputStream.close();
        return pdf;
    }

    private KnowledgeHolder tryToFoundDataInDocument(String document, List<Archetype> archetypes) {
        KnowledgeHolder knowledgeHolder = new KnowledgeHolder();
        List<String> foundValues;
        for(Archetype archetype : archetypes){
            foundValues = dataExtractor.extractData(document, knowledgeFinder.getKnowledgeByArchetype(archetype));
            for(String value : foundValues){
                knowledgeHolder.append(value, archetype);
            }
        }
        return knowledgeHolder;
    }
}