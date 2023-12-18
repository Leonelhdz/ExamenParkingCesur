package com.example.parkingcesur;

import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import models.Cliente;
import models.Coche;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

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


    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();
    private ObservableList<Coche> coches = FXCollections.observableArrayList();


    @javafx.fxml.FXML
    public void initialize() {
        //TODO LISTA DE CLIENTES
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

        //TODO AGREGAMOS DATOS A EL COMBO MODELO
        ObservableList<String> datosModelo = FXCollections.observableArrayList();
        datosModelo.addAll("Toyota Camry", "Ford Mustang", "Honda Civic", "Chevrolet Corvette", "BMW 3 Series",
                "Mercedes-Benz S-Class", "Nissan Altima", "Audi Q5", "Volkswagen Golf", "Tesla Model S");
        comboModelo.setItems(datosModelo);
        comboModelo.getSelectionModel().selectFirst();

        //TODO DATOS A LA TABLA
        if (Session.getCoches().isEmpty()){
            ArrayList<Coche> coche = new ArrayList<>();
            coche.add(new Coche("sjshshhs", "fddfd", LocalDate.of(2023,2,15), LocalDate.of(2023,2,16), "Carlos", 8.0, 5.0));
            coche.add(new Coche("sjshshhs", "fddfd", LocalDate.of(2023,2,14), LocalDate.of(2023,2,15), "Carmen", 6.0, 5.0));
            coche.add(new Coche("sjshshhs", "fddfd", LocalDate.of(2023,2,13), LocalDate.of(2023,2,14), "Pedro", 2.0, 5.0));
            coche.add(new Coche("sjshshhs", "fddfd", LocalDate.of(2023,2,12), LocalDate.of(2023,2,13), "Paco", 8.0, 5.0));
            coche.add(new Coche("sjshshhs", "fddfd", LocalDate.of(2023,2,11), LocalDate.of(2023,2,12), "Raul", 2.0, 5.0));
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
            String cliente = fila.getValue().getModelo();
            return new SimpleStringProperty(cliente);
        });

        cTarifa.setCellValueFactory(fila ->
                new SimpleStringProperty(Double.toString(fila.getValue().getTarifa())));

        cCoste.setCellValueFactory(fila ->
                new SimpleStringProperty(Double.toString(fila.getValue().getCoste())));

        tableParking.getSelectionModel().selectedItemProperty();
        tableParking.setItems(coches);

    }

    @javafx.fxml.FXML
    public void añadir(ActionEvent actionEvent) {
        // Obtener datos de los controles de la interfaz de usuario
        String matricula = txtMatricula.getText();
        String modelo = comboModelo.getValue().toString();
        LocalDate fechaEntrada = dateEntrada.getValue();
        LocalDate fechaSalida = dateSalida.getValue();
        Cliente cliente = (Cliente) comboCliente.getValue();
        double tarifa = /* lógica para obtener la tarifa */;
        double coste = /* lógica para obtener el coste */;

        // Crear un nuevo Coche
        Coche nuevoCoche = new Coche(matricula, modelo, fechaEntrada, fechaSalida, cliente.getNombre(), tarifa, coste);

        // Agregar el nuevo coche a la lista
        coches.add(nuevoCoche);
    }


    @javafx.fxml.FXML
    public void salir(ActionEvent actionEvent) {
        System.exit(0);
    }
}