package com.uit.ucompanion.objects;

/**
 * Created by dell on 7/30/2016.
 */
public class Lectures {
    private String file_url;
    private String size;
    private String title;

    public Lectures() {
    }

    public Lectures(String file_url, String size, String title) {
        this.file_url = file_url;
        this.size = size;
        this.title = title;
    }

    public String getFile_url() {
        return file_url;
    }

    public String getSize() {
        return size;
    }

    public String getTitle() {
        return title;
    }
}
