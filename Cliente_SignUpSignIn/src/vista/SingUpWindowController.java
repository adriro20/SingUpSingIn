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
    PasswordField pfContra;
    
    @FXML
    PasswordField pfContra2;
    
    @FXML
    Button btnSingUp;
    
    
    
    /**
     * Metodo para coger los datos de la ventana y crear el usuario para registrar
     */
    private void singUp(){
          if(tfNombre.getText().equals("") || tfTelefono.getText().equals("") || tfCorreo.getText().equals("")
                || tfCiudad.getText().equals("") || pfContra.getText().equals("") || pfContra2.getText().equals("")){
            new Alert(Alert.AlertType.ERROR, "Te Falta algun campo por rellenar");
        }else if(pfContra.getText().equalsIgnoreCase(pfContra2.getText())){
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
    
    /**
     * Metodo para cerrar la aplicacion
     */
    private void salir(){
        
    }
    
    /**
     * Metodo para ir a la vista Sing in
     */
    private void irSingIn(){
        
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
}
