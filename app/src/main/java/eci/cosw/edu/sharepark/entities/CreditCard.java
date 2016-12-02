package eci.cosw.edu.sharepark.entities;


import java.io.Serializable;

/**
 * Created by Andrés Felipe on 12/09/2016.
 */

public class CreditCard implements Serializable{
    public CreditCard() {
    }
    private Integer cardNumber;
    private String expirationDate;
    private Integer cvcCode;
    private Integer owner_id;

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }


    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }
    

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    

    public Integer getCvcCode() {
        return cvcCode;
    }

    public void setCvcCode(Integer cvcCode) {
        this.cvcCode = cvcCode;
    }
}
