package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import model.Airlines;
import model.SearchAndOrder;

import java.util.ArrayList;

public class AirlinesController {

    private int flights = 15;
    private final static int COLUMNS = 6;

    private ArrayList<Airlines> airlines = new ArrayList<>();
    private Airlines airline;

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
    private TextField searchField;

    @FXML
    void createFlights(ActionEvent event) {
        if(searchField.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ingrese el numero de vuelos en 'Busqueda...'");
            alert.showAndWait();
        }else{
            flights = Integer.valueOf(searchField.getText());
        airlines.clear();
        for (int i = 0; i < flights; i++) {
            airline = new Airlines();
            airlines.add(airline);
        }
        generateFlights();}
    }


    GridPane flightsGrid;

    private void generateFlights() {
        flightsGrid = new GridPane();

        flightsGrid.getRowConstraints().clear();
        flightsGrid.getColumnConstraints().clear();
        flightsGrid.setGridLinesVisible(false);
        flightsGrid.setGridLinesVisible(true);
        flightsGrid.setAlignment(Pos.CENTER);
        flightsScroll.setContent(flightsGrid);

        for (int row = 0; row < flights; row++) {
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
                        label.setText(String.valueOf(al.getDate()));
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

    private void generateFound(Airlines al) {
        flightsGrid.getChildren().clear();
        for (int i = 0; i < COLUMNS; i++) {
            Label label = new Label();
            switch (i) {
                case 0:
                    label.setText(String.valueOf(al.getDate()));
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
            flightsGrid.add(label, i, 0);
        }
    }

    @FXML
    void searchFlight(ActionEvent event) {
        SearchAndOrder order = new SearchAndOrder();
        String newValue = orderingCB.getSelectionModel().getSelectedItem().toString();
        String searchText = searchField.getText();
        Alert alert = new Alert(Alert.AlertType.ERROR, "Ningun vuelo encontrado segun el criterio usado");
        switch (newValue) {
            case "Fecha":
                long processingTime = System.currentTimeMillis();
                int found = order.linearSearchDate(searchText, airlines);
                timeLabel.setText("Tiempo: " + (System.currentTimeMillis() - processingTime) + "ms");
                if (found != -1) {
                    Airlines al = airlines.get(found);
                    generateFound(al);
                } else {
                    alert.showAndWait();
                }
                break;
            case "Hora":
                processingTime = System.currentTimeMillis();
                found = order.linearSearchDate(searchText, airlines);
                timeLabel.setText("Tiempo: " + (System.currentTimeMillis() - processingTime) + "ms");
                if (found != -1) {
                    Airlines al = airlines.get(found);
                    generateFound(al);
                } else {
                    alert.showAndWait();
                }
                break;
            case "Aerolinea":
                processingTime = System.currentTimeMillis();
                found = order.linearSearchDate(searchText, airlines);
                timeLabel.setText("Tiempo: " + (System.currentTimeMillis() - processingTime) + "ms");
                if (found != -1) {
                    Airlines al = airlines.get(found);
                    generateFound(al);
                } else {
                    alert.showAndWait();
                }
                break;
            case "Vuelo":
                processingTime = System.currentTimeMillis();
                found = order.linearSearchDate(searchText, airlines);
                timeLabel.setText("Tiempo: " + (System.currentTimeMillis() - processingTime) + "ms");
                if (found != -1) {
                    Airlines al = airlines.get(found);
                    generateFound(al);
                } else {
                    alert.showAndWait();
                }
                break;
            case "Destino":
                processingTime = System.currentTimeMillis();
                found = order.linearSearchDate(searchText, airlines);
                timeLabel.setText("Tiempo: " + (System.currentTimeMillis() - processingTime) + "ms");
                if (found != -1) {
                    Airlines al = airlines.get(found);
                    generateFound(al);
                } else {
                    alert.showAndWait();
                }
                break;
            case "Puerta":
                processingTime = System.currentTimeMillis();
                found = order.binarySearchGate(searchText, airlines);
                timeLabel.setText("Tiempo: " + (System.currentTimeMillis() - processingTime) + "ms");
                if (found != -1) {
                    Airlines al = airlines.get(found);
                    generateFound(al);
                } else {
                    alert.showAndWait();
                }
                break;
        }
    }
}