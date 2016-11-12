package com.example.studentslist;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class ImageActivity extends MainActivity {

    public static final int IDM_CAMERA = 101;
    public static final int IDM_GALLERY = 102;

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        img = (ImageView) findViewById(R.id.photo_image);

        registerForContextMenu(img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] fromItem = {"Camera", "Gallery"};

                AlertDialog.Builder builder = new AlertDialog.Builder(ImageActivity.this);
                builder.setTitle("Load image from:");

                builder.setItems(fromItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {

                        switch (item) {
                            case 0:
                                loadImageFromCamera();
                                break;
                            case 1:
                                loadImageFromGallery();
                                break;
                        }
                    }
                });
                builder.setCancelable(true);
                builder.show();
            }
        });

    }

    void loadImageFromCamera() {

        Intent intentImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentImage, 0);
    }

    void loadImageFromGallery() {

        Intent intentPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intentPhoto, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    img.setImageBitmap(thumbnail);
                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    img.setImageURI(selectedImage);
                }
                break;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(Menu.NONE, IDM_CAMERA, Menu.NONE, "Сделать снимок камерой");
        menu.add(Menu.NONE, IDM_GALLERY, Menu.NONE, "Загрузить изобр. из галереи");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case IDM_CAMERA:
                loadImageFromCamera();
                break;
            case IDM_GALLERY:
                loadImageFromGallery();
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }
}
