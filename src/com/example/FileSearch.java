package com.example;

import java.io.File;

public class FileSearch {
    String path;
    String regex;
    String zipFileName;

    // Constructors
    public FileSearch() {

    }

    public FileSearch(String path, String regex, String zipFileName) {
        this.path = path;
        this.regex = regex;
        this.zipFileName = zipFileName;
    }
    
    public void walkDirectory(String path) {
        System.out.println("walkDirectory: " + path);
        searchFile(null);
        addFileToZip(null);
    }

    public void searchFile(File file) {
        System.out.println("searchFile: " + path);
    }

    public void addFileToZip(File file) {
        System.out.println("addFileToZip: " + path);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getZipFileName() {
        return zipFileName;
    }

    public void setZipFileName(String zipFileName) {
        this.zipFileName = zipFileName;
    }



}
