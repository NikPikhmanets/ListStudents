package com.example.studentslist.showprofile;

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

    public String getObjectType() {
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
}
