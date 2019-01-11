package com.humblecarrotstudios.android.drwords;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.humblecarrotstudios.android.drwords.data.WordDetails;
import com.humblecarrotstudios.android.drwords.data.WordDetailsStates;
import com.humblecarrotstudios.android.drwords.data.WordResult;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager
        .LoaderCallbacks<String> {

    final static String BASE_URL = "https://wordsapiv1.p.mashape.com/words";
    public String userInput;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();

        final EditText wordEditText = (EditText) findViewById(R.id.word_field);
        final Button resultsButton = (Button) findViewById(R.id.word_button);
        resultsButton.setBackgroundColor(Color.parseColor("#64DD17"));

        resultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userInput = wordEditText.getText().toString().trim();

                if (!isConnected()) {
                    Toast.makeText(MainActivity.this, "No internet connection. Please connect " +
                            "to the internet and try again.", Toast.LENGTH_SHORT).show();
                } else if (userInput.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a word" +
                            ".", Toast.LENGTH_SHORT).show();
                } else {
                    getLoaderManager().initLoader(0, null, MainActivity.this);
                }
            }
        });
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new WordResultLoader(this, BASE_URL + "/" + userInput);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String jsonResponse) {

        Gson gson = new GsonBuilder().create();
        WordDetails wordDetails = gson.fromJson(jsonResponse, WordDetails.class);

        List<WordResult> resultsList = wordDetails.getResultsList();

        if (resultsList != null && !resultsList.isEmpty()) {
            getLoaderManager().destroyLoader(0);
            WordDetailsStates states = new WordDetailsStates();
            states.push(wordDetails);

            intent = new Intent(MainActivity.this, ResultsActivity.class);
            intent.putExtra("detailsStates", states);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "No results for that word, please try again" +
                    ".", Toast.LENGTH_SHORT).show();
            getLoaderManager().destroyLoader(0);

        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        assert true; // do nothing
    }

    private boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean connectedYes = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return connectedYes;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Uri webpage;
        Intent intent;

        switch (item.getItemId()) {

            case R.id.feedback_item:

                webpage = Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSdWcepGjMmfJfwHhdwY0lZBB3Hftpoo9I9E1hHFkpy5qwm8ng/viewform?usp=sf_link");
                intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

                return true;
            case R.id.privacy_policy_item:

                webpage = Uri.parse("https://sites.google.com/view/drwords/privacy-policy");
                intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
