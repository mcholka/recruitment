package com.recruitment.data.extract.control;

import com.recruitment.common.AffixType;
import com.recruitment.common.RecruitmentUtils;
import com.recruitment.entity.Affix;
import com.recruitment.entity.Knowledge;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by mcholka on 2014-03-27. Enjoy!
 */
public class DataExtractor {
    private static final Logger logger = Logger.getLogger(DataExtractor.class);

    private String foundValue;

    public List<String> extractData(String document, List<Knowledge> interestedValues){
        List<String> foundValues = new ArrayList<>();
        for(Knowledge interestingValue : interestedValues){
            boolean found = tryToFindValue(document, interestingValue);
            checkState(found);
            if(found){
                foundValues.add(foundValue);
            }
        }
        logger.info("Found in doc " + foundValues.size() + " interesting values");
        return foundValues;
    }

    private void checkState(boolean found) {
        if(found && RecruitmentUtils.emptyString(foundValue)){
            throw new IllegalStateException("Found value but value is empty!");
        }
        if(!found && !RecruitmentUtils.emptyString(foundValue)){
            throw new IllegalStateException("Not found value but value is not empty!");
        }
    }

    private boolean tryToFindValue(String document, Knowledge knowledge) {
        foundValue = null;
        if(knowledge.getAffixes().isEmpty()){
            return findWithoutAffixes(document, knowledge);
        } else {
            return findWithAffixes(document, knowledge);
        }
    }

    private boolean findWithoutAffixes(String document, Knowledge knowledge) {
        logger.info("Try to find value: " + knowledge.getValue() + " without affixes");
        return findValue(document, knowledge.getValue());
    }

    private boolean findValue(String document, String interestingValue){
        checkWithWhiteSpaces(document, interestingValue);

        if(document.contains(interestingValue)){
            logger.info("Found without any changes! Value: " + interestingValue);
            foundValue = interestingValue;
            return true;
        }
        if(document.toUpperCase().contains(interestingValue.toUpperCase())){
            logger.info("Found with only upperCase! Value: " + interestingValue);
            foundValue = interestingValue;
            return true;
        }
        String formattedDocument = document.toUpperCase().replaceAll("[^A-Z0-9\\s]", "");
        String formattedInterestingValue = interestingValue.toUpperCase().replaceAll("[^A-Z0-9\\s]", "");

        if(formattedDocument.contains(formattedInterestingValue)) {
            logger.info("Found before formatted! Value: " + interestingValue);
            foundValue = interestingValue;
            return true;
        }
        return false;
    }

    private void checkWithWhiteSpaces(String document, String interestingValue) {
        if(Pattern.compile("(^|\\s)" + interestingValue + "($|\\s)", Pattern.CASE_INSENSITIVE).matcher(document).find()){
            logger.info("Document contains value: " + interestingValue + " with white spaces");
        } else {
            logger.info("Document doesn't contain the value: " + interestingValue + " with white spaces");
        }
    }

    private boolean findWithAffixes(String document, Knowledge knowledge) {
        logger.info("Try to find value: " + knowledge.getValue() + " with affixes");
        boolean prefixFound = false;
        boolean suffixFound = false;
        boolean valueFound;

        if(!findWithoutAffixes(document, knowledge)){
            logger.info("Value not found even without prefixes, skip founding with prefixes");
            return false;
        }

        String toFound = knowledge.getValue();
        for(Affix affix : knowledge.getAffixes()) {
            boolean required = affix.isRequired();
            if(alreadyMatched(affix, prefixFound, suffixFound)){
                logger.info("Affix with type " + affix.getType() + " already matched! Skip affix " + affix.getValue());
                continue;
            }
            toFound = append(toFound, affix);

            logger.info("Try to find value: " + toFound);

            valueFound = findValue(document, toFound);
            if(required && !valueFound){
                logger.info("Affix " + affix.getValue() + " required but not found! Not adding");
                foundValue = null;
                return false;
            }
            if(required && AffixType.PREFIX.equals(affix.getType())){
                logger.info("Found prefix");
                prefixFound = true;
                continue;
            }
            if(required && AffixType.SUFFIX.equals(affix.getType())){
                logger.info("Found suffix");
                suffixFound = true;
                continue;
            }
            if(!required && !valueFound){
                logger.info("Affix not found, remove it");
                toFound = remove(toFound, affix);
            }
        }
        foundValue = toFound;
        return true;
    }

    private boolean alreadyMatched(Affix affix, boolean prefixFound, boolean suffixFound) {
        return (AffixType.PREFIX.equals(affix.getType()) && prefixFound) ||
                (AffixType.SUFFIX.equals(affix.getType()) && suffixFound);
    }

    private String append(String value, Affix affix) {
        if(AffixType.PREFIX.equals(affix.getType())){
            return affix.getValue().concat(" ").concat(value);
        } else if (AffixType.SUFFIX.equals(affix.getType())){
            return value.concat(" ").concat(affix.getValue());
        } else {
            throw new RuntimeException("Unrecognized affix " + affix.getValue() + " with type " + affix.getType());
        }
    }

    private String remove(String value, Affix affix) {
        if(AffixType.PREFIX.equals(affix.getType())){
            return value.replaceAll(affix.getValue() + " ", "");
        } else if (AffixType.SUFFIX.equals(affix.getType())){
            return value.replaceAll(" " + affix.getValue(), "");
        } else {
            throw new RuntimeException("Unrecognized affix " + affix.getValue() + " with type " + affix.getType());
        }
    }
}
