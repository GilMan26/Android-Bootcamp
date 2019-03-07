package com.example.assignment3.Ques3;

public class Model {
    private String text;
    int image;
    int type;

    public Model(int image) {
        this.image = image;
    }

    public Model(String text) {
        this.text = text;
    }

    public Model(String text, int image) {
        this.text = text;
        this.image = image;
    }

    public Model(String text, int image, int type) {
        this.text = text;
        this.image = image;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
