package com.humblecarrotstudios.android.drwords.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class WordResult implements Parcelable {

    private String definition;
    private String partOfSpeech;
    private List<String> synonyms;
    private List<String> antonyms;
    private List<String> examples;
    private List<String> derivation;
    private List<String> typeOf;
    private List<String> hasTypes;
    private List<String> partOf;
    private List<String> hasParts;
    private List<String> instanceOf;
    private List<String> hasInstances;
    private List<String> similarTo;
    private List<String> also;
    private List<String> entails;
    private List<String> memberOf;
    private List<String> hasMembers;
    private List<String> substanceOf;
    private List<String> hasSubstances;
    private List<String> inCategory;
    private List<String> hasCategories;
    private List<String> usageOf;
    private List<String> hasUsages;
    private List<String> inRegion;
    private List<String> regionOf;
    private List<String> pertainsTo;

    protected WordResult(Parcel in) {
        definition = in.readString();
        partOfSpeech = in.readString();
        synonyms = in.createStringArrayList();
        antonyms = in.createStringArrayList();
        examples = in.createStringArrayList();
        derivation = in.createStringArrayList();
        typeOf = in.createStringArrayList();
        hasTypes = in.createStringArrayList();
        partOf = in.createStringArrayList();
        hasParts = in.createStringArrayList();
        instanceOf = in.createStringArrayList();
        hasInstances = in.createStringArrayList();
        similarTo = in.createStringArrayList();
        also = in.createStringArrayList();
        entails = in.createStringArrayList();
        memberOf = in.createStringArrayList();
        hasMembers = in.createStringArrayList();
        substanceOf = in.createStringArrayList();
        hasSubstances = in.createStringArrayList();
        inCategory = in.createStringArrayList();
        hasCategories = in.createStringArrayList();
        usageOf = in.createStringArrayList();
        hasUsages = in.createStringArrayList();
        inRegion = in.createStringArrayList();
        regionOf = in.createStringArrayList();
        pertainsTo = in.createStringArrayList();
    }

    public static final Creator<WordResult> CREATOR = new Creator<WordResult>() {
        @Override
        public WordResult createFromParcel(Parcel in) {
            return new WordResult(in);
        }

        @Override
        public WordResult[] newArray(int size) {
            return new WordResult[size];
        }
    };

    public String getDefinition() {
        return definition;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public List<String> getAntonyms() {
        return antonyms;
    }

    public List<String> getExamples() {
        return examples;
    }

    public List<String> getDerivation() {
        return derivation;
    }

    public List<String> getTypeOf() {
        return typeOf;
    }

    public List<String> getHasTypes() {
        return hasTypes;
    }

    public List<String> getPartOf() {
        return partOf;
    }

    public List<String> getHasParts() {
        return hasParts;
    }

    public List<String> getInstanceOf() {
        return instanceOf;
    }

    public List<String> getHasInstances() {
        return hasInstances;
    }

    public List<String> getSimilarTo() {
        return similarTo;
    }

    public List<String> getAlso() {
        return also;
    }

    public List<String> getEntails() {
        return entails;
    }

    public List<String> getMemberOf() {
        return memberOf;
    }

    public List<String> getHasMembers() {
        return hasMembers;
    }

    public List<String> getSubstanceOf() {
        return substanceOf;
    }

    public List<String> getHasSubstances() {
        return hasSubstances;
    }

    public List<String> getInCategory() {
        return inCategory;
    }

    public List<String> getHasCategories() {
        return hasCategories;
    }

    public List<String> getUsageOf() {
        return usageOf;
    }

    public List<String> getHasUsages() {
        return hasUsages;
    }

    public List<String> getInRegion() {
        return inRegion;
    }

    public List<String> getRegionOf() {
        return regionOf;
    }

    public List<String> getPertainsTo() {
        return pertainsTo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(definition);
        parcel.writeString(partOfSpeech);
        parcel.writeStringList(synonyms);
        parcel.writeStringList(antonyms);
        parcel.writeStringList(examples);
        parcel.writeStringList(derivation);
        parcel.writeStringList(typeOf);
        parcel.writeStringList(hasTypes);
        parcel.writeStringList(partOf);
        parcel.writeStringList(hasParts);
        parcel.writeStringList(instanceOf);
        parcel.writeStringList(hasInstances);
        parcel.writeStringList(similarTo);
        parcel.writeStringList(also);
        parcel.writeStringList(entails);
        parcel.writeStringList(memberOf);
        parcel.writeStringList(hasMembers);
        parcel.writeStringList(substanceOf);
        parcel.writeStringList(hasSubstances);
        parcel.writeStringList(inCategory);
        parcel.writeStringList(hasCategories);
        parcel.writeStringList(usageOf);
        parcel.writeStringList(hasUsages);
        parcel.writeStringList(inRegion);
        parcel.writeStringList(regionOf);
        parcel.writeStringList(pertainsTo);
    }
}
