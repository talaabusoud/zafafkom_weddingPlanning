package entity;

import java.util.List;

public class Service {
   private int id;
   private String location;
   private String type;
   private String name;
   private double price;
   private String phone;
   private String image;
   private List<String> services;
   private ServiceProvider owner;
   private List<Reserve> reserves;
   private String photo;


   public Service() {
      // constructor without parameters
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getLocation() {
      return location;
   }

   public void setLocation(String location) {
      this.location = location;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public List<String> getServices() {
      return services;
   }

   public void setServices(List<String> services) {
      this.services = services;
   }

   public ServiceProvider getOwner() {
      return owner;
   }

   public void setOwner(ServiceProvider owner) {
      this.owner = owner;
   }

   public List<Reserve> getReservations() {
      return reserves;
   }

   public void setReserves(List<Reserve> reserves) {
      this.reserves = reserves;
   }

   public String getPhoto() {
      return photo;
   }

   public void setPhoto(String photo) {
      this.photo = photo;
   }


}
