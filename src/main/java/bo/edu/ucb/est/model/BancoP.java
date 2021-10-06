/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bernardo
 */
public class BancoP {
    private String nombre;
    private List<ClienteP> clientes;
    
    public BancoP(String nombre) {
        this.nombre = nombre;
        this.clientes = new ArrayList();
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public List<ClienteP> getClientes() {
        return this.clientes;
    }
    
    public void agregarCliente(ClienteP cliente) {
        clientes.add(cliente);
    }
    
    
    public ClienteP buscarClientePorPin(String pin) {
        for ( int i = 0; i < clientes.size(); i++) {
            ClienteP cli = clientes.get(i); // Sacando elemento por elemento
            if (cli.getPinSeguridad().equals(pin)) {
                return cli;
            }
        }
        return null; //TODO Cambiar la funcionalidad por Optional para evitar NullPointerException
    }
    
    public ClienteP buscarClientePorNombre(String nombre) {
        for ( int i = 0; i < clientes.size(); i++) {
            ClienteP cli = clientes.get(i); // Sacando elemento por elemento
            if (cli.getNombre().equals(nombre)) {
                return cli;
            }
        }
        return null; //TODO Cambiar la funcionalidad por Optional para evitar NullPointerException
    }
}
