/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * clase que maneja todos los controles de la ventana vistaSingUp (ventana de registro de usuario)
 * @author 2dam
 */
public class SingUpWindowController implements  Initializable{
    
    /** Enlace para redirigir al usuario a la vista de inicio de sesión (Sign In). */
    @FXML
    Hyperlink hlSignIn;
    
    /** Enlace para redirigir al usuario a la vista de registro (Sign Up). */
    @FXML
    Hyperlink hlSignUp;
    
    /** Campo de texto para ingresar el nombre del usuario. */
    @FXML
    TextField tfNombre;
    
    /** Campo de texto para ingresar el número de teléfono del usuario. */
    @FXML
    TextField tfTelefono;
    
    /** Campo de texto para ingresar el correo electrónico del usuario. */
    @FXML
    TextField tfCorreo;
    
    /** Campo de texto para ingresar la ciudad del usuario. */
    @FXML
    TextField tfCiudad;
    
    /** Campo de contraseña para ingresar la contraseña del usuario. */
    @FXML
    PasswordField pfPass;
    
    /** Campo de contraseña para repetir la contraseña del usuario. */
    @FXML
    PasswordField pfPass2;
    
    /** Campo de texto para mostrar la contraseña en texto plano (alternativa a pfPass). */
    @FXML
    TextField tfPass;
    
    /** Campo de texto para mostrar la segunda contraseña en texto plano (alternativa a pfPass2). */
    @FXML
    TextField tfPass2;
    
    /** Botón para confirmar el registro del usuario. */
    @FXML
    Button btnSingUp;
    
    /** Botón para salir de la aplicación. */
    @FXML
    Button btnSalir;
    
    /** Botón para alternar la visibilidad de la contraseña principal. */
    @FXML
    Button btnVerPass;
    
    /** Botón para alternar la visibilidad de la contraseña repetida. */
    @FXML
    Button btnVerPass2;
    
    /**
     * Método que gestiona el registro del usuario utilizando los datos
     * proporcionados en los campos de texto. Valida que los campos no estén vacíos,
     * que las contraseñas coincidan, que el número de teléfono tenga 8 dígitos,
     * y que el correo sea válido con formato de Gmail.
     * 
     * @param event Evento que se dispara cuando el usuario intenta registrarse.
     */
    @FXML
    private void singUp(ActionEvent event){
          if(tfNombre.getText().equals("") || tfTelefono.getText().equals("") || tfCorreo.getText().equals("")
                || tfCiudad.getText().equals("") || pfPass.getText().equals("") || pfPass2.getText().equals("")){
            new Alert(Alert.AlertType.ERROR, "Te Falta algun campo por rellenar", ButtonType.OK).showAndWait();
        }else if(!pfPass.getText().equalsIgnoreCase(pfPass2.getText())){
            new Alert(Alert.AlertType.ERROR, "La contraseña no es igual en los dos campos", ButtonType.OK).showAndWait();
        }else if(tfTelefono.getText().length()!=8 || !tfTelefono.getText().matches("\\d+")){
            new Alert(Alert.AlertType.ERROR, "El numero de telefono no es correcto", ButtonType.OK).showAndWait();
        }else if(!tfCorreo.getText().matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")){
            new Alert(Alert.AlertType.ERROR, "Ese correo electronico no es valido", ButtonType.OK).showAndWait();
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
     * Sirve para cerrar la aplicaion
     * @param event 
     */
    @FXML
    private void salir(ActionEvent event){
        Platform.exit();
    }
    
    /**
     * metodo para que alterne entre  visible y no visible la contraseña
     * cambia entre (pfPass/tfPass)
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
     * metodo para que alterne entre  visible y no visible la contraseña repetida
     * cambia entre (pfPass/tfPass)
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
     * Metodo para cambiar de ventana llendo a la vista vistaLogIn
     */
    @FXML
    private void irSingIn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaLogIn.fxml"));
        Parent root = loader.load();

        // Obtener el Stage desde el botón que disparó el evento
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Crear una nueva escena con el contenido cargado
        Scene scene = new Scene(root);

        // Establecer la nueva escena en el Stage
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
}
