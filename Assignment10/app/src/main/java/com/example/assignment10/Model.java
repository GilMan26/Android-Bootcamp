package com.example.assignment10;

public class Model {
    String name;
    String path;
    int type;
    final static int TYPE_FILE=1;
    final static int TYPE_DIRECTORY=2;


    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public int getType() {
        return type;
    }

    public Model(String name, String path, int type) {
        this.name = name;
        this.path = path;
        this.type = type;
    }
}
