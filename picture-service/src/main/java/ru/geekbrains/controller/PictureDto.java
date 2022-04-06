package ru.geekbrains.controller;

import java.nio.file.Path;

public class PictureDto {

    private String contentType;

    private Path path;

    private byte[] data;

    public PictureDto(String contentType, Path path) {
        this.contentType = contentType;
        this.path = path;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
