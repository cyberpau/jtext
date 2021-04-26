# SearchRaptor - A Simple Java File Search App

## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

## Dependency Management

The `JAVA DEPENDENCIES` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-pack/blob/master/release-notes/v0.9.0.md#work-with-jar-files-directly).

## Functions

| Description             | Variable      | Method        |
|-------------------------|---------------|---------------|
| Read all files in a dir | String path   | `walkDirectory(String path)` |
| Search each file        | String regex  | `searchFile(File file)` |
| Add all matching files to Zip | String zipFileName | `addFileToZip(File file)` |