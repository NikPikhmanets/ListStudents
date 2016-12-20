package com.example.studentslist.showprofile.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Николай on 21.12.2016.
 */

public class Cover {
    @SerializedName("coverPhoto")
    @Expose
    private CoverPhoto coverPhoto;

    public CoverPhoto getCoverPhoto() {
        return coverPhoto;
    }
}
