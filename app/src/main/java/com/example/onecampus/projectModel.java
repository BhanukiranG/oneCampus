package com.example.onecampus;

public class projectModel {
    private String description,title,clg,link;
    private String productImage;

    public projectModel() {
    }

    public projectModel(String description, String title, String clg, String link, String productImage) {
        this.description = description;
        this.title = title;
        this.clg = clg;
        this.link = link;
        this.productImage = productImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClg() {
        return clg;
    }

    public void setClg(String clg) {
        this.clg = clg;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
