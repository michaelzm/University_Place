package de.ur.wi2_intern.ur_place.urplace.retrofit.models;

public class ArticleCategory {
    public int id;
    public String name;
    public String dateCreated;
    public String lastUpdated;

    public ArticleCategory(int id, String name, String dateCreated, String lastUpdated) {
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }

    public ArticleCategory(String name, String dateCreated, String lastUpdated) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }

    public ArticleCategory(String name){
        this.name=name;
    }
    public String getString(){
        return name;
    }
}
