package com.humblecarrotstudios.android.drwords.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class WordDetails implements Parcelable {

    private String word;
    private List<WordResult> results;

    public WordDetails() {
        this.results = new ArrayList<>();
    }

    protected WordDetails(Parcel in) {
        word = in.readString();
        results = in.createTypedArrayList(WordResult.CREATOR);
    }

    public static final Creator<WordDetails> CREATOR = new Creator<WordDetails>() {
        @Override
        public WordDetails createFromParcel(Parcel in) {
            return new WordDetails(in);
        }

        @Override
        public WordDetails[] newArray(int size) {
            return new WordDetails[size];
        }
    };

    public List<WordResult> getResultsList() {
        return results;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(word);
        parcel.writeTypedList(results);
    }
}
