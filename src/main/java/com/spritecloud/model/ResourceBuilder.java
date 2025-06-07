package com.spritecloud.model;

public class ResourceBuilder {

    private int id;
    private String name;
    private int year;
    private String color;
    private String pantone_value;

    public ResourceBuilder id(int id) {
        this.id = id;
        return this;
    }

    public ResourceBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ResourceBuilder year(int year) {
        this.year = year;
        return this;
    }

    public ResourceBuilder color(String color) {
        this.color = color;
        return this;
    }

    public ResourceBuilder pantone_value(String pantone_value) {
        this.pantone_value = pantone_value;
        return this;
    }

    public Resources build() {
        Resources resources = new Resources();
        resources.setId(id);
        resources.setName(name);
        resources.setYear(year);
        resources.setColor(color);
        resources.setPantone_value(pantone_value);
        return resources;
    }
}