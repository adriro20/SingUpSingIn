/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal del proyecto la cual gestiona el arranque de app, abriendo la
 * UI de SignIn y mostrandola en una ventana.
 *
 * @author Julen Hidalgo
 */
public class Main extends javafx.application.Application {

    /**
     * Método que inicializa y muestra la ventana SignIn de la aplicación.
     *
     * El método carga el archivo FXML que define la interfaz de inicio de
     * sesión y lo utiliza para crear la escena que será mostrada en la
     * ventana.
     *
     * @param stage El escenario principal donde se cargará la escena.
     * 
     * @throws Exception Si ocurre algún error al cargar el archivo FXML o al
     * crear la escena.
     */
    @Override
    public void start(Stage stage) throws Exception {
        //Se carga el FXML viewSignIn.
        Parent root = FXMLLoader.load(getClass().getResource("viewSignIn.fxml"));

        //Se crea un objeto de la clase Scene y se le añade el FXML cargado
        Scene scene = new Scene(root);

        //Se carga la escena creada con el FXML en el Stage(ventana).
        stage.setScene(scene);
        
        //Se muestra la ventana.
        stage.show();
    }

    /**
     * Método main que inicia la aplicación.
     * 
     * @param args los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        //Inicia el programa.
        launch(args);
    }

}
