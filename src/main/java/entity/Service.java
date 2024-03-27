package entity;

import java.util.List;

public class Service {

   private int id; // Unique identifier for the service
   private String location; // Location where the service is offered
   private String type; // Category of the service (e.g., Cleaning, Repair, etc.)
   private String status; // Current state of the service (e.g., Available, Discontinued, On Hold)
   private String name; // Descriptive name of the service
   private double price; // Cost of the service
   private String phone; // Contact phone number for the service provider (optional)
   private String image; // URL or path to an image representing the service (optional)
   private ServiceProvider owner; // Reference to the provider offering the service
   

   public Service() {
      // Constructor without parameters, can be used for initialization
   }

   public Service(int id, String location, String type, String status, String name, double price,ServiceProvider owner) {
      this.id = id;
      this.location = location;
      this.type = type;
      this.status = status;
      this.name = name;
      this.price = price;
      this.owner = owner;
   }

   // Getters and setters for all attributes
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

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
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

   public ServiceProvider getOwner() {
      return owner;
   }

   public void setOwner(ServiceProvider owner) {
      this.owner = owner;
   }


   @Override
   public String toString() {
      return "Service{" +
              "id=" + id +
              ", location='" + location + '\'' +
              ", type='" + type + '\'' +
              ", status='" + status + '\'' +
              ", name='" + name + '\'' +
              ", price=" + price +
              '}';
   }
}
