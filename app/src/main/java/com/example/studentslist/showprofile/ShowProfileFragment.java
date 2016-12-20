package com.example.studentslist.showprofile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentslist.ProfileInfo;
import com.example.studentslist.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.studentslist.R.id.avatar;

public class ShowProfileFragment extends Fragment {

    String GOOGLE_API_KEY = "AIzaSyA-mwyNqHCvmMKEwa_a3OLP1JXz_f8th9g";

    List<ProfileInfo> profileInfo = new ArrayList<>();
    ShowProfileAdapter profileAdapter;
    ListView listV;
    ImageView imageView;
    TextView userName;
    LinearLayout fragmentLayout;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_show_profile, container, false);
        listV = (ListView) rootView.findViewById(R.id.list_description_profile);
        imageView = (ImageView) rootView.findViewById(avatar);

        userName = (TextView) rootView.findViewById(R.id.user_name);
        fragmentLayout = (LinearLayout) rootView.findViewById(R.id.fragment_layout);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);

        String googlePlusID = getActivity().getIntent().getStringExtra("googlePlusID");
        String gitHubID = getActivity().getIntent().getStringExtra("gitHubID");

         if (googlePlusID != null) {

             getInfoGoogleProfile(googlePlusID);

        } else if (gitHubID != null) {

            getInfoGitProfile(gitHubID);
        } else {

             Toast.makeText(getContext(), "Нету данных", Toast.LENGTH_SHORT).show();
             getActivity().finish();
         }
        return rootView;
    }

    public void getInfoGoogleProfile(String userId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InfoInterface infoInterface = retrofit.create(InfoInterface.class);
        Call<StudentGoogleInfo> info = infoInterface.googleInfo(userId, GOOGLE_API_KEY);
        info.enqueue(new Callback<StudentGoogleInfo>() {
            @Override
            public void onResponse(Call<StudentGoogleInfo> call, Response<StudentGoogleInfo> response) {
                String imageURL = response.body().getImage().getUrl().replace("?sz=50", "");
                Picasso.with(getContext()).load(imageURL).into(imageView);

                if( !response.body().getDisplayName().equals(""))
                    userName.setText(response.body().getDisplayName());

                profileInfo.add(new ProfileInfo("birthday", response.body().getBirthday()));
                profileInfo.add(new ProfileInfo("Type", response.body().getObjectType()));
                profileInfo.add(new ProfileInfo("URL", response.body().getUrl()));
                profileInfo.add(new ProfileInfo("Circle", response.body().getCircledByCount()));

                profileAdapter = new ShowProfileAdapter(getContext(), profileInfo);
                listV.setAdapter(profileAdapter);

                viewProgressBar(true);
            }

            @Override
            public void onFailure(Call<StudentGoogleInfo> call, Throwable t) {

            }
        });
    }

    private void viewProgressBar(boolean view) {
        if(view) {
            progressBar.setVisibility(View.INVISIBLE);
            fragmentLayout.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
            fragmentLayout.setVisibility(View.INVISIBLE);
        }

    }


    public void getInfoGitProfile(String userLogin) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InfoInterface infoInterface = retrofit.create(InfoInterface.class);
        Call<StudentGitInfo> info = infoInterface.gitInfo(userLogin);
        info.enqueue(new Callback<StudentGitInfo>() {
            @Override
            public void onResponse(Call<StudentGitInfo> call, Response<StudentGitInfo> response) {
                String imageUrl = response.body().getAvatar_url().replace("?v=3", "");
                Picasso.with(getContext()).load(imageUrl).into(imageView);

                if(response.body().getName() != null)
                    userName.setText(response.body().getName());

                profileInfo.add(new ProfileInfo("LOGIN", response.body().getLogin()));
                profileInfo.add(new ProfileInfo("GIT link", response.body().getHtml_url()));
                profileInfo.add(new ProfileInfo("COMPANY", response.body().getCompany()));
                profileInfo.add(new ProfileInfo("BLOG", response.body().getBlog()));
                profileInfo.add(new ProfileInfo("LOCATION", response.body().getLocation()));
                profileInfo.add(new ProfileInfo("Repository", response.body().getPublic_repos()));
                profileInfo.add(new ProfileInfo("FOLLOWERS", response.body().getFollowers()));
                profileInfo.add(new ProfileInfo("FOLLOWING", response.body().getFollowing()));
                profileInfo.add(new ProfileInfo("CREATED", response.body().getCreated_at()));
                profileInfo.add(new ProfileInfo("UPDATE", response.body().getUpdated_at()));

                profileAdapter = new ShowProfileAdapter(getContext(), profileInfo);
                listV.setAdapter(profileAdapter);

                viewProgressBar(true);
            }

            @Override
            public void onFailure(Call<StudentGitInfo> call, Throwable t) {

            }
        });
    }

    @Override
    public void onStart() {
        viewProgressBar(false);
        super.onStart();
    }
}
