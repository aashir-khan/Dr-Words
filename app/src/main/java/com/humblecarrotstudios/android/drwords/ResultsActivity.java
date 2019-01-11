package com.humblecarrotstudios.android.drwords;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.humblecarrotstudios.android.drwords.data.WordDetailsStates;


public class ResultsActivity extends AppCompatActivity {

    WordDetailsStates states;
    String currentWord;
    TextView resultsTitleView;
    ListView resultsListView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_list);

    }

    @Override
    protected void onResume() {
        super.onResume();

        states = getIntent().getParcelableExtra("detailsStates");
        currentWord = states.getLatestWord();

        WordsAdapter mAdapter = new WordsAdapter(ResultsActivity.this, states
                .getLatestWordDetails().getResultsList());

        setTitle("Word Information Results");

        resultsTitleView = (TextView) findViewById(R.id.results_title);
        resultsTitleView.setText("RESULTS FOR \"" + currentWord + "\"");

        resultsListView = (ListView) findViewById(R.id.results_list);


        resultsListView.setAdapter(mAdapter);

        resultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ResultsActivity.this, SingleResultActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("detailsStates", states);
                startActivity(intent);
            }
        });

        // implementing copying to clipboard
        resultsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position,
                                           long id) {

                ClipData myClip;
                ClipboardManager clipboard = (ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);

                TextView textView = (TextView) view.findViewById(R.id.result_view);
                String textToPutOnClipboard = textView.getText().toString();

                myClip = ClipData.newPlainText("text", textToPutOnClipboard);
                clipboard.setPrimaryClip(myClip);

                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                boolean hasBackPressed = data.getBooleanExtra("backOrUpPressed", false);

                if (hasBackPressed) {
                    states = getIntent().getParcelableExtra("detailsStates");
                    currentWord = states.getLatestWord();
                    resultsTitleView = (TextView) findViewById(R.id.results_title);
                    resultsTitleView.setText("RESULTS FOR \"" + currentWord + "\"");
                }
            }
        }

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(ResultsActivity.this, SingleResultActivity.class);
        intent.putExtra("backOrUpPressed", true);
        intent.putExtra("detailsStates", states);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    // override up button to be the same as the back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



