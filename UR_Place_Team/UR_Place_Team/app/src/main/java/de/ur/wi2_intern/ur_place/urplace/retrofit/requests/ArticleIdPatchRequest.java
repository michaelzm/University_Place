package de.ur.wi2_intern.ur_place.urplace.retrofit.requests;

import java.util.ArrayList;

import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleCategory;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleContactData;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleType;

public class ArticleIdPatchRequest {
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
    public boolean bought;

    public ArticleIdPatchRequest(int id, String title, String description, ArrayList<ArticleCategory> articleCategories, ArticleType articleType, String inShopUntil, double price, String picture, ArticleContactData articleContactData, String dateCreated, String lastUpdated, boolean bought) {
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
        this.bought=bought;
    }
}
