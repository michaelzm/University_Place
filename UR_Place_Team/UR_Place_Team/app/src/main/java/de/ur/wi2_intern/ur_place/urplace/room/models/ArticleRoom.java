package de.ur.wi2_intern.ur_place.urplace.room.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleType;
import de.ur.wi2_intern.ur_place.urplace.room.converter.ArticleTypeConverter;

@Entity(tableName = "article")
public class ArticleRoom {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "articleID")
    private long id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "categoryTitle")
    private String articleCategoryTitle;
    @ColumnInfo(name = "category_id")
    private int categoryId;
    @ColumnInfo(name = "type")
    @TypeConverters(ArticleTypeConverter.class)
    private ArticleType articleType;
    @ColumnInfo(name = "until")
    private String inShopUntil;
    @ColumnInfo(name = "price")
    private double price;
    @ColumnInfo(name = "picture")
    private String picture;
    @ColumnInfo(name = "contact_name")
    private String name;
    @ColumnInfo(name = "contact_street")
    private String street;
    @ColumnInfo(name = "contact_phone")
    private String telephone;
    @ColumnInfo(name = "contact_email")
    private String email;
    @ColumnInfo(name = "dateCreated")
    private String date;
    @ColumnInfo(name = "favorite")
    private boolean favorite;
    @ColumnInfo(name = "sellerId")
    private int sellerId;
    @ColumnInfo(name = "bought")
    private boolean bought;
    @ColumnInfo(name="created")
    private String created;



    public ArticleRoom() {

    }

    public ArticleRoom(long id, String title, String description, ArticleType articleType,
                       String inShopUntil, double price, String picture, String name, String street,
                       String telephone, String email, String date,int sellerId, boolean bought, String created) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.articleType = articleType;
        this.inShopUntil = inShopUntil;
        this.price = price;
        this.picture = picture;
        this.name = name;
        this.street = street;
        this.telephone = telephone;
        this.email = email;
        this.date=date;
        this.sellerId=sellerId;
        this.bought=bought;
        this.created=created;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean getBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArticleCategoryTitle() {
        return articleCategoryTitle;
    }

    public void setArticleCategoryTitle(String articleCategoryTitle) {
        this.articleCategoryTitle = articleCategoryTitle;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public ArticleType getArticleType() {
        return articleType;
    }

    public void setArticleType(ArticleType articleType) {
        this.articleType = articleType;
    }

    public String getInShopUntil() {
        return inShopUntil;
    }

    public void setInShopUntil(String inShopUntil) {
        this.inShopUntil = inShopUntil;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
