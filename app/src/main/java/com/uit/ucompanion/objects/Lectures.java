package com.uit.ucompanion.objects;

/**
 * Created by dell on 7/30/2016.
 */
public class Lectures {
    private String file_url;
    private String size;
    private String title;
    private String file_type;

    public Lectures() {
    }

    public Lectures(String file_url, String size, String title, String file_type) {
        this.file_url = file_url;
        this.size = size;
        this.title = title;
        this.file_type = file_type;

    }

    public String getFile_type() {
        return file_type;
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
