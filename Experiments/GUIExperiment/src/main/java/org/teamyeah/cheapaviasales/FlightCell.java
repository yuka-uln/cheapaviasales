package org.teamyeah.cheapaviasales;

import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

class FlightCell extends ListCell<Flight> {

    TextField originField = new TextField();
    TextField destinationField = new TextField();
    DatePicker departureDatePicker = new DatePicker();
    DatePicker returnDatePicker = new DatePicker();
    Text priceText = new Text();

    Pane contentRoot;
    Flight flight;

    public FlightCell() {
        super();

//        originField.setEditable(false);
//        destinationField.setEditable(false);
//        departureDatePicker.setEditable(false);
//        returnDatePicker.setEditable(false);

        GridPane requestedInfoGrid = new GridPane();
        //requestedInfoGrid.setAlignment(Pos.CENTER);
        requestedInfoGrid.setHgap(5);
        requestedInfoGrid.setVgap(5);

        Label originLabel = new Label("Откуда:");
        GridPane.setHalignment(originLabel, HPos.RIGHT);
        requestedInfoGrid.add(originLabel, 1, 1);
        requestedInfoGrid.add(originField, 2, 1);

        Label destLabel = new Label("Куда:");
        GridPane.setHalignment(destLabel, HPos.RIGHT);
        requestedInfoGrid.add(destLabel, 1, 2);
        requestedInfoGrid.add(destinationField, 2, 2);

        Label deptDateLabel = new Label("Дата отправления:");
        GridPane.setHalignment(deptDateLabel, HPos.RIGHT);
        requestedInfoGrid.add(deptDateLabel, 3, 1);
        requestedInfoGrid.add(departureDatePicker, 4, 1);

        Label retDateLabel = new Label("Дата возвращения:");
        GridPane.setHalignment(retDateLabel, HPos.RIGHT);
        requestedInfoGrid.add(retDateLabel, 3, 2);
        requestedInfoGrid.add(returnDatePicker, 4, 2);

        GridPane foundInfoGrid = new GridPane();
        //foundInfoGrid.setAlignment(Pos.CENTER);
        foundInfoGrid.setHgap(5);
        foundInfoGrid.setVgap(5);

        Label priceLabel = new Label("Текущая цена:");
        GridPane.setHalignment(priceLabel, HPos.RIGHT);
        foundInfoGrid.add(priceLabel, 1, 1);
        foundInfoGrid.add(priceText, 2, 1);

        VBox dataBox = new VBox();
        dataBox.setSpacing(4);
        dataBox.getChildren().addAll(requestedInfoGrid, foundInfoGrid);

        HBox controlBox = new HBox();
        controlBox.setSpacing(4);
        //controlBox.setAlignment(Pos.CENTER);

        Button historyBtn = new Button("История");
        Button settingsBtn = new Button("Настройки");
        Button deleteBtn = new Button("Удалить");
        deleteBtn.setOnAction(event -> {
            if (flight != null && flight.getModel() != null) {
                flight.getModel().getFlights().remove(flight);
            }
        });

        controlBox.getChildren().addAll(historyBtn, settingsBtn, deleteBtn);

        BorderPane root = new BorderPane();
        //BorderPane.setAlignment(controlBox, Pos.CENTER);
        root.setCenter(dataBox);
        root.setBottom(controlBox);
        root.setBorder(new Border(new BorderStroke(
                Color.GRAY, BorderStrokeStyle.SOLID,
                new CornerRadii(4),
                new BorderWidths(1))));
        root.setPadding(new Insets(4));

        contentRoot = root;
        this.setAlignment(Pos.CENTER);
    }

    @Override
    protected void updateItem(Flight item, boolean empty) {
        super.updateItem(item, empty);
        setText("");

        if (flight != null) {
            originField.textProperty().unbindBidirectional(flight.originProperty());
            destinationField.textProperty().unbindBidirectional(flight.destinationProperty());
            departureDatePicker.valueProperty().unbindBidirectional(flight.departureDateProperty());
            returnDatePicker.valueProperty().unbindBidirectional(flight.returnDateProperty());
            priceText.textProperty().unbind();
        }

        if (empty) {
            setGraphic(null);
            originField.setText("");
            destinationField.setText("");
            departureDatePicker.setValue(null);
            returnDatePicker.setValue(null);
            priceText.setText("");
        } else {
            originField.textProperty().bindBidirectional(item.originProperty());
            destinationField.textProperty().bindBidirectional(item.destinationProperty());
            departureDatePicker.valueProperty().bindBidirectional(item.departureDateProperty());
            returnDatePicker.valueProperty().bindBidirectional(item.returnDateProperty());
            priceText.textProperty().bind(item.currentPriceProperty());
            setGraphic(contentRoot);
        }

        flight = item;
    }
}
