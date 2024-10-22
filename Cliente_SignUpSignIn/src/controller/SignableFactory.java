/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import clases.Signable;

/**
 * La clase SignableFactory se utiliza para obtener instancias de objetos que 
 * implementan la interfaz Signable, en este caso devuelve objetos de la 
 * clase CLient.
 * 
 * @author Julen Hidalgo, Adrian Rocha
 */
public class SignableFactory {
    
    /**
     * Devuelve una instancia de un objeto que implementa la interfaz Signable.
     * 
     * @return una instancia de Signable, en este caso, un objeto de la clase Client.
     */
    public static Signable getSignable(){
        //Retorna un nuevo objeto de la clase Client
        return new Client();
    }
    
}
