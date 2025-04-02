package com.example.ddma_lists;

public class Notebook {
    private final String name;
    private final String cpu;
    private final int imageId;

    public Notebook(String name, String cpu, int imageId) {
        this.name = name;
        this.cpu = cpu;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public String getCpuName() {
        return cpu;
    }

    public int getImageId() {
        return imageId;
    }
}
