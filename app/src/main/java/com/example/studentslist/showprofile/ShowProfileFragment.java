package com.example.studentslist.showprofile;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.studentslist.ProfileInfo;
import com.example.studentslist.R;
import com.example.studentslist.allasynctask.FetchGitHubTask;
import com.example.studentslist.allasynctask.FetchGoogleTask;
import com.example.studentslist.allasynctask.FetchImageTask;

import java.util.ArrayList;
import java.util.List;

public class ShowProfileFragment extends Fragment {

    ShowProfileAdapter profileAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_show_profile, container, false);
        ListView listV = (ListView) rootView.findViewById(R.id.list_description_profile);


        TextView userName = (TextView) rootView.findViewById(R.id.user_name);

        String googlePlusID = getActivity().getIntent().getStringExtra("googlePlusID");
        String gitHubID = getActivity().getIntent().getStringExtra("gitHubID");

        String[] studentInfo = null;
        if (googlePlusID != null) {

            FetchGoogleTask fetchGoogleTask = new FetchGoogleTask();
            fetchGoogleTask.execute(googlePlusID);
            try {
                studentInfo = fetchGoogleTask.get();
            } catch (Exception ignored) {
            }

        } else if (gitHubID != null) {

            FetchGitHubTask fetchGitHubTask = new FetchGitHubTask();
            fetchGitHubTask.execute(gitHubID);

            try {
                studentInfo = fetchGitHubTask.get();
            } catch (Exception ignored) {
            }
        }


        if (studentInfo != null) {
            if (!studentInfo[0].equals("null")) {
                FetchImageTask fetchImageTask = new FetchImageTask(studentInfo[0]);
                fetchImageTask.execute();
                try {
                    Bitmap bitmap = fetchImageTask.get();
                    ImageView imageView = (ImageView) rootView.findViewById(R.id.avatar);
                    imageView.setImageBitmap(bitmap);
                } catch (Exception ignored) {
                }
            }

            userName.setText(studentInfo[1]);

            List<ProfileInfo> info = new ArrayList<>();
            if (googlePlusID != null) {

                    info.add(new ProfileInfo("Ссылка на страницку", studentInfo[2]));
                    info.add(new ProfileInfo("Круги", studentInfo[3]));

//                    if (!studentInfo[4].equals("null")) {
//                        FetchImageTask fetchImageTask = new FetchImageTask(studentInfo[0]);
//                        fetchImageTask.execute();
//                        try {
//                            Bitmap bitmap = fetchImageTask.get();
//                            ImageView imageView = (ImageView) rootView.findViewById(R.id.cover_image);
//                            imageView.setImageBitmap(bitmap);
//                        } catch (Exception ignored) {
//                        }
//                    }

            } else {

                info.add(new ProfileInfo("LOGIN", studentInfo[2]));
                info.add(new ProfileInfo("GIT link", studentInfo[3]));
                info.add(new ProfileInfo("COMPANY", studentInfo[4]));
                info.add(new ProfileInfo("BLOG", studentInfo[5]));
                info.add(new ProfileInfo("LOCATION", studentInfo[6]));
                info.add(new ProfileInfo("Repository", studentInfo[7]));
                info.add(new ProfileInfo("FOLLOWERS", studentInfo[8]));
                info.add(new ProfileInfo("FOLLOWING", studentInfo[9]));
                info.add(new ProfileInfo("CREATED", studentInfo[10]));
                info.add(new ProfileInfo("UPDATE", studentInfo[11]));
            }

            profileAdapter = new ShowProfileAdapter(getContext(), info);
            listV.setAdapter(profileAdapter);
        }
        return rootView;
    }
}
