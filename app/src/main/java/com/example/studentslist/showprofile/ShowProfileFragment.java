package com.example.studentslist.showprofile;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.studentslist.R;
import com.example.studentslist.Students;
import com.example.studentslist.allasynctask.FetchGitHubTask;
import com.example.studentslist.allasynctask.FetchGoogleTask;
import com.example.studentslist.allasynctask.FetchImageTask;

import java.util.ArrayList;

public class ShowProfileFragment extends Fragment {

    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        adapter = new ArrayAdapter<>(
                getActivity(),                      // ссылку на активность через метод getActivity()
                R.layout.list_item_details,
                R.id.itemDescription,
                new ArrayList<String>());

        View rootView = inflater.inflate(R.layout.fragment_show_profile, container, false); //из содержимого layout-файла создать View-элемент

        ListView listV = (ListView) rootView.findViewById(R.id.list_description_profile);
        listV.setAdapter(adapter);

        String googlePlusID = getActivity().getIntent().getStringExtra("googlePlusID");
        String gitHubID = getActivity().getIntent().getStringExtra("gitHubID");

        Students students = new Students();
        String[] studentInfo = null;
        if (googlePlusID != null) {

            FetchGoogleTask fetchGoogleTask = new FetchGoogleTask();
            fetchGoogleTask.execute(googlePlusID);
            try {
                studentInfo = fetchGoogleTask.get();
            } catch (Exception e) {
            }

        } else if (gitHubID != null) {

            FetchGitHubTask fetchGitHubTask = new FetchGitHubTask();
            fetchGitHubTask.execute(gitHubID);

            try {
                studentInfo = fetchGitHubTask.get();
            } catch (Exception e) {
            }

            if (studentInfo != null) {
                if (!studentInfo[0].equals("null")) {
                    FetchImageTask fetchImageTask = new FetchImageTask(studentInfo[0]);
                    fetchImageTask.execute();
                    try {
                        Bitmap bitmap = fetchImageTask.get();
                        ImageView imageView = (ImageView) rootView.findViewById(R.id.avatar);
                        imageView.setImageBitmap(bitmap);
                    } catch (Exception e) {
                    }
                }
                TextView userName = (TextView) rootView.findViewById(R.id.user_name);
                userName.setText(studentInfo[1]);

                adapter.clear();
                for (int i = 2; i < 12; i++) {
                    adapter.add(studentInfo[i]);
                }
            }
        }
        return rootView;
    }
}
