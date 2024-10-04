/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Button;
import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;

/**
 *
 * @author 2dam
 */
public class LogInWindowController implements Initializable{
    
    @FXML
    TextField tfCorreo;
    
    @FXML
    PasswordField pfContra;
    
    @FXML
    Button btnLogIn;
        
    @FXML
    Button btnCrear;
    
    
    @FXML
    private void logIn(ActionEvent event) {
        if(tfCorreo.getText().equals("") || pfContra.getText().equals("")){
            new Alert(Alert.AlertType.ERROR, "Todos los campos tienen que estar llenos", ButtonType.OK).showAndWait();
        }else{
            UserLogIn user = new UserLogIn();
            user.setCorreo(tfCorreo.getText());
            user.setContra(pfContra.getText());
        }
        
        

    }
    
    @FXML
    private void signUp(ActionEvent event) {
        

    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
