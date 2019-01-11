package com.humblecarrotstudios.android.drwords.data;

import com.humblecarrotstudios.android.drwords.SingleResultActivity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SingleResultExpandableListDataPump {


    public static Map<String, List<String>> getData(WordDetails wordDetails, int resultPosition) {

//        String inputWord = wordDetails.getWord();
        Map<String, List<String>> expandableListDetail = new LinkedHashMap<>();

        WordResult wordResult = wordDetails.getResultsList().get(resultPosition);
        String definition = wordResult.getDefinition();
        String partOfSpeech = wordResult.getPartOfSpeech();
        List<String> synonymsList = wordResult.getSynonyms();
        List<String> antonymsList = wordResult.getAntonyms();
        List<String> examplesList = wordResult.getExamples();
        List<String> derivationList = wordResult.getDerivation();
        List<String> typeOfList = wordResult.getTypeOf();
        List<String> hasTypesList = wordResult.getHasTypes();
        List<String> partOfList = wordResult.getPartOf();
        List<String> hasPartsList = wordResult.getHasParts();
        List<String> instanceOfList = wordResult.getInstanceOf();
        List<String> hasInstancesList = wordResult.getHasInstances();
        List<String> similarToList = wordResult.getSimilarTo();
        List<String> alsoList = wordResult.getAlso();
        List<String> entailsList = wordResult.getEntails();
        List<String> memberOfList = wordResult.getMemberOf();
        List<String> hasMembersList = wordResult.getHasMembers();
        List<String> substanceOfList = wordResult.getSubstanceOf();
        List<String> hasSubstancesList = wordResult.getHasSubstances();
        List<String> inCategoryList = wordResult.getInCategory();
        List<String> hasCategoriesList = wordResult.getHasCategories();
        List<String> usageOfList = wordResult.getUsageOf();
        List<String> hasUsagesList = wordResult.getHasUsages();
        List<String> inRegionList = wordResult.getInRegion();
        List<String> regionOfList = wordResult.getRegionOf();
        List<String> pertainsToList = wordResult.getPertainsTo();



//        if (!inputWord.isEmpty()) {
//            List<String> wordList = new ArrayList<>();
//            wordList.add(inputWord);
//            expandableListDetail.put(SingleResultActivity.WordBeingSearchedText, wordList);
//        }

        if (definition != null) {
            List<String> definitionList = new ArrayList<>();
            definitionList.add(definition);
            expandableListDetail.put(SingleResultActivity.DefinitionText, definitionList);
        }

        if (partOfSpeech != null) {
            List<String> partOfSpeechList = new ArrayList<>();
            partOfSpeechList.add(partOfSpeech);
            expandableListDetail.put(SingleResultActivity.PartOfSpeechText, partOfSpeechList);
        }

        if (synonymsList != null) {
            expandableListDetail.put(SingleResultActivity.SynonymsText, synonymsList);
        }

        if (antonymsList != null) {
            expandableListDetail.put(SingleResultActivity.AntonymsText, antonymsList);
        }

        if (examplesList != null) {
            expandableListDetail.put(SingleResultActivity.ExamplesText, examplesList);
        }

        if (derivationList != null) {
            expandableListDetail.put(SingleResultActivity.DerivativesText, derivationList);
        }

        if (typeOfList != null) {
            expandableListDetail.put(SingleResultActivity.TypeOfText, typeOfList);
        }

        if (hasTypesList != null) {
            expandableListDetail.put(SingleResultActivity.HasTypesText, hasTypesList);
        }

        if (partOfList != null) {
            expandableListDetail.put(SingleResultActivity.PartOfText, partOfList);
        }

        if (hasPartsList != null) {
            expandableListDetail.put(SingleResultActivity.HasPartsText, hasPartsList);
        }

        if (instanceOfList != null) {
            expandableListDetail.put(SingleResultActivity.InstanceOfText, instanceOfList);
        }

        if (hasInstancesList != null) {
            expandableListDetail.put(SingleResultActivity.HasInstancesText, hasInstancesList);
        }

        if (similarToList != null) {
            expandableListDetail.put(SingleResultActivity.SimilarToText, similarToList);
        }

        if (alsoList != null) {
            expandableListDetail.put(SingleResultActivity.AlsoText, alsoList);
        }

        if (entailsList != null) {
            expandableListDetail.put(SingleResultActivity.EntailsText, entailsList);
        }

        if (memberOfList != null) {
            expandableListDetail.put(SingleResultActivity.MemberOfText, memberOfList);
        }

        if (hasMembersList != null) {
            expandableListDetail.put(SingleResultActivity.HasMembersText, hasMembersList);
        }

        if (substanceOfList != null) {
            expandableListDetail.put(SingleResultActivity.SubstanceOfText, substanceOfList);
        }

        if (hasSubstancesList != null) {
            expandableListDetail.put(SingleResultActivity.HasSubstancesText, hasSubstancesList);
        }

        if (inCategoryList != null) {
            expandableListDetail.put(SingleResultActivity.InCategoryText, inCategoryList);
        }

        if (hasCategoriesList != null) {
            expandableListDetail.put(SingleResultActivity.HasCategoriesText, hasCategoriesList);
        }

        if (usageOfList != null) {
            expandableListDetail.put(SingleResultActivity.UsageOfText, usageOfList);
        }

        if (hasUsagesList != null) {
            expandableListDetail.put(SingleResultActivity.HasUsagesText, hasUsagesList);
        }

        if (inRegionList != null) {
            expandableListDetail.put(SingleResultActivity.InRegionText, inRegionList);
        }

        if (regionOfList != null) {
            expandableListDetail.put(SingleResultActivity.RegionOfText, regionOfList);
        }

        if (pertainsToList != null) {
            expandableListDetail.put(SingleResultActivity.PertainsToText, pertainsToList);
        }

        return expandableListDetail;

    }


}
