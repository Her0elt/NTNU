package Øving_del_2;

import java.time.LocalDate;

abstract class Animal {
    private final String norName;
    private final String latName;
    private final String latFamily;
    private final LocalDate arrivalDate;
    private String address;
    public Animal(String norName,
                  String latName,
                  String latFamily,
                  LocalDate arrivalDate,
                  String address) {

        this.norName = norName;
        this.latName = latName;
        this.latFamily = latFamily;
        this.arrivalDate = arrivalDate;
        this.address = address;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getNorName() {
        return norName;
    }
    public String getLatName() {
        return latName;
    }
    public String getLatFamily() {
        return latFamily;
    }
    public LocalDate getArrivalDate() {
        return arrivalDate;
    }
    public String toString() {
        return "Norsk navn: " + norName +
                "\nLatinsk navn og familie: " +
                latName + ", " + latFamily + "\nAdresse: "
                + address;
    }
}

