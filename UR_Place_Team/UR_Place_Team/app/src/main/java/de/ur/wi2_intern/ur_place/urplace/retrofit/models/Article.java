package de.ur.wi2_intern.ur_place.urplace.retrofit.models;

import java.util.ArrayList;

public class Article {

    public int id;
    public String title;
    public String description;
    public ArrayList<ArticleCategory> articleCategories;
    public ArticleType articleType;
    public String inShopUntil;
    public double price;
    public String picture;
    public ArticleContactData articleContactData;
    public String dateCreated;
    public String lastUpdated;

    public Article(int id, String title, String description, ArrayList<ArticleCategory> articleCategories, ArticleType articleType, String inShopUntil, double price, String picture, ArticleContactData articleContactData, String dateCreated, String lastUpdated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.articleCategories = articleCategories;
        this.articleType = articleType;
        this.inShopUntil = inShopUntil;
        this.price = price;
        this.picture = picture;
        this.articleContactData = articleContactData;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }
}

