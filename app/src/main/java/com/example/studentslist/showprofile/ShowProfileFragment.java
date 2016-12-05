package com.example.studentslist.showprofile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
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
        Call<GoogleInfo> info = infoInterface.googleInfo(userId, GOOGLE_API_KEY);
        info.enqueue(new Callback<GoogleInfo>() {
            @Override
            public void onResponse(Call<GoogleInfo> call, Response<GoogleInfo> response) {
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
            }

            @Override
            public void onFailure(Call<GoogleInfo> call, Throwable t) {

            }
        });
    }



    public void getInfoGitProfile(String userLogin) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InfoInterface infoInterface = retrofit.create(InfoInterface.class);
        Call<GitInfo> info = infoInterface.gitInfo(userLogin);
        info.enqueue(new Callback<GitInfo>() {
            @Override
            public void onResponse(Call<GitInfo> call, Response<GitInfo> response) {
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
            }

            @Override
            public void onFailure(Call<GitInfo> call, Throwable t) {

            }
        });
    }
}
