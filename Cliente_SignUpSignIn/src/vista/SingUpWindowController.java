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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;

/**
 *
 * @author 2dam
 */
public class SingUpWindowController implements  Initializable{
    
    @FXML
    TextField tfNombre;
    
    @FXML
    PasswordField tfTelefono;
    
    @FXML
    TextField tfCorreo;
    
    @FXML
    TextField tfCiudad;
    
    @FXML
    PasswordField pfPass;
    
    @FXML
    PasswordField pfPass2;
    
    @FXML
    TextField tfPass;
    
    @FXML
    TextField tfPass2;
    
    @FXML
    Button btnSingUp;
    
    @FXML
    Button btnSalir;
    
    @FXML
    Button btnVerPass;
    
    @FXML
    Button btnVerPass2;
    
    /**
     * Metodo para coger los datos de la ventana y crear el usuario para registrar
     */
    @FXML
    private void singUp(ActionEvent event){
          if(tfNombre.getText().equals("") || tfTelefono.getText().equals("") || tfCorreo.getText().equals("")
                || tfCiudad.getText().equals("") || pfPass.getText().equals("") || pfPass2.getText().equals("")){
            new Alert(Alert.AlertType.ERROR, "Te Falta algun campo por rellenar");
        }else if(pfPass.getText().equalsIgnoreCase(pfPass2.getText())){
            new Alert(Alert.AlertType.ERROR, "La contraseña no es igual en los dos campos");
        }else if(tfTelefono.getText().length()!=8){
            new Alert(Alert.AlertType.ERROR, "El numero de telefono tiene que tener 8 digitos");
        }else if(!tfCorreo.getText().matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")){
            new Alert(Alert.AlertType.ERROR, "Ese correo electronico no es valido");
        }else{
            /*Usuario user;
            user.setNombre(tfNombre.getText());
            user.setTelefono(tfTelefono.getText());
            user.setCorreo(tfCiudad.getText());
            user.setCorreo(tfCorreo.getText());
            user.setContra(pfContra.getText());*/
        }
    }
    
     @FXML
    private void salir(ActionEvent event){
        Platform.exit();
    }
    
    /**
     * metodo para que sea visible la contraseña repetida
     * @param event 
     */
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
    
    /**
     * metodo para que sea visible la contraseña repetida
     * @param event 
     */
    @FXML
    private void verPass2(ActionEvent event) {
        if(tfPass2.isVisible()){
            pfPass2.setText(tfPass2.getText());            
            pfPass2.setVisible(true);
            tfPass2.setVisible(false);
        }else{
            tfPass2.setText(pfPass2.getText());
            tfPass2.setVisible(true);
            pfPass2.setVisible(false);           
        }
    }
    
    
   
    
    /**
     * Metodo para ir a la vista Sing in
     */
    @FXML
    private void irSingIn(ActionEvent event){
        
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
}
