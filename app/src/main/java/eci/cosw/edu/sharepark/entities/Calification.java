package eci.cosw.edu.sharepark.entities;


import java.io.Serializable;

/**
 * Created by alejandra on 20/10/16.
 */

public class Calification implements Serializable{
    public Calification() {

    }
    private Integer id=null;
    private Integer stars=null;
    private Integer giver_id=null;
    private Integer taker_id=null;


    public Integer getGiver_id() {
        return giver_id;
    }

    public void setGiver_id(Integer giver_id) {
        this.giver_id = giver_id;
    }


    public Integer getTaker_id() {
        return taker_id;
    }

    public void setTaker_id(Integer taker_id) {
        this.taker_id = taker_id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }
}
