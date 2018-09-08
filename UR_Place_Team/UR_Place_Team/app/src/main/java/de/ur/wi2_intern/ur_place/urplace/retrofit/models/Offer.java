package de.ur.wi2_intern.ur_place.urplace.retrofit.models;

import java.util.Date;

public class Offer {
    private String name;
    private Date createDate;
    private double price;
    private OfferType type;
    private int offerID;
    private int offerCounterForID=1;


    public Offer(String name, Date createDate, double price, OfferType type){
        this.name=name;
        this.createDate=createDate;
        this.price=price;
        this.type=type;
        this.offerID=offerCounterForID;
        offerCounterForID++;
    }
    public int getOfferID(){
        return offerID;
    }
    public int getOfferCounterForID(){
        return offerCounterForID;
    }

    public String getName() {
        return name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public double getPrice() {
        return price;
    }

    public OfferType getType() {
        return type;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(OfferType type) {
        this.type = type;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean checkOfferValues(){
        return true;
    }
}
