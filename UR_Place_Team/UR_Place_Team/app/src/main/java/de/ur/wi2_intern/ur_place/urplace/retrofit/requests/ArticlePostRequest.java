package de.ur.wi2_intern.ur_place.urplace.retrofit.requests;

import java.util.ArrayList;

import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleCategory;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleContactData;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleType;

public class ArticlePostRequest {
    public String title;
    public String description;
    public ArrayList<ArticleCategory> articleCategories;
    public ArticleType articleType;
    public String inShopUntil;
    public double price;
    public String picture;
    public ArticleContactData articleContactData;

    public ArticlePostRequest(String title, String description, ArrayList<ArticleCategory> articleCategories, ArticleType articleType, String inShopUntil, double price, String picture, ArticleContactData articleContactData) {
        this.title = title;
        this.description = description;
        this.articleCategories = articleCategories;
        this.articleType = articleType;
        this.inShopUntil = inShopUntil;
        this.price = price;
        this.picture = picture;
        this.articleContactData = articleContactData;
    }
}
