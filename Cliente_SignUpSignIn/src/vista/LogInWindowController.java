/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;


import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;

/**
 *
 * @author 2dam
 */
public class LogInWindowController implements Initializable{
    


    @FXML
    Button btnSalir;
    
    @FXML
    Hyperlink hlSignIn;
    
    @FXML
    Hyperlink hlSignUp;
    

    @FXML
    TextField tfCorreo;
    

    PasswordField pfPass;
    
    @FXML
    Button btnVerPass;
    
    @FXML
    Button btnSignIn;
    
    @FXML
    Hyperlink hlCrear;
    
    @FXML
    TextField tfPass;
    
    
    @FXML
    private void closeApp(ActionEvent event){
        Platform.exit();
    }
    
    @FXML
    private void logIn(ActionEvent event) {
        if(tfCorreo.getText().equals("") || pfPass.getText().equals("")){
            new Alert(Alert.AlertType.ERROR, "Todos los campos tienen que estar llenos", ButtonType.OK).showAndWait();
        }else{
//            User user = new User();
//            user.setCorreo(tfCorreo.getText());
//            user.setContra(pfPass.getText());
        }


    }
    
    @FXML
    private void signUp(ActionEvent event) {
        

    }
    


    @FXML
    private void verPass(ActionEvent event) {
        if(tfPass.isVisible()){
            pfPass.setText(tfPass.getText());            
            pfPass.setVisible(true);
            tfPass.setVisible(false);
        }else{
            tfPass.setText(pfPass.getText());
            tfPass.setVisible(true);
            pfPass.setVisible(false);           
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         
    }

    
}
