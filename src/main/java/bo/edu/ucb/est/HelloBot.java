/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est;

import bo.edu.ucb.est.iuu.MensajesBot;
import bo.edu.ucb.est.model.BancoP;
import bo.edu.ucb.est.model.ClienteP;
import bo.edu.ucb.est.model.CuentaP;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * @author Bernardo
 */
public class HelloBot extends TelegramLongPollingBot{
   
    private Map estadoUsuario = new HashMap();
    private Map MsjUsuario = new HashMap();
    public int estado;
    public String nombreCta ;
    public String pin, pinPrueba,moneda,tipo,nroCuenta="";
    public double saldo;

    @Override
    public String getBotToken() {
        return "2025102039:AAEWqnRvVXGHPZzpF0r0ftqpVx1RnulF3zI";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long userId = update.getMessage().getChatId();
        Integer estado = (Integer)estadoUsuario.get(userId);
        if(estado == null){
            estado = 0;
            estadoUsuario.put(userId, 0);
            
        }
        if(update.hasMessage()){
            BancoP fortuna = new BancoP("BANCO FORTUNA");
            String nombreChat = update.getMessage().getFrom().getFirstName().toString();
            SendMessage message = new SendMessage();
            MensajesBot mensaje = new MensajesBot();
            ClienteP cliente = new ClienteP(nombreChat,pin);
            CuentaP cuenta = new CuentaP(moneda, nroCuenta, tipo, saldo);
            message.setChatId(update.getMessage().getChatId().toString());
            String msjMostrar=null;
            String msjUsuario=null;
            switch(estado){
            case 0: if(nombreCta!=nombreChat){ 
                       msjMostrar = mensaje.msjInicial();//Envia el primer mensaje a usuario nuevo y pide el nombre
                       estadoUsuario.put(userId, 1);
                    }else if(cliente.getNombre() == nombreChat) {
                        msjMostrar= mensaje.HelloAgain(cliente.getNombre());  //Saluda a Usuario ya registrado  
                        estadoUsuario.put(userId, 4);
                    }
                    break;
            case 1: msjUsuario = update.getMessage().getText(); //usuario ingresa su nombre
                    
                    try {
                        nombreCta=msjUsuario;
                        MsjUsuario.put(userId, nombreCta);
                        if(nombreCta.equals(nombreChat)){//Si ingreso un nombre verdadero
                           msjMostrar = mensaje.PrimerPin(); //Pide primer pin para registro
                           
                           estadoUsuario.put(userId, 2);
                        }else{
                            msjMostrar = mensaje.NombreReal(); //Pide el nombre real de nuevo
                            
                            estadoUsuario.put(userId, 1);
                        }      
                    } catch (Exception e) {
                        
                        estadoUsuario.put(userId, 1);// 
                    }
                    break;
            case 2: msjUsuario = update.getMessage().getText();// Ingresa el pin nuevo
                    try {
                        pin = msjUsuario;
                        MsjUsuario.put(userId, pin);
                        msjMostrar = mensaje.CorrectRegister();// Registro correcto
                        fortuna.agregarCliente(cliente);//Agregar al cliente
                        
                        estadoUsuario.put(userId, 3);
                    } catch (Exception e) {
                        msjMostrar = mensaje.PrimerPin();
                        
                        estadoUsuario.put(userId, 2);
                    }
                    break;
            case 3: msjMostrar=mensaje.HelloAgain(cliente.getNombre());
                    
                    estadoUsuario.put(userId, 4);
                    break;
            case 4: msjUsuario = update.getMessage().getText();
                    try {
                        pinPrueba = msjUsuario;
                        MsjUsuario.put(userId, pinPrueba);
                        if(pin.equals(pinPrueba)){//Si los pines son iguales
                           msjMostrar = mensaje.Bienvenido(); //Dar bienvenida y menu de opciones
                           
                           estadoUsuario.put(userId, 5);
                        }else{
                            msjMostrar = mensaje.PinMal(nombreCta); // Mostrar mensaje de pin incorrecto y volver a pedir pin
                            
                            estadoUsuario.put(userId, 4);
                        }      
                    } catch (Exception e) {
                        
                        estadoUsuario.put(userId, 4);// 
                    }
                    break;
            case 5: msjUsuario = update.getMessage().getText();
                    try {
                        int opcion = Integer.parseInt(msjUsuario);
                        MsjUsuario.put(userId, opcion);
                        if(opcion == 1){
                            if(cuenta.getNroCuenta().equals("")){
                                msjMostrar = mensaje.SinCuentas(); //Decir que el usuario no tiene cuentas y que la cree
                                
                                estadoUsuario.put(userId, 5);
                            }else{
                                msjMostrar = mensaje.VerSaldo(moneda, nroCuenta, tipo, saldo); //Mostrar saldo 
                                estadoUsuario.put(userId, 3);
                                
                            }
                        }else if(opcion == 2){
                            msjMostrar = mensaje.Retirar();
                            estadoUsuario.put(userId, 8);
                            
                        }else if(opcion == 3){
                            msjMostrar = mensaje.Depositar();
                            
                            estadoUsuario.put(userId, 9);
                        }else if(opcion == 4){
                            if(cuenta.getNroCuenta().equals("")){
                                msjMostrar = mensaje.Moneda(); //Preguntar que moneda quiere que sea su cuenta nueva
                            
                                estadoUsuario.put(userId, 6);//Crear Cuenta
                            }else{
                                msjMostrar = mensaje.FuncionFaltante();
                                estadoUsuario.put(userId, 7);
                            }
                            
                        }else if(opcion == 5){
                            msjMostrar = mensaje.Bienvenido();
                            estadoUsuario.put(userId, 5);
                        }else{
                            msjMostrar = mensaje.Bienvenido();
                            estadoUsuario.put(userId, 5);
                        }
                        
                    } catch (Exception e) {
                        msjMostrar = mensaje.Bienvenido();
                        
                        estadoUsuario.put(userId, 5);
                    }
                    break;
            case 6: msjUsuario = update.getMessage().getText();
                    try {
                    int opcion = Integer.parseInt(msjUsuario);
                    MsjUsuario.put(userId, opcion);
                    if(opcion ==1) {
                        moneda="USD";
                        nroCuenta="100001";
                        tipo= "Cuenta Corriente";
                        saldo = 0;
                        msjMostrar = mensaje.CuentaCreada(moneda, saldo, nroCuenta);
                        cliente.agregarCuenta(cuenta);
                        
                        estadoUsuario.put(userId, 7);
                    }else if(opcion==2) {
                        moneda="BOB";
                        nroCuenta="100001";
                        tipo= "Caja de Ahorros";
                        saldo = 0;
                        msjMostrar = mensaje.CuentaCreada(moneda, saldo, nroCuenta);
                        cliente.agregarCuenta(cuenta);
                        
                        estadoUsuario.put(userId, 7);
                    }else{
                        msjMostrar = mensaje.Moneda();
                        
                        estadoUsuario.put(userId, 6);
                    }
                    } catch (Exception e) {
                        msjMostrar = mensaje.Moneda();
                        
                        estadoUsuario.put(userId, 6);
                    }
                    break;
            case 7: msjMostrar = mensaje.HelloAgain(nombreCta);
                    
                    estadoUsuario.put(userId, 5);
                    break;
            case 8: msjUsuario = update.getMessage().getText();
                    try {
                        double retirar = Double.parseDouble(msjUsuario);
                        MsjUsuario.put(userId, retirar);
                        double total = cuenta.retirar(retirar, cuenta.getSaldo());
                        if(retirar>=0 && retirar<=cuenta.getSaldo()){
                            saldo=total;
                            cuenta.setSaldo(saldo);
                            cliente.agregarCuenta(cuenta);
                            msjMostrar = mensaje.RetiroGood(total);
                            
                            
                            estadoUsuario.put(userId, 7);
                        }else{
                            msjMostrar = mensaje.RetiroMal();
                            
                            estadoUsuario.put(userId, 7);
                        }
                    } catch (Exception e) {
                        msjMostrar = mensaje.RetiroMal();
                        
                        estadoUsuario.put(userId, 7);
                    }
                    break;
            case 9: msjUsuario = update.getMessage().getText();
                    try {
                        double depositar = Double.parseDouble(msjUsuario);
                        MsjUsuario.put(userId, depositar);
                        double total = cuenta.depositar(depositar, cuenta.getSaldo());
                        if(depositar>0){
                            saldo=total;
                            cuenta.setSaldo(saldo);
                            cliente.agregarCuenta(cuenta);
                            msjMostrar = mensaje.RetiroGood(total);
                            
                            estadoUsuario.put(userId, 7);
                        }else{
                            msjMostrar = mensaje.DepositoMal();
                            
                            estadoUsuario.put(userId, 7);
                        }
                    } catch (Exception e) {
                        msjMostrar = mensaje.DepositoMal();
                        
                        estadoUsuario.put(userId, 7);
                    }
                    break;
            default: estadoUsuario.put(userId, 7);
                     break;
            }
            
            message.setText(msjMostrar);
            
            try {
                execute(message);
            } catch(TelegramApiException e){
                e.printStackTrace();
            }
        }
        
        
    }

    public String getBotUsername() {
        return "proyecto1_duran_bot";
    } 
    
}
