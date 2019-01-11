package com.humblecarrotstudios.android.drwords;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.humblecarrotstudios.android.drwords.data.SingleResultExpandableListDataPump;
import com.humblecarrotstudios.android.drwords.data.WordDetails;
import com.humblecarrotstudios.android.drwords.data.WordDetailsStates;
import com.humblecarrotstudios.android.drwords.data.WordResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SingleResultActivity extends AppCompatActivity implements LoaderManager
        .LoaderCallbacks<String> {

    final public static String DefinitionText = "Definition";
    final public static String PartOfSpeechText = "Part Of Speech";
    final public static String SynonymsText = "Synonyms";
    final public static String AntonymsText = "Antonyms";
    final public static String ExamplesText = "Examples";
    final public static String DerivativesText = "Derivatives";
    final public static String TypeOfText = "Type Of";
    final public static String HasTypesText = "Has Types";
    final public static String PartOfText = "Part Of";
    final public static String HasPartsText = "Has Parts";
    final public static String InstanceOfText = "Instance Of";
    final public static String HasInstancesText = "Has Instances";
    final public static String SimilarToText = "Similar To";
    final public static String AlsoText = "Also";
    final public static String EntailsText = "Entails";
    final public static String MemberOfText = "Member Of";
    final public static String HasMembersText = "Has Members";
    final public static String SubstanceOfText = "Substance Of";
    final public static String HasSubstancesText = "Has Substances";
    final public static String InCategoryText = "In Category";
    final public static String HasCategoriesText = "Has Categories";
    final public static String UsageOfText = "Usage Of";
    final public static String HasUsagesText = "Has Usages";
    final public static String InRegionText = "In Region";
    final public static String RegionOfText = "Region Of";
    final public static String PertainsToText = "Pertains To";

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListGroupNames;
    private Map<String, List<String>> expandableListChildDetails;
    String currentWord;
    String selectedWord;
    WordDetailsStates states;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_result_layout);

    }

    @Override
    protected void onResume() {
        super.onResume();

        states = getIntent().getParcelableExtra("detailsStates");
        currentWord = states.getLatestWord();
        setTitle(currentWord);


        TextView singleResultTitle = (TextView) findViewById(R.id.singleResultTitle);
        singleResultTitle.setText("Single Result Information");

        int wordPosition = getIntent().getIntExtra("position", -1);


        expandableListChildDetails = SingleResultExpandableListDataPump.getData
                (states.getLatestWordDetails(), wordPosition);
        expandableListGroupNames = new ArrayList<String>(expandableListChildDetails.keySet());
        expandableListAdapter = new SingleResultExpandableListAdapter(this, expandableListGroupNames, expandableListChildDetails);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                String clickedWordParentHeadingTitle = expandableListGroupNames.get(groupPosition);

                if (
                        clickedWordParentHeadingTitle == PartOfSpeechText ||
                                clickedWordParentHeadingTitle == TypeOfText ||
                                clickedWordParentHeadingTitle == SynonymsText ||
                                clickedWordParentHeadingTitle == HasTypesText ||
                                clickedWordParentHeadingTitle == DerivativesText ||
                                clickedWordParentHeadingTitle == PartOfText ||
                                clickedWordParentHeadingTitle == HasPartsText ||
                                clickedWordParentHeadingTitle == InstanceOfText ||
                                clickedWordParentHeadingTitle == HasInstancesText ||
                                clickedWordParentHeadingTitle == SimilarToText ||
                                clickedWordParentHeadingTitle == AlsoText ||
                                clickedWordParentHeadingTitle == EntailsText ||
                                clickedWordParentHeadingTitle == MemberOfText ||
                                clickedWordParentHeadingTitle == HasMembersText ||
                                clickedWordParentHeadingTitle == SubstanceOfText ||
                                clickedWordParentHeadingTitle == HasSubstancesText ||
                                clickedWordParentHeadingTitle == InCategoryText ||
                                clickedWordParentHeadingTitle == HasCategoriesText ||
                                clickedWordParentHeadingTitle == UsageOfText ||
                                clickedWordParentHeadingTitle == HasUsagesText ||
                                clickedWordParentHeadingTitle == InRegionText ||
                                clickedWordParentHeadingTitle == RegionOfText ||
                                clickedWordParentHeadingTitle == PertainsToText

                        ) {

                    selectedWord = expandableListChildDetails.get(
                            expandableListGroupNames.get(groupPosition)).get(
                            childPosition);


                    if (!isConnected()) {
                        Toast.makeText(SingleResultActivity.this, "No internet connection. Please connect " +
                                "to the internet and try again.", Toast.LENGTH_SHORT).show();
                    } else {
                        getLoaderManager().initLoader(0, null, SingleResultActivity.this);
                    }

                }
                return false;
            }
        });

        // implementing copying to clipboard
        expandableListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position,
                                           long l) {

                long packedPosition = expandableListView.getExpandableListPosition(position);
                if (ExpandableListView.getPackedPositionType(packedPosition) ==
                        ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                    // get item ID's
                    int groupPosition = ExpandableListView.getPackedPositionGroup(packedPosition);
                    int childPosition = ExpandableListView.getPackedPositionChild(packedPosition);

                    // handle data


                    ClipData myClip;
                    ClipboardManager clipboard = (ClipboardManager)
                            getSystemService(Context.CLIPBOARD_SERVICE);

                    String textToPutOnClipboard = expandableListChildDetails.get(
                            expandableListGroupNames.get(groupPosition)).get(
                            childPosition);

                    myClip = ClipData.newPlainText("text", textToPutOnClipboard);
                    clipboard.setPrimaryClip(myClip);


                    // return true as we are handling the event.
                    return true;


                }
                return false;
            }
        });

    }


    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new WordResultLoader(this, MainActivity.BASE_URL + "/" + selectedWord);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String jsonResponse) {

        Gson gson = new GsonBuilder().create();
        WordDetails newWordDetails = gson.fromJson(jsonResponse, WordDetails.class);
        List<WordResult> newWordResults = newWordDetails.getResultsList();

        if (newWordResults != null && !newWordResults.isEmpty()) {
            states.push(newWordDetails);
            getLoaderManager().destroyLoader(0);

            Intent intent = new Intent(SingleResultActivity.this, ResultsActivity.class);
            intent.putExtra("detailsStates", states);
            intent.putExtra("backOrUpPressed", true);

            startActivityForResult(intent, 1);
        } else {
            Toast.makeText(SingleResultActivity.this, "No results for that word, please try again" +
                    ".", Toast.LENGTH_SHORT).show();
            getLoaderManager().destroyLoader(0);

        }

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        expandableListAdapter = null;
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                boolean hasBackPressed = data.getBooleanExtra("backOrUpPressed", false);

                if (hasBackPressed) {
                    states = getIntent().getParcelableExtra("detailsStates");
                    states.pop();
                    currentWord = states.getLatestWord();
                    setTitle(currentWord);
                }
            }
        }

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