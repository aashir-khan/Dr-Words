package com.humblecarrotstudios.android.drwords.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class WordDetailsStates implements Parcelable {

    private List<WordDetails> wordDetailsList;

    public WordDetailsStates() {
        this.wordDetailsList = new ArrayList<>();
    }

    public WordDetailsStates(List<WordDetails> wordDetailsList) {
        this.wordDetailsList = wordDetailsList;
    }


    protected WordDetailsStates(Parcel in) {
        wordDetailsList = in.createTypedArrayList(WordDetails.CREATOR);
    }

    public static final Creator<WordDetailsStates> CREATOR = new Creator<WordDetailsStates>() {
        @Override
        public WordDetailsStates createFromParcel(Parcel in) {
            return new WordDetailsStates(in);
        }

        @Override
        public WordDetailsStates[] newArray(int size) {
            return new WordDetailsStates[size];
        }
    };

    public List<WordDetails> getWordDetailsList() {
        return wordDetailsList;
    }

    public void setWordDetailsList(List<WordDetails> wordDetailsList) {
        this.wordDetailsList = wordDetailsList;
    }

    public WordDetails getLatestWordDetails() {
        return this.wordDetailsList.get(this.wordDetailsList.size() - 1);
    }

    public WordDetails pop() {

        List<WordDetails> currentList = this.wordDetailsList;
        int currentSize = currentList.size();
        WordDetails returnValue = null;

        if (currentList.size() > 0) {
            returnValue = currentList.remove(currentSize - 1);
        }

        return returnValue;
    }

    public void push(WordDetails detailsToAdd) {

        this.wordDetailsList.add(detailsToAdd);
    }

    public String getLatestWord() {

        WordDetails latestDetails = getLatestWordDetails();
        return  latestDetails.getWord();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(wordDetailsList);
    }
}
