package eci.cosw.edu.sharepark.entities;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by alejandra on 27/09/16.
 */

public class Parking implements Serializable{
    public Parking() {
    }

    private Integer owner_id=null;
    private boolean covert=false;
    private boolean home=false;
    private Float height=null; 
    private Float width=null;
    private Float length=null;
    private boolean available=false;
    private Timestamp serviceStart=null;
    private Timestamp availableFinish=null;
    private Integer costMinute=null;
    private Float x=null;
    private Float y=null;
    private String address=null;
    private Integer stratum=null;
    private String picture=null;

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer ownerId) {
        this.owner_id = ownerId;
    }


    public boolean isCovert() {return covert;}

    public void setCovert(boolean covert) {this.covert = covert;}


    public boolean isHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }


    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


    public Timestamp getServiceStart() {
        return serviceStart;
    }

    public void setServiceStart(Timestamp serviceStart) {
        this.serviceStart = serviceStart;
    }


    public Timestamp getAvailableFinish() {
        return availableFinish;
    }

    public void setAvailableFinish(Timestamp availableFinish) {
        this.availableFinish = availableFinish;
    }


    public Integer getCostMinute() {
        return costMinute;
    }

    public void setCostMinute(Integer costMinute) {
        this.costMinute = costMinute;
    }


    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }


    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }


    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }


    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }


    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Integer getStratum() {
        return stratum;
    }

    public void setStratum(Integer stratum) {
        this.stratum = stratum;
    }


    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

   
}
