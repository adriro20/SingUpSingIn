/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import clases.Message;
import clases.Request;
import static clases.Request.CONNECTIONS_EXCEPTION;
import static clases.Request.INTERNAL_EXCEPTION;
import static clases.Request.LOG_IN_EXCEPTION;
import static clases.Request.USER_EXISTS_EXCEPTION;
import clases.Signable;
import clases.User;
import excepciones.InternalServerErrorException;
import excepciones.LogInDataException;
import excepciones.NoConnectionsAvailableException;
import excepciones.ServerClosedException;
import excepciones.UserExitsException;
import excepciones.UserNotActiveException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La clase Client implementa la interfaz Signable y es la encargada de
 * comunicar con la parte servidor para realizar las operaciones de inicio de
 * sesión, registro y cierre de sesión. Se conecta mediante sockets, envía y
 * recibe objetos de tipo Message y lanza excepciones personalizadas basadas en
 * las respuestas del servidor.
 *
 * @author Julen Hidalgo
 */
public class Client implements Signable {

    /**
     * Número de puerto para la conexión con el servidor, cargado desde el
     * archivo de propiedades infoClient.
     */
    int puerto = Integer.valueOf(ResourceBundle.getBundle("model.infoClient").getString("PUERTO"));

    /**
     * Dirección IP para la conexión con el servidor, cargado desde el archivo
     * de propiedades infoClient.
     */
    String ip = ResourceBundle.getBundle("model.infoClient").getString("IP");

    /**
     * Socket utilizado para la conexión entre el clinete y el servidor.
     */
    Socket socket = null;

    /**
     * Flujo de entrada para recibir información desde el servidor
     */
    ObjectInputStream entrada = null;

    /**
     * Flujo de salida para enviar información hacia el servidor
     */
    ObjectOutputStream salida = null;

    
    @Override
    public User signIn(User user) throws InternalServerErrorException, LogInDataException, NoConnectionsAvailableException, UserNotActiveException, ServerClosedException {
        try {
            Message mensaje = new Message();
            
            //Se añade el User creado al Message.
            mensaje.setUser(user);

            //Se le añade al Message el Request SING_UP_REQUEST.
            mensaje.setRequest(Request.SING_IN_REQUEST);
            return sendMessage(mensaje);
        } catch (UserExitsException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }


    public User sendMessage(Message messaje) throws InternalServerErrorException, UserExitsException, NoConnectionsAvailableException, ServerClosedException {
        Message mensaje = messaje;
        try {
            //Establece la conexión con el servidor con la IP y el puerto.
            socket = new Socket(ip, puerto);

            //Inicializa los flujos de entrada y salida para enviar y recoger datos.
            entrada = new ObjectInputStream(socket.getInputStream());
            salida = new ObjectOutputStream(socket.getOutputStream());
            
            //Envia el mensaje al servidor.
            salida.writeObject(mensaje);

            //Espera la entrada del mensaje de vuelta desde el servidor.
            mensaje = (Message) entrada.readObject();

            //Lee el mensaje enviado por el servidor y comprueba si ha devuelto 
            //algun mensaje de error, dependiendo del mensaje lanza una excepción u otra.
            switch (mensaje.getRequest()) {
                case INTERNAL_EXCEPTION:
                    throw new InternalServerErrorException();
                case USER_EXISTS_EXCEPTION:
                    throw new UserExitsException();
                case CONNECTIONS_EXCEPTION:
                    throw new NoConnectionsAvailableException();
            }
            //Si sucede algún otro error se lanza la excepción InternalServerErrorException.
        } catch (IOException | ClassNotFoundException e) {
            throw new ServerClosedException();
        } finally {
            closeConnection();
        }
        //Si todo ha ido correctamente, es decir, si en el mensaje del servidor 
        //no devuelto ningún error, se devuelve el mismo User enviado.
        return mensaje.getUser();

    }

    private void closeConnection() throws InternalServerErrorException{
        try {
            //Se cierra la conexión y los flujos de salida y entrada.
            if (socket != null) {
                socket.close();
            }
            if (entrada != null) {
                entrada.close();
            }
            if (salida != null) {
                salida.close();
            }
            //Si sucede algún error al cerrar las conexiones se lanza la 
            //excepción InternalServerErrorException.
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public User signUp(User user) throws InternalServerErrorException, UserExitsException, NoConnectionsAvailableException, ServerClosedException {
        Message mensaje = new Message();

        //Se añade el User creado al Message.
        mensaje.setUser(user);

        //Se le añade al Message el Request SING_UP_REQUEST.
        mensaje.setRequest(Request.SING_IN_REQUEST);
        return sendMessage(mensaje);
    }

}
