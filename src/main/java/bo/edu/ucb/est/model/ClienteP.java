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
public class ClienteP {
    
    private String nombre;
    private String pinSeguridad;
    private List<CuentaP> cuentas;
    
    public ClienteP(){
        
    }
    public ClienteP(String nombre ,String pinSeguridad) {
        this.nombre = nombre;
        this.pinSeguridad = pinSeguridad;
        this.cuentas = new ArrayList();
    }

    

    /**
     * Este metodo permite agregar una cuenta a un cliente existente
     * @param cuenta objeto de tipo Cuenta que se le agrega al cliente.
     */
    public void agregarCuenta(CuentaP cuenta) {
        this.cuentas.add(cuenta);
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPinSeguridad() {
        return pinSeguridad;
    }

    public void setPinSeguridad(String pinSeguridad) {
        this.pinSeguridad = pinSeguridad;
    }

    public List<CuentaP> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<CuentaP> cuentas) {
        this.cuentas = cuentas;
    }
}
