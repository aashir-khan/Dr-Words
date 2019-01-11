package com.humblecarrotstudios.android.drwords;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class SingleResultExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListGroupNames;
    private Map<String, List<String>> expandableListChildDetails;

    public SingleResultExpandableListAdapter(Context context, List<String> expandableListGroupNames,
                                             Map<String, List<String>> expandableListChildDetails) {
        this.context = context;
        this.expandableListGroupNames = expandableListGroupNames;
        this.expandableListChildDetails = expandableListChildDetails;
    }


    @Override
    public int getGroupCount() {
        return this.expandableListGroupNames.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return this.expandableListChildDetails.get(this.expandableListGroupNames.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.expandableListGroupNames.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return this.expandableListChildDetails.get(this.expandableListGroupNames.get
                (groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        View groupView = convertView;

        if (groupView == null) {
            groupView = LayoutInflater.from(this.context).inflate(R.layout.single_result_list_group,
                    null);
        }
        TextView groupTextView = (TextView) groupView
                .findViewById(R.id.resultTitle);
        groupTextView.setTypeface(null, Typeface.BOLD);
        String currentGroupTitle = (String) getGroup(groupPosition);
        groupTextView.setText(currentGroupTitle);
        return groupView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        View expandedView = convertView;

        if (expandedView == null) {
            expandedView = LayoutInflater.from(this.context).inflate(R.layout.single_result_list_item,
                    null);
        }
        TextView currentExpandedTextView = (TextView) expandedView
                .findViewById(R.id.expandedResultItem);
        final String currentExpandedListItemText = (String) getChild(groupPosition, childPosition);
        currentExpandedTextView.setText(currentExpandedListItemText);
        return expandedView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
