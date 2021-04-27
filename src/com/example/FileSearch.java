package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileSearch {
    String path;
    String regex;
    String zipFileName;
    Pattern pattern;
    List<File> zipFiles = new ArrayList<File>();

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
        try {
            this.pattern = Pattern.compile(regex);
        } catch (Exception e) {
            this.pattern = null;
        }
        
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
        System.out.println(file.getAbsolutePath());
        try {
            if(searchFile(file)){
                zipFile();
            }
        } catch (AccessDeniedException e) {
            System.out.println("Skiping directory: " + file + "...");
        } catch (Exception e) {
            System.err.println("Error processing file: " + 
                file + ": " + e);
        }
        
    }

    public boolean searchFile(File file) throws IOException {
        if (searchFileJava8(file)){
            System.out.println("Found file: " + file);
            return true;
        } else {
            System.out.println("Skipping file: " + file);
            return false;
        }
    }

    public void zipFile() throws IOException {
        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(getZipFileName()))){
            File baseDir = new File(getPath());
            for (File file : zipFiles) {
                // Filename must be relative path, not absolute
                String fileName = getRelativeFilename(file, baseDir);
                ZipEntry zipEntry = new ZipEntry(fileName);
                zipEntry.setTime(file.lastModified());
                out.putNextEntry(zipEntry);

                Files.copy(file.toPath(), out);
                out.closeEntry();
                
            }
        }
    }

    private String getRelativeFilename(File file, File baseDir) {
        String fileName = file.getAbsolutePath().substring(baseDir.getAbsolutePath().length());

        // The ZipEntry MUST USE "/" instead of "\"
        fileName = fileName.replace('\\', '/');

        while (fileName.startsWith("/")){
            fileName = fileName.substring(1);
        }
        return null;
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

    private boolean searchText(String text) {
        return (this.getRegex() == null) ? true : 
            this.pattern.matcher(text).matches();
    }

    public void addFileToZip(File file) {
        if (getZipFileName() != null) {
            zipFiles.add(file);
        }
    }

}
