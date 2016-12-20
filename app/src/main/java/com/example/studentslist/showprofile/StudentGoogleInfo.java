package com.example.studentslist.showprofile;

import com.example.studentslist.showprofile.gson.Cover;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class StudentGoogleInfo {

    private String birthday;
    private String displayName;
    private String objectType;
    private Image  image;
    private String url;
    private String circledByCount;

    String getBirthday() {
        return birthday;
    }

    String getDisplayName() {
        return displayName;
    }

    String getObjectType() {
        return objectType;
    }

    public Image getImage() {
        return image;
    }

    String getUrl() {
        return url;
    }

    String getCircledByCount() {
        return circledByCount;
    }

    class Image{

        private String url;

        String getUrl() {
            return url;
        }
    }

    @SerializedName("cover")
    @Expose
    private Cover cover;

    public Cover getCover() {
        return cover;
    }
}
