package com.project.railway.data.entity;

public class BookingData {
    private RouteList route;
    private Client client;
    private String deliveryType;
    private String spaceName;
    private String compartmentType;
    private double price;

    public BookingData(){
        route = new RouteList();
        client = new Client();
        deliveryType = "";
        spaceName = "";
        compartmentType = "";
        price = 0;
    }

    public RouteList getRoute() {
        return route;
    }

    public void setRoute(RouteList route) {
        this.route = route;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getCompartmentType() {
        return compartmentType;
    }

    public void setCompartmentType(String compartmentType) {
        this.compartmentType = compartmentType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setClientAddress(String address){
        this.client.setAddress(address);
    }

    public void setClientAdditionalAddress(String address){
        this.client.setAdditionalAddress(address);
    }

    public void setClientCity(String city){
        this.client.setCity(city);
    }

    public void setClientZip(String zip){
        this.client.setZip(zip);
    }

    public void setClientNameOnCard(String nameOnCard){
        this.client.setCreditCardName(nameOnCard);
    }

    public void setClientCreditCardNumber(String creditCardNumber){
        this.client.setCreditCardNumber(creditCardNumber);
    }

    public void setClientCreditCardExpirationMonth(String month){
        this.client.setCreditCardExpirationMonth(month);
    }

    public void setClientCreditCardExpirationYear(String year){
        this.client.setCreditCardExpirationYear(year);
    }

    public void setClientCreditCardCVV(String cvv){
        this.client.setCreditCardCVV(cvv);
    }

    @Override
    public String toString() {
        return "BookingData{" +
                "route=" + route +
                ", client=" + client +
                ", deliveryType='" + deliveryType + '\'' +
                ", spaceName='" + spaceName + '\'' +
                ", compartmentType='" + compartmentType + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
