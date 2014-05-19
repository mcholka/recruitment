package com.recruitment.common;

import com.recruitment.crud.ArchetypeFinder;
import com.recruitment.crud.KnowledgeBaseTypeFinder;
import com.recruitment.crud.KnowledgeFinder;
import com.recruitment.entity.Affix;
import com.recruitment.entity.Archetype;
import com.recruitment.entity.Knowledge;
import com.recruitment.entity.KnowledgeBaseType;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mcholka on 2014-05-18. Enjoy!
 */
public class KnowledgeWrapperFactory {
    private static final Logger logger = Logger.getLogger(KnowledgeWrapperFactory.class);

    @Inject
    ArchetypeFinder archetypeFinder;
    @Inject
    KnowledgeBaseTypeFinder knowledgeBaseTypeFinder;
    @Inject
    KnowledgeFinder knowledgeFinder;

    private static final Pattern BASE_TYPE_PATTERN = Pattern.compile("\\[(.+)\\]");
    private static final Pattern ARCHETYPE_PATTERN = Pattern.compile("\\{(.+)\\}");
    private static final Pattern PREFIX_PATTERN = Pattern.compile("<PREFIX (.+?)>");
    private static final Pattern SUFFIX_PATTERN = Pattern.compile("<SUFFIX (.+)>");
    private static final Integer MATCHER = 1;

    public KnowledgeWrapper mappingToWrapper(String value) {
        logger.info("Mapping value: " + value + " to KnowledgeWrapper");
        KnowledgeWrapper knowledgeWrapper = new KnowledgeWrapper();

        Archetype archetype = findArchetypeByID(matchArchetypeID(value));
        knowledgeWrapper.setArchetype(archetype);

        KnowledgeBaseType baseType = findBaseTypeByID(matchBaseTypeID(value));
        knowledgeWrapper.setKnowledgeBaseType(baseType);

        Knowledge knowledge = findKnowledge(archetype, value);
        knowledgeWrapper.setValue(knowledge);

        if(knowledge == null){
            throw new IllegalStateException("Knowledge can't be null!");
        }

        Affix prefix = findPrefix(value, knowledge);
        knowledgeWrapper.setPrefix(prefix);

        Affix suffix = findSuffix(value, knowledge);
        knowledgeWrapper.setSuffix(suffix);

        return knowledgeWrapper;
    }

    private String matchArchetypeID(String value) {
        Matcher matcher = ARCHETYPE_PATTERN.matcher(value);
        if(!matcher.find()){
            throw new RuntimeException("Critical error! Not found archetype in value: " + value);
        }
        return matcher.group(MATCHER);
    }

    private Archetype findArchetypeByID(String archetypeID) {
        return archetypeFinder.findById(archetypeID);
    }

    private String matchBaseTypeID(String value) {
        Matcher matcher = BASE_TYPE_PATTERN.matcher(value);
        if(!matcher.find()){
            throw new RuntimeException("Critical error! Not found base type in value: " + value);
        }
        return matcher.group(MATCHER);
    }

    private KnowledgeBaseType findBaseTypeByID(String baseTypeID) {
        return knowledgeBaseTypeFinder.findByID(baseTypeID);
    }

    private Knowledge findKnowledge(Archetype archetype, String value) {
        boolean prefix = value.contains("PREFIX");
        boolean suffix = value.contains("SUFFIX");
        String id;

        if(prefix && suffix){
            Pattern pattern = Pattern.compile("<PREFIX .+?>(.+)<SUFFIX .+?>");
            Matcher matcher = pattern.matcher(value);
            if(!matcher.find()){
                throw new IllegalStateException("Value not found in string: " + value);
            }
            id = matcher.group(MATCHER);
            return knowledgeFinder.findByArchetypeBaseTypeAndValue(archetype.getKnowledgeBaseType(), archetype, id);
        }
        if(prefix){
            Pattern pattern = Pattern.compile("<PREFIX .+?>(.+?)(;|$)");
            Matcher matcher = pattern.matcher(value);
            if(!matcher.find()){
                throw new IllegalStateException("Value not found in string: " + value);
            }
            id = matcher.group(MATCHER);
            return knowledgeFinder.findByArchetypeBaseTypeAndValue(archetype.getKnowledgeBaseType(), archetype, id);
        }
        if(suffix){
            Pattern pattern = Pattern.compile("\\}(.+)<SUFFIX .+?>");
            Matcher matcher = pattern.matcher(value);
            if(!matcher.find()){
                throw new IllegalStateException("Value not found in string: " + value);
            }
            id = matcher.group(MATCHER);
            return knowledgeFinder.findByArchetypeBaseTypeAndValue(archetype.getKnowledgeBaseType(), archetype, id);
        }
        Pattern pattern = Pattern.compile("\\}(.+?)($|;)");
        Matcher matcher = pattern.matcher(value);
        if(!matcher.find()){
            throw new IllegalStateException("Value not found in string: " + value);
        }
        id = matcher.group(MATCHER);
        return knowledgeFinder.findByArchetypeBaseTypeAndValue(archetype.getKnowledgeBaseType(), archetype, id);
    }

    private Affix findPrefix(String value, Knowledge knowledge) {
        Matcher matcher = PREFIX_PATTERN.matcher(value);
        String id;
        if(matcher.find()){
            logger.info("Found prefix in value: " + value);
            id = matcher.group(MATCHER);
        } else {
            return null;
        }
        for(Affix affix : knowledge.getAffixes()){
            if(affix.getValue().equals(id) && AffixType.PREFIX.equals(affix.getType())){
                return affix;
            }
        }
        return null;
    }

    private Affix findSuffix(String value, Knowledge knowledge) {
        Matcher matcher = SUFFIX_PATTERN.matcher(value);
        String id;
        if(matcher.find()){
            logger.info("Found suffix in value: " + value);
            id = matcher.group(MATCHER);
        } else {
            return null;
        }
        for(Affix affix : knowledge.getAffixes()){
            if(affix.getValue().equals(id) && AffixType.SUFFIX.equals(affix.getType())){
                return affix;
            }
        }
        return null;
    }
}
