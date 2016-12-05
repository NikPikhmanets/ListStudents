package com.example.studentslist.contact;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studentslist.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class ContactAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ContactItem> contacts;

    ContactAdapter(Context context, ArrayList<ContactItem> contacts) {
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
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.contact_list_item, parent, false);
        }

        ContactItem contact = (ContactItem) getItem(position);

        ImageView avatar = (ImageView) view.findViewById(R.id.contact_avatar);
        TextView contactName = (TextView) view.findViewById(R.id.contact_name);
        TextView contactPhone = (TextView) view.findViewById(R.id.contact_number);

        contactName.setText(contact.getName());
        contactPhone.setText(contact.getNumber());

        if(contact.getUriAvatar() != null)
            Picasso.with(view.getContext()).load(contact.getUriAvatar()).into(avatar);

        final View finalView = view;
        view.findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView number = (TextView) finalView.findViewById(R.id.contact_number);
                String toSms = "smsto:" + number.getText().toString();
                Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse(toSms));
                v.getContext().startActivity(sms);
            }
        });

        return view;
    }

    void swapCursor(ArrayList<ContactItem> contacts) {
        this.contacts = contacts;
        this.notifyDataSetChanged();
    }
}
