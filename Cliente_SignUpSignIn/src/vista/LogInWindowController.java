/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import clases.Message;
import clases.Request;
import controller.SignableFactory;
import clases.User;
import excepciones.InternalServerErrorException;
import excepciones.LogInDataException;
import excepciones.NoConnectionsAvailableException;
import java.io.IOException;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * Clase que controla la ventana de inicio de sesión (LogInWindow). Proporciona
 * métodos para manejar las acciones de los controles en la interfaz de usuario
 * como iniciar sesión, cambiar a la ventana de registro, mostrar u ocultar
 * contraseñas, y salir de la aplicación.
 *
 * @author 2dam
 */
public class LogInWindowController implements Initializable {

    /**
     * Botón para salir de la aplicación.
     */
    @FXML
    Button btnSalir;

    /**
     * Enlace para redirigir al usuario a la vista de inicio de sesión (Sign
     * In).
     */
    @FXML
    Hyperlink hlSignIn;

    /**
     * Enlace para redirigir al usuario a la vista de registro (Sign Up).
     */
    @FXML
    Hyperlink hlSignUp;

    /**
     * Campo de texto para ingresar el correo electrónico del usuario.
     */
    @FXML
    TextField tfCorreo;

    /**
     * Campo de contraseña para ingresar la contraseña del usuario.
     */
    @FXML
    PasswordField pfPass;

    /**
     * Botón para alternar la visibilidad de la contraseña.
     */
    @FXML
    Button btnVerPass;

    /**
     * Botón para iniciar sesión en la aplicación.
     */
    @FXML
    Button btnSignIn;

    /**
     * Enlace para crear una cuenta nueva.
     */
    @FXML
    Hyperlink hlCrear;

    /**
     * Campo de texto para mostrar la contraseña en texto plano (alternativa a
     * pfPass).
     */
    @FXML
    TextField tfPass;
    
    /**
     * Cierra la aplicación.
     *
     * @param event Evento que se dispara cuando el usuario hace clic en el
     * botón "Salir".
     */
    private void closeApp(ActionEvent event) {
        Platform.exit();
    }
    
    /**
     * Método para manejar el inicio de sesión. Valida que el campo de correo y
     * el de contraseña estén llenos antes de proceder con el inicio de sesión.
     * Si falta algún campo, se muestra una alerta de error.
     *
     * @param event Evento que se dispara cuando el usuario hace clic en el
     * botón "Sign In".
     */
    @FXML
    private void logIn(ActionEvent event) {
        Message mensaje = new Message();
        if (tfCorreo.getText().equals("") || pfPass.getText().equals("")) {
            new Alert(Alert.AlertType.ERROR, "Todos los campos tienen que estar llenos", ButtonType.OK).showAndWait();
        } else {
            User user = new User();
            user.setEmail(tfCorreo.getText());
            user.setPassword(pfPass.getText());

            mensaje.setUser(user);
            mensaje.setRequest(Request.SING_IN_REQUEST);

            try {
                if (SignableFactory.getSignable().signIn(mensaje)) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaMain.fxml"));
                    Parent root = loader.load();

                    // Obtener el Stage desde el nodo que disparó el evento
                    Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

                    // Crear una nueva escena con el contenido cargado
                    Scene scene = new Scene(root);

                    // Establecer la nueva escena en el Stage
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (IOException ex){
                Logger.getLogger(LogInWindowController.class.getName()).log(Level.SEVERE, null, ex);
                new Alert(Alert.AlertType.ERROR, "Error en la sincronización de ventanas, intentalo más tarde.", ButtonType.OK).showAndWait();
            } catch (InternalServerErrorException | LogInDataException | NoConnectionsAvailableException ex) {
                Logger.getLogger(LogInWindowController.class.getName()).log(Level.SEVERE, null, ex);
                new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK).showAndWait();
            }
        }
    }

    /**
     * Cambia a la ventana de registro (SignUpWindow). Carga la vista
     * correspondiente al registro y la establece en la escena actual.
     *
     * @param event Evento que se dispara cuando el usuario hace clic en el
     * enlace "Sign Up".
     * @throws IOException Si ocurre un error al cargar el archivo FXML de la
     * vista de Sign Up.
     */
    @FXML
    private void signUp(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaSingUp.fxml"));
            Parent root = loader.load();
            
            // Obtener el Stage desde el nodo que disparó el evento
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            
            // Crear una nueva escena con el contenido cargado
            Scene scene = new Scene(root);
            
            // Establecer la nueva escena en el Stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LogInWindowController.class.getName()).log(Level.SEVERE, null, ex);
            new Alert(Alert.AlertType.ERROR, "Error en la sincronización de ventanas, intentalo más tarde.", ButtonType.OK).showAndWait();
        }
    }

    /**
     * Alterna la visibilidad de la contraseña (pfPass/tfPass). Si el campo de
     * texto de la contraseña está visible, lo oculta y muestra el campo de
     * password; si no, muestra la contraseña en texto plano.
     *
     * @param event Evento que se dispara cuando el usuario hace clic en el
     * botón "Ver Pass".
     */
    @FXML
    private void verPass(ActionEvent event) {
        if (tfPass.isVisible()) {
            pfPass.setText(tfPass.getText());
            pfPass.setVisible(true);
            tfPass.setVisible(false);
        } else {
            tfPass.setText(pfPass.getText());
            tfPass.setVisible(true);
            pfPass.setVisible(false);
        }
    }

    /**
     * Inicializa la ventana de inicio de sesión cuando ésta es creada.
     *
     * @param location Ubicación del archivo FXML utilizado para crear la
     * interfaz.
     * @param resources Recursos internacionales utilizados para la interfaz.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSalir.setOnAction(this::closeApp);
    }

}
