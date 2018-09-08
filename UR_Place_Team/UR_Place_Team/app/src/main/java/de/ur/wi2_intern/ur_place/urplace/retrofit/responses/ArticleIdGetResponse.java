package de.ur.wi2_intern.ur_place.urplace.retrofit.responses;

import java.util.ArrayList;

import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleCategory;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleContactData;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleType;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Profile;

public class ArticleIdGetResponse {
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
    public Profile seller;


}
