package com.example.parkingcesur;

import models.Cliente;
import models.Coche;

import java.util.ArrayList;

public class Session {

    public static Cliente getClienteActual() {
        return clienteActual;
    }

    public static void setClienteActual(Cliente clienteActual) {
        Session.clienteActual = clienteActual;
    }

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public static void setClientes(ArrayList<Cliente> clientes) {
        Session.clientes = clientes;
    }

    public static Coche getCocheActual() {
        return cocheActual;
    }

    public static void setCocheActual(Coche cocheActual) {
        Session.cocheActual = cocheActual;
    }

    public static ArrayList<Coche> getCoches() {
        return coches;
    }

    public static void setCoches(ArrayList<Coche> coches) {
        Session.coches = coches;
    }

    private static Cliente clienteActual = null;
    private static ArrayList<Cliente> clientes = new ArrayList<>(0);

    private static Coche cocheActual= null;
    private static ArrayList<Coche> coches = new ArrayList<>(0);


}
