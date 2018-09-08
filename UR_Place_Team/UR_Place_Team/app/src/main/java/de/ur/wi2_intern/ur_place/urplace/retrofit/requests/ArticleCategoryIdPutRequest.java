package de.ur.wi2_intern.ur_place.urplace.retrofit.requests;

public class ArticleCategoryIdPutRequest {
    public int id;
    public String name;
    public String dateCreated;
    public String lastUpdated;

    public ArticleCategoryIdPutRequest(int id, String name, String dateCreated, String lastUpdated) {
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }
}
