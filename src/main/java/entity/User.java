package entity;

import java.util.List;

public class User {

    private int id;
    private String name;
    private String PhoneNumber;

    private String address;
    private String city;
    private String street;
    private String email;
    private String password;

    boolean hasServiceWindow;
    private List<String> service;

    public User() {
        //construct without parameter
    }

    public List<String> getService() {
        return service;
    }

    public void setService(List<String> service) {
        if(service.isEmpty()){
            this.service = service;
        }else{
            setHasServiceWindow(true);
        }
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isHasServiceWindow() {
        return hasServiceWindow;
    }

    public void setHasServiceWindow(boolean hasServiceWindow) {
        this.hasServiceWindow = hasServiceWindow;
    }

}
