package org.teamyeah.cheapaviasales;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

/**
 * Represents a flight with given origin, destination, departure date.
 */
public class Flight {
    private StringProperty origin = new SimpleStringProperty();
    private StringProperty destination = new SimpleStringProperty();
    private ObjectProperty<LocalDate> departureDate = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> returnDate = new SimpleObjectProperty<>();
    private FlightModel model;

    private StringProperty currentPrice = new SimpleStringProperty();

    public Flight() {
        this("", "", LocalDate.now(), null, "н/д");
    }

    public Flight(String origin, String destination, LocalDate departureDate, LocalDate returnDate, String currentPrice) {
        this.origin.setValue(origin);
        this.destination.setValue(destination);
        this.departureDate.setValue(departureDate);
        this.returnDate.setValue(returnDate);
        this.currentPrice.setValue(currentPrice);
    }

    public String getCurrentPrice() {
        return currentPrice.get();
    }

    public StringProperty currentPriceProperty() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice.set(currentPrice);
    }

    public LocalDate getDepartureDate() {
        return departureDate.get();
    }

    public ObjectProperty<LocalDate> departureDateProperty() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate.set(departureDate);
    }

    public String getDestination() {
        return destination.get();
    }

    public StringProperty destinationProperty() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination.set(destination);
    }

    public String getOrigin() {
        return origin.get();
    }

    public StringProperty originProperty() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin.set(origin);
    }

    public LocalDate getReturnDate() {
        return returnDate.get();
    }

    public ObjectProperty<LocalDate> returnDateProperty() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate.set(returnDate);
    }

    public FlightModel getModel() {
        return model;
    }

    public void setModel(FlightModel model) {
        this.model = model;
    }
}
