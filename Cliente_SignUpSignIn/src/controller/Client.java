/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import clases.Message;
import static clases.Request.CONNECTIONS;
import static clases.Request.INTERNAL;
import static clases.Request.LOG_IN;
import static clases.Request.USER_EXISTS;
import clases.Signable;
import excepciones.InternalServerErrorException;
import excepciones.LogInDataException;
import excepciones.NoConnectionsAvailableException;
import excepciones.UserExitsException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ResourceBundle;

/**
 *
 * @author 2dam
 */
public class Client implements Signable {

    int puerto = Integer.getInteger(ResourceBundle.getBundle("model.infoClient").getString("PUERTO"));
    String ip = ResourceBundle.getBundle("model.infoClient").getString("IP");

    Socket socket = null;
    ObjectInputStream entrada = null;
    ObjectOutputStream salida = null;

    @Override
    public boolean signIn(Message mensaje) throws InternalServerErrorException, LogInDataException, NoConnectionsAvailableException {
        try {
            socket = new Socket(ip, puerto);
            entrada = new ObjectInputStream(socket.getInputStream());
            salida = new ObjectOutputStream(socket.getOutputStream());

            salida.writeObject(mensaje);
            mensaje = (Message) entrada.readObject();
            switch (mensaje.getRequest()) {
                case INTERNAL:
                    throw new InternalServerErrorException();
                case LOG_IN:
                    throw new LogInDataException();
                case CONNECTIONS:
                    throw new NoConnectionsAvailableException();
                default:
                    return true;
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new InternalServerErrorException();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
                if (salida != null) {
                    salida.close();
                }
            } catch (IOException e) {
                throw new InternalServerErrorException();
            }

        }

    }

    @Override
    public boolean signUp(Message mensaje) throws InternalServerErrorException, UserExitsException, NoConnectionsAvailableException {
        try {
            socket = new Socket(ip, puerto);
            entrada = new ObjectInputStream(socket.getInputStream());
            salida = new ObjectOutputStream(socket.getOutputStream());

            salida.writeObject(mensaje);
            mensaje = (Message) entrada.readObject();
            switch (mensaje.getRequest()) {
                case INTERNAL:
                    throw new InternalServerErrorException();
                case USER_EXISTS:
                    throw new UserExitsException();
                case CONNECTIONS:
                    throw new NoConnectionsAvailableException();
                default:
                    return true;
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new InternalServerErrorException();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
                if (salida != null) {
                    salida.close();
                }
            } catch (IOException e) {
                throw new InternalServerErrorException();
            }

        }

    }

    @Override
    public boolean logOut(Message mensaje) throws InternalServerErrorException {
        try {
            socket = new Socket(ip, puerto);
            entrada = new ObjectInputStream(socket.getInputStream());
            salida = new ObjectOutputStream(socket.getOutputStream());

            salida.writeObject(mensaje);
            mensaje = (Message) entrada.readObject();
            switch (mensaje.getRequest()) {
                case INTERNAL:
                    throw new InternalServerErrorException();
                default:
                    return true;
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new InternalServerErrorException();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
                if (salida != null) {
                    salida.close();
                }
            } catch (IOException e) {
                throw new InternalServerErrorException();
            }

        }

    }

}
