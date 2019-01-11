package com.humblecarrotstudios.android.drwords;

import com.humblecarrotstudios.android.drwords.data.WordResult;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class WordsAdapter extends ArrayAdapter<WordResult> {

    private List<WordResult> results;

    public WordsAdapter(Context context, List<WordResult> results) {
        super(context, 0, results);
        this.results = results;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.results_list_item,
                    parent, false);
        }

        TextView resultView = (TextView) listItemView.findViewById(R.id.result_view);
        resultView.setText(this.results.get(position).getDefinition());
        resultView.setGravity(Gravity.CENTER_VERTICAL);
        listItemView.setMinimumHeight(parent.getHeight() / getCount());

        return listItemView;
    }
}
