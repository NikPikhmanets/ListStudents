package com.example.studentslist.contact;

import android.content.ContentProviderOperation;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
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

import static android.Manifest.permission.READ_CONTACTS;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.support.v4.app.LoaderManager.LoaderCallbacks;

public class PhoneBookActivity extends AppCompatActivity implements LoaderCallbacks<Cursor>, View.OnClickListener {

    private static final int READ_CONTACTS_REQUEST = 1;

    private static final String[] COLUMNS_TO_BE_BOUND = new String[]{
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI
    };

    ListView listView;
    FloatingActionButton fab;
    ContactAdapter adapter;
    Uri photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_book);

        listView = (ListView) findViewById(R.id.lv_phone_book);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        if(ActivityCompat.checkSelfPermission(this, READ_CONTACTS) == PERMISSION_GRANTED) {
            getSupportLoaderManager().initLoader(1, null, this).forceLoad();
            adapter = new ContactAdapter(this, new ArrayList<ContactItem>());
            listView.setAdapter(adapter);
        } else {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, READ_CONTACTS)){
                showExplanationDialog();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{READ_CONTACTS}, READ_CONTACTS_REQUEST);
            }
        }
    }

    private void showExplanationDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Разрешение на чтение контактов не получено")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(PhoneBookActivity.this, new String[]{READ_CONTACTS}, READ_CONTACTS_REQUEST);
                    }
                })
                .setNeutralButton("Перейти в настройки", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openPermissionSettings();
                    }
                })
                .create()
                .show();
    }

    private void openPermissionSettings() {
        final Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                .addCategory(Intent.CATEGORY_DEFAULT)
                .setData(Uri.parse("package:" + getPackageName()))
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                .addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(intent);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
        return new CursorLoader(this, ContactsContract.CommonDataKinds.Phone.CONTENT_URI, COLUMNS_TO_BE_BOUND, null, null, sort);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        ArrayList<ContactItem> contacts = new ArrayList<>();
//        List<String> names = new ArrayList<>();
        data.moveToFirst();

        while (data.moveToNext()) {
            String name = data.getString(data.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = data.getString(data.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String photoThumb = data.getString(data.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI));

            if (photoThumb != null) {
                photo = Uri.parse(photoThumb);
            }
            else
                photo = null;
//            if (!names.contains(name)) {
//                names.add(name);

                ContactItem contact = new ContactItem(name, number, photo);
                contacts.add(contact);
//            }
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
