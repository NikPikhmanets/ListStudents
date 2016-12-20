package com.example.studentslist.showprofile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Николай on 28.11.2016.
 */

public interface InfoInterface {
    @GET("users/{username}")
    Call<StudentGitInfo> gitInfo(@Path("username") String username);

    @GET("plus/v1/people/{username}")
    Call<StudentGoogleInfo> googleInfo(@Path("username") String username, @Query("key") String api);
}
