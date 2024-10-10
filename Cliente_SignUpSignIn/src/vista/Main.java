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

public class Main extends javafx.application.Application{
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("vistaSingUp.fxml"));

        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metodo para ejecutar el programa
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
