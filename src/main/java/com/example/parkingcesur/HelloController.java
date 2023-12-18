package com.example.parkingcesur;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import models.Cliente;
import models.Coche;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class HelloController
{
    @javafx.fxml.FXML
    private TextField txtMatricula;
    @javafx.fxml.FXML
    private ChoiceBox comboCliente;
    @javafx.fxml.FXML
    private DatePicker dateEntrada;
    @javafx.fxml.FXML
    private DatePicker dateSalida;
    @javafx.fxml.FXML
    private ComboBox comboModelo;
    @javafx.fxml.FXML
    private Button btnAñadir;
    @javafx.fxml.FXML
    private Button btnSalir;
    @javafx.fxml.FXML
    private TableView<Coche> tableParking;
    @javafx.fxml.FXML
    private TableColumn<Coche, String> cMatricula;
    @javafx.fxml.FXML
    private TableColumn<Coche, String> cModelo;
    @javafx.fxml.FXML
    private TableColumn<Coche, String> cFechaEntrada;
    @javafx.fxml.FXML
    private TableColumn<Coche, String> cFechaSalida;
    @javafx.fxml.FXML
    private TableColumn<Coche, String> cCliente;
    @javafx.fxml.FXML
    private TableColumn<Coche, String> cTarifa;
    @javafx.fxml.FXML
    private TableColumn<Coche, String> cCoste;
    @javafx.fxml.FXML
    private RadioButton radioStandar;
    @javafx.fxml.FXML
    private RadioButton radioOferta;
    @javafx.fxml.FXML
    private RadioButton radioLargaDuracion;
    @javafx.fxml.FXML
    private ToggleGroup tipoTarifa;
    @javafx.fxml.FXML
    private Label labelInfo;
    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();
    private ObservableList<Coche> coches = FXCollections.observableArrayList();

    //las variables con los precios
    double precioStandar = 8.0;
    double precioOferta = 6.0;
    double precioLargaDuracion = 2.0;
    @javafx.fxml.FXML
    private Label labelCoste;


    @javafx.fxml.FXML
    public void initialize() {
        // LISTA DE CLIENTES
        if(Session.getClientes().isEmpty()){
            ArrayList<Cliente> client = new ArrayList<>();
            client.add(new Cliente(1, "Carlos", "carlos@cesur.com"));
            client.add(new Cliente(2, "Paco", "paco@cesur.com"));
            client.add(new Cliente(3, "Pedro", "pedro@cesur.com"));
            client.add(new Cliente(4, "Raul", "raul@cesur.com"));
            client.add(new Cliente(5, "Carmen", "carmen@cesur.com"));
            Session.setClientes(client);
        }
        //agregamos los clientes
        clientes.addAll(Session.getClientes());
        comboCliente.setConverter(new StringConverter<Cliente>(){
            @Override
            public String toString(Cliente cliente) {
                if(cliente!=null) return cliente.getNombre();
                else return null;
            }

            @Override
            public Cliente fromString(String s) {
                return null;
            }
        });
        comboCliente.setItems(clientes);

        // AGREGAMOS DATOS A EL COMBO MODELO
        ObservableList<String> datosModelo = FXCollections.observableArrayList();
        datosModelo.addAll("Toyota Camry", "Ford Mustang", "Honda Civic", "Chevrolet Corvette", "BMW 3 Series",
                "Mercedes-Benz S-Class", "Nissan Altima", "Audi Q5", "Volkswagen Golf", "Tesla Model S");
        comboModelo.setItems(datosModelo);
        comboModelo.getSelectionModel().selectFirst();

        // DATOS A LA TABLA
        if (Session.getCoches().isEmpty()){
            ArrayList<Coche> coche = new ArrayList<>();
            coche.add(new Coche("ABC123", "Toyota Camry", LocalDate.of(2023, 2, 15), LocalDate.of(2023, 2, 16), "Carlos", 8.0, 5.0));
            coche.add(new Coche("DEF456", "Ford Mustang", LocalDate.of(2023, 2, 14), LocalDate.of(2023, 2, 15), "Carmen", 6.0, 5.0));
            coche.add(new Coche("GHI789", "Honda Civic", LocalDate.of(2023, 2, 13), LocalDate.of(2023, 2, 14), "Pedro", 2.0, 5.0));
            coche.add(new Coche("JKL012", "Chevrolet Corvette", LocalDate.of(2023, 2, 12), LocalDate.of(2023, 2, 13), "Paco", 8.0, 5.0));
            coche.add(new Coche("MNO345", "BMW 3 Series", LocalDate.of(2023, 2, 11), LocalDate.of(2023, 2, 12), "Raul", 2.0, 5.0));
            Session.setCoches(coche);
        }

        coches.addAll(Session.getCoches());

        cMatricula.setCellValueFactory((fila)-> {
            String matricula = fila.getValue().getMatricula();
            return new SimpleStringProperty(matricula);
        });

        cModelo.setCellValueFactory((fila)-> {
            String modelo = fila.getValue().getModelo();
            return new SimpleStringProperty(modelo);
        });

        cFechaEntrada.setCellValueFactory(fila ->
                new SimpleStringProperty(fila.getValue().getFechaEntrada().toString()));

        cFechaSalida.setCellValueFactory(fila ->
                new SimpleStringProperty(fila.getValue().getFechaSalida().toString()));

        cCliente.setCellValueFactory((fila)-> {
            String cliente = fila.getValue().getCliente();
            return new SimpleStringProperty(cliente);
        });

        cTarifa.setCellValueFactory(fila ->
                new SimpleStringProperty(Double.toString(fila.getValue().getTarifa())));

        cCoste.setCellValueFactory(fila ->
                new SimpleStringProperty(Double.toString(fila.getValue().getCoste())));

        tableParking.getSelectionModel().selectedItemProperty();
        tableParking.setItems(coches);

        // AGRUPAR LOS RADIOBUTTON
        tipoTarifa = new ToggleGroup();
        radioStandar.setToggleGroup(tipoTarifa);
        radioOferta.setToggleGroup(tipoTarifa);
        radioLargaDuracion.setToggleGroup(tipoTarifa);


    }

    @javafx.fxml.FXML
    public void añadir(ActionEvent actionEvent) {

        String matricula = txtMatricula.getText();
        Cliente cliente = (Cliente) comboCliente.getValue();
        LocalDate fechaEntrada = dateEntrada.getValue();
        LocalDate fechaSalida = dateSalida.getValue();
        String modelo = (String) comboModelo.getValue();

        if(txtMatricula.getText().isEmpty() || comboCliente.getValue() == null ||
        dateEntrada.getValue() == null || dateSalida.getValue() == null ||
        comboModelo.getValue() == null || tipoTarifa.getSelectedToggle() == null){

            // Mostrar un Alert indicando que algunos campos están vacíos
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, complete todos los campos antes de añadir un coche.");
            alert.showAndWait();
            return;
        }

        //obtener la tarifa seleccionada
        RadioButton radioseleccionado = (RadioButton) tipoTarifa.getSelectedToggle();
        double tarifa = 0.0;

        //calcular el coste según los dias y tarifa seleccionada
        if(radioseleccionado != null) {
            if(radioseleccionado.equals(radioStandar)){
                tarifa = precioStandar;
            } else if (radioseleccionado.equals(radioOferta)) {
                tarifa = precioOferta;
            } else if (radioseleccionado.equals(radioLargaDuracion)){
                tarifa = precioLargaDuracion;
            }

            int dias = (int) fechaEntrada.until(fechaSalida, ChronoUnit.DAYS);
            double costo = tarifa * dias;

            Coche nuevoCoche = new Coche(matricula, modelo, fechaEntrada, fechaSalida, cliente.getNombre(), tarifa, costo);
            coches.add(nuevoCoche);
            Session.getCoches().add(nuevoCoche);

            tableParking.setItems(coches);
            labelCoste.setText("Total: " + costo + " €");
            limpiar();
        }
    }

    private void limpiar() {
        txtMatricula.clear();
        comboCliente.getSelectionModel().clearSelection();
        dateEntrada.setValue(null);
        dateSalida.setValue(null);
        comboModelo.getSelectionModel().selectFirst();
        tipoTarifa.selectToggle(null);
    }

    @javafx.fxml.FXML
    public void salir(ActionEvent actionEvent) {
        System.exit(0);
    }

    @javafx.fxml.FXML
    public void info(Event event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText("Realizado por: ");
        alert.setContentText("Francisco Leonel Soriano Hernandez\n 2º DAM");
        alert.showAndWait();
    }
}