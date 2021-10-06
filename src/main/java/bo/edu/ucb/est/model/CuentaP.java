/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est.model;

/**
 *
 * @author Bernardo
 */
public class CuentaP {
    
    private String moneda;
    private String tipo;
    private String nroCuenta;
    private double saldo;

    
    public CuentaP(String moneda, String nroCuenta, String tipo, double saldoInicial) {
        this.moneda = moneda;
        this.tipo = tipo;
        this.saldo = saldoInicial;
        this.nroCuenta = nroCuenta;
    }

    public double retirar(double monto, double saldo) {
        double total = saldo - monto;     
        
        return total;
    }
    
    public double depositar(double monto, double saldo) {
        double total = saldo + monto;
        
        return total;
    }
            
    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }
    
}
