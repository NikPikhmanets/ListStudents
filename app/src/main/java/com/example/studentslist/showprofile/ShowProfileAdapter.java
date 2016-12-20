package com.example.studentslist.showprofile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.studentslist.R;

import java.util.List;

class ShowProfileAdapter extends ArrayAdapter<ProfileInfo> {

    ShowProfileAdapter(Context context, List<ProfileInfo> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_details, parent, false);
        }

        final ProfileInfo profileInfo = getItem(position);

        TextView titleText = (TextView) listItemView.findViewById(R.id.itemMainText);
        TextView textDescription = (TextView) listItemView.findViewById(R.id.itemDescription);

        assert profileInfo != null;
        titleText.setText(profileInfo.getKey());
        textDescription.setText(profileInfo.getDescription());

        return listItemView;
    }
}
