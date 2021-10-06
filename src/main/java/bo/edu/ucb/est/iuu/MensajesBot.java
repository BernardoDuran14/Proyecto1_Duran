/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est.iuu;

/**
 *
 * @author Bernardo
 */
public class MensajesBot {
    
    
    public String msjInicial() {
        String text = "Bienvenido al Banco de la Fortuna\n\nHe notado que aún no eres Cliente, procedamos a registrarte\n\n¿Cúal es tu primer nombre?";
        return text;
    }
    
    public String PrimerPin() {
        String text = "Por favor elige un PIN de seguridad, este te será requerido cada que ingreses al sistema.";
        return text;
    }
    
    public String NombreReal() {
        String text = "Ingrese su nombre Real";
        return text;
    }
    
    public String CorrectRegister() {
        String text = "Te hemos registrado correctamente.";
        return text;
    }
    
    public String HelloAgain(String nombre) {
        String text = "Hola de nuevo "+nombre+"\n\nSolo por seguridad ¿cuál es tu PIN?";
        return text;
    }
    
    public String PinMal(String nombre) {
        String text = "Lo siento, el código es incorrecto.\n\nHola de nuevo "+nombre+"\n\nSolo por seguridad ¿cuál es tu PIN?";
        return text;
    }
    
    public String Bienvenido() {
        String text = "Bienvenido\n\nElige una opción:\n" +
                                       "\n" +
                      "1. Ver Saldo\n" +
                      "2. Retirar dinero.\n" +
                      "3. Depositar dinero.\n" +
                      "4. Crear cuenta\n" +
                      "5. Salir";
        return text;
    }
    public String SinCuentas() {
        String text = "Usted no tiene cuentas, cree una primero\n\nBienvenido\n\nElige una opción:\n" +
                                       "\n" +
                      "1. Ver Saldo\n" +
                      "2. Retirar dinero.\n" +
                      "3. Depositar dinero.\n" +
                      "4. Crear cuenta\n" +
                      "5. Salir";
        return text;
    }
    
    public String EjegirCuenta() {
        String text = "Elija la cuenta que desea ver el saldo";
        return text;
    }
    
    public String VerSaldo(String moneda, String nroCuenta, String tipo, double saldoInicial) {
        String text = "Cuenta: "+nroCuenta+"\nMoneda: "+moneda+"\nTipo caja: "+tipo+"\nSaldo: "+saldoInicial;
        return text;
    }
    
    public String Moneda() {
        String text ="Seleccione la moneda: \n1. Dolares\n2. Bolivianos";
        return text;
    }
    
    public String CuentaCreada(String tipo, double saldo, String nroCuenta){
        String text ="Se le ha creado una cuenta en " +tipo+ " con saldo " +saldo+", cuyo número es " +nroCuenta;
        return text;
    }
    
    public String Retirar() {
        String text = "Cuanto desea retirar?";
        return text;
    }
    public String Depositar() {
        String text = "Cuanto desea depositar?";
        return text;
    }
    
    public String RetiroGood(double saldo){
        String text = "Retiro exitoso tiene de saldo: "+saldo;
        return text;
    }
    
    public String RetiroMal(){
        String text = "Error en el retiro";
        return text;
    }
    
    public String DepositoGood(double saldo){
        String text = "Deposito exitoso tiene de saldo: "+saldo;
        return text;
    }
    
    public String DepositoMal(){
        String text = "Error en el deposito";
        return text;
    }
    
    public String FuncionFaltante(){
        String text = "Lo lamentamos, aun no tenemos la funcion de multiples cuentas."+"\n?";
        return text;
    }
}
