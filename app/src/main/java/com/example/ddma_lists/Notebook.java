package com.example.ddma_lists;

public class Notebook {
    private final String name;
    private final String cpu;

    public Notebook(String name, String cpu) {
        this.name = name;
        this.cpu = cpu;
    }

    public String getName() {
        return name;
    }

    public String getCpuName() {
        return cpu;
    }
}
