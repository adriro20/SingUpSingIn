/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author 2dam
 */
public class MainSingUp extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws Exception {
        //Se carga el FXML viewSignIn.
        Parent root = FXMLLoader.load(getClass().getResource("viewSignUp.fxml"));

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
