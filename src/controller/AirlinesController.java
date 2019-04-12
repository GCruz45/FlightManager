package controller;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import model.Airlines;
import model.SearchAndOrder;

import java.util.ArrayList;

import static java.lang.System.currentTimeMillis;

public class AirlinesController {

    private final static int FLIGHTS = 15;
    private final static int COLUMNS = 6;

    ArrayList<Airlines> airlines = new ArrayList<>();
    Airlines airline;

    public AirlinesController() {
        for (int i = 0; i < FLIGHTS; i++) {
            airline = new Airlines();
            airlines.add(airline);
        }
    }

    @FXML
    void initialize() {
        orderingCB.getItems().addAll("Fecha", "Hora", "Aerolinea", "Vuelo", "Destino", "Puerta");
    }

    @FXML
    private ScrollPane flightsScroll;

    @FXML
    private ComboBox orderingCB;

    @FXML
    private Label timeLabel;

    @FXML
    void createFlights(ActionEvent event) {
        airlines.clear();
        for (int i = 0; i < FLIGHTS; i++) {
            airline = new Airlines();
            airlines.add(airline);
        }
        generateFlights();
    }

    private void generateFlights() {
        GridPane flightsGrid = new GridPane();

        flightsGrid.getRowConstraints().clear();
        flightsGrid.getColumnConstraints().clear();
        flightsGrid.setGridLinesVisible(false);
        flightsGrid.setGridLinesVisible(true);
        flightsGrid.setAlignment(Pos.CENTER);
        flightsScroll.setContent(flightsGrid);

        for (int row = 0; row < FLIGHTS; row++) {
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            rc.setMaxHeight(50.0);
            rc.setPrefHeight(20);
            rc.setMinHeight(30.0);
            flightsGrid.getRowConstraints().add(rc);
        }
        for (int col = 0; col < COLUMNS; col++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            cc.setMaxWidth(flightsScroll.getWidth() / COLUMNS - 5);
            cc.setPrefWidth(flightsScroll.getWidth() / COLUMNS - 5);
            cc.setMinWidth(30.0);
            flightsGrid.getColumnConstraints().add(cc);
        }

        int row = 0;
        for (Airlines al : airlines
        ) {
            for (int i = 0; i < COLUMNS; i++) {
                Label label = new Label();
                switch (i) {
                    case 0:
                        label.setText(al.transformDate(al.getDate()));
                        break;
                    case 1:
                        label.setText(al.transformDepartureTime(al.getDepartureTime()));
                        break;
                    case 2:
                        label.setText(al.getAirline());
                        break;
                    case 3:
                        label.setText(al.getFlightNum());
                        break;
                    case 4:
                        label.setText(al.getDestination());
                        break;
                    case 5:
                        label.setText(String.valueOf(al.getGate()));
                        break;
                }
                GridPane.setHalignment(label, HPos.CENTER);
                flightsGrid.add(label, i, row);
            }
            row++;
        }
    }

    @FXML
    void orderFlights(ActionEvent event) {
        SearchAndOrder order = new SearchAndOrder();
        String newValue = orderingCB.getSelectionModel().getSelectedItem().toString();
        switch (newValue) {
            case "Fecha":
                long processingTime = System.currentTimeMillis();
                airlines = order.bubbleSortDate(airlines);
                timeLabel.setText("Tiempo: " + (System.currentTimeMillis() - processingTime) + "ms");
                break;
            case "Hora":
                processingTime = System.currentTimeMillis();
                airlines = order.bubbleSortDepartureTime(airlines);
                timeLabel.setText("Tiempo: " + (System.currentTimeMillis() - processingTime) + "ms");
                break;
            case "Aerolinea":
                processingTime = System.currentTimeMillis();
                airlines = order.insertionSortAirline(airlines);
                timeLabel.setText("Tiempo: " + (System.currentTimeMillis() - processingTime) + "ms");
                break;
            case "Vuelo":
                processingTime = System.currentTimeMillis();
                airlines = order.insertionSortFlightNumber(airlines);
                timeLabel.setText("Tiempo: " + (System.currentTimeMillis() - processingTime) + "ms");
                break;
            case "Destino":
                processingTime = System.currentTimeMillis();
                airlines = order.selectionSortDestination(airlines);
                timeLabel.setText("Tiempo: " + (System.currentTimeMillis() - processingTime) + "ms");
                break;
            case "Puerta":
                processingTime = System.currentTimeMillis();
                airlines = order.selectionSortGate(airlines);
                timeLabel.setText("Tiempo: " + (System.currentTimeMillis() - processingTime) + "ms");
                break;
        }
        generateFlights();
    }

    @FXML
    void searchFlight(ActionEvent event) {
        SearchAndOrder order = new SearchAndOrder();
        String newValue = orderingCB.getSelectionModel().getSelectedItem().toString();
        switch (newValue) {
            case "Fecha":
                airlines = order.bubbleSortDate(airlines);
                break;
            case "Hora":
                airlines = order.bubbleSortDepartureTime(airlines);
                break;
            case "Aerolinea":
                airlines = order.insertionSortAirline(airlines);
                break;
            case "Vuelo":
                airlines = order.insertionSortFlightNumber(airlines);
                break;
            case "Destino":
                airlines = order.selectionSortDestination(airlines);
                break;
            case "Puerta":
                airlines = order.selectionSortGate(airlines);
                break;
        }
    }

}
