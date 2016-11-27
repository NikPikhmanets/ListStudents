package com.example.studentslist.contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.studentslist.R;

import java.util.ArrayList;

/**
 * Created by Николай on 27.11.2016.
 */

public class ContactAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ContactItem> contacts;

    public ContactAdapter(Context context, ArrayList<ContactItem> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.contact_list_item, parent, false);
        }

        ContactItem contact = (ContactItem)getItem(position);

        TextView contactName = (TextView)view.findViewById(R.id.contact_name);
        TextView contactPhone = (TextView)view.findViewById(R.id.contact_number);

        contactName.setText(contact.getName());
        contactPhone.setText(contact.getNumber());

        return view;
    }

    public void swapCursor(ArrayList<ContactItem> contacts){
        this.contacts = contacts;
        this.notifyDataSetChanged();
    }
}
