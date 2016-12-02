package eci.cosw.edu.sharepark.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrés Felipe on 12/09/2016.
 */

public class User implements Serializable{
    public User() {
    }

    private String firstName=null;
    private String lastName=null;
    private Integer id=null;
    private String email=null;
    private String address=null;
    private Integer phone=null;
    //Por ahora, la contraseña del usuario sera una propiedad de si mismo:
    private String password=null;
    private List<CreditCard> creditCards=new ArrayList<>();
    private List<Calification> calificationsGive=new ArrayList<>();
    private List<Calification> calificationsTake=new ArrayList<>();
    private List<Vehicle> vehicles=new ArrayList<>();
    private List<Request> requestsGive=new ArrayList<>();
    private List<Request> requestsTake=new ArrayList<>();

    public List<Parking> getParkings() {
        return parkings;
    }

    public void setParkings(List<Parking> parkings) {
        this.parkings = parkings;
    }

    private List<Parking> parkings=new ArrayList<>();



    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    

    public String getLastName() {
        return lastName;
    }


    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }


    public List<Calification> getCalificationsGive() {
        return calificationsGive;
    }

    public void setCalificationsGive(List<Calification> calificationsGive) {
        this.calificationsGive = calificationsGive;
    }



    public List<Calification> getCalificationsTake() {
        return calificationsTake;
    }

    public void setCalificationsTake(List<Calification> calificationsTake) {
        this.calificationsTake = calificationsTake;
    }



    public List<Request> getRequestsGive() {
        return requestsGive;
    }

    public void setRequestsGive(List<Request> requestsGive) {
        this.requestsGive = requestsGive;
    }


    public List<Request> getRequestsTake() {
        return requestsTake;
    }

    public void setRequestsTake(List<Request> requestsTake) {
        this.requestsTake = requestsTake;
    }

    public void addCreditCard(CreditCard cr){
        this.creditCards.add(cr);
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

}
