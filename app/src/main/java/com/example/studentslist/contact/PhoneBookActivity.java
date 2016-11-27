package com.example.studentslist.contact;

import android.content.ContentProviderOperation;
import android.content.DialogInterface;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.studentslist.R;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.LoaderManager.LoaderCallbacks;

public class PhoneBookActivity extends AppCompatActivity implements LoaderCallbacks<Cursor>, View.OnClickListener {

    private static final String[] COLUMNS_TO_BE_BOUND = new String[]{
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
    };

    ListView listView;
    FloatingActionButton fab;
    ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_book);
        getSupportLoaderManager().initLoader(1, null, this);

        listView = (ListView) findViewById(R.id.lv_phone_book);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        adapter = new ContactAdapter(this, new ArrayList<ContactItem>());
        listView.setAdapter(adapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
        return new CursorLoader(this, ContactsContract.CommonDataKinds.Phone.CONTENT_URI, COLUMNS_TO_BE_BOUND, null, null, sort);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        ArrayList<ContactItem> contacts = new ArrayList<>();
        List<String> names = new ArrayList<>();
        data.moveToFirst();

        while (data.moveToNext()) {
            String name = data.getString(data.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = data.getString(data.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            if (!names.contains(name)) {
                names.add(name);

                ContactItem contact = new ContactItem(name, number);
                contacts.add(contact);
            }
        }
        adapter.swapCursor(contacts);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            final View dialogView = getLayoutInflater().inflate(R.layout.add_contact_dialog, null);
            new AlertDialog.Builder(this)
                    .setView(dialogView)
                    .setTitle("Добавить контакт")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            EditText user = (EditText) dialogView.findViewById(R.id.name);
                            EditText phone = (EditText) dialogView.findViewById(R.id.number);
                            if (!user.getText().toString().equals("") && !phone.getText().toString().equals("")) {
                                String username = user.getText().toString().trim();
                                String phone_number = phone.getText().toString().trim();
                                addContact(username, phone_number);
                                adapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(PhoneBookActivity.this, "Контакт не добавлен", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
        }
    }
    public void addContact(String displayName, String number) {

        ArrayList<ContentProviderOperation> cntProOper = new ArrayList<>();
        int contactIndex = cntProOper.size();

        cntProOper.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());

        cntProOper.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, contactIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, displayName)
                .build());

        cntProOper.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, contactIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE).build());
        try {
            getContentResolver().applyBatch(ContactsContract.AUTHORITY, cntProOper);
        } catch (OperationApplicationException | RemoteException exp) {
            exp.printStackTrace();
        }
    }
}
