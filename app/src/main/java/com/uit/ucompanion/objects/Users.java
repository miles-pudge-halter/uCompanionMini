package com.uit.ucompanion.objects;

import android.net.Uri;

/**
 * Created by dell on 7/29/2016.
 */
public class Users {
    private String username;
    private Uri profilePic;
    private String email;
    private String year;
    private String major;
    private String section;

    public Users() {
    }

    public Users(String username, String email, Uri profilePic, String year, String major, String section) {
        this.username = username;
        this.profilePic = profilePic;
        this.email = email;
        this.year = year;
        this.major = major;
        this.section = section;
    }

    public String getYear() {
        return year;
    }

    public String getMajor() {
        return major;
    }

    public String getSection() {
        return section;
    }

    public String getUsername() {
        return username;
    }

    public Uri getProfilePic() {
        return profilePic;
    }

    public String getEmail() {
        return email;
    }
}
