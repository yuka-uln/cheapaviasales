package org.teamyeah.cheapaviasales;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class FlightModel {

    private ListProperty<Flight> flights;

    public FlightModel() {
        FlightModel model = this;
        flights = new SimpleListProperty<>();
        flights.addListener(new ListChangeListener<Flight>() {
            @Override
            public void onChanged(Change<? extends Flight> c) {
                while (c.next()) {
                    if (c.wasAdded()) {
                        c.getAddedSubList().stream().forEach(item -> item.setModel(model));
                    } else if (c.wasRemoved()) {
                        c.getRemoved().stream().forEach(item -> item.setModel(null));
                    }
                }
            }
        });
    }

    public ObservableList<Flight> getFlights() {
        return flights.get();
    }

    public ListProperty<Flight> flightsProperty() {
        return flights;
    }

    public void setFlights(ObservableList<Flight> flights) {
        this.flights.set(flights);
    }
}
