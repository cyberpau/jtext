package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileSearch {
    String path;
    String regex;
    String zipFileName;

    public FileSearch() {

    }

    public FileSearch(String path, String regex, String zipFileName) {
        this.path = path;
        this.regex = regex;
        this.zipFileName = zipFileName;
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

    public void walkDirectory(String path) throws IOException {
        Files.walk(Paths.get(path))
            .forEach(f -> processFile(f.toFile()));
    }

    private void processFile(File file) {
        System.out.println(file);
        try {
            if(searchFile(file)){
                addFileToZip(file);
            }
        } catch (Exception e) {
            System.err.println("Error processing file: " + 
                file + ": " + e);
        }
        
    }

    public boolean searchFile(File file) throws IOException {
        return searchFileJava8(file);
    }

    public boolean searchFileJava6(File file) throws FileNotFoundException {
        boolean found = false;
        
        Scanner scanner = new Scanner(file, "UTF-8");
        while (scanner.hasNextLine()) {
            found = searchText(scanner.nextLine());
            if (found) { break;}
        }
        scanner.close();
        return found;
    }

    public boolean searchFileJava8(File file) throws IOException {
        return Files.lines(file.toPath(), StandardCharsets.UTF_8)
            .anyMatch(t -> searchText(t));
    }

    private boolean searchText(String nextLine) {
        return false;
    }

    public void addFileToZip(File file) {
        System.out.println("addFileToZip: " + file);
    }

}
