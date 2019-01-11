package com.humblecarrotstudios.android.drwords;

import android.content.AsyncTaskLoader;
import android.content.Context;

public class WordResultLoader extends AsyncTaskLoader<String> {

    String userInput;
    public WordResultLoader(Context context, String userInput) {
        super(context);
        this.userInput = userInput;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public String loadInBackground() {

        String jsonResponse = QueryUtils.getJsonFromUrl(this.userInput);
        return jsonResponse;
    }
}
