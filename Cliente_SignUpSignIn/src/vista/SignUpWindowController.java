/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import clases.Message;
import clases.Request;
import clases.User;
import controller.SignableFactory;
import excepciones.InternalServerErrorException;
import excepciones.NoConnectionsAvailableException;
import excepciones.UserExitsException;
import static java.awt.SystemColor.text;
import java.io.IOException;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * clase que maneja todos los controles de la ventana vistaSingUp (ventana de
 * registro de usuario)
 *
 * @author 2dam
 */
public class SignUpWindowController implements Initializable {

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
     * Campo de texto para ingresar el nombre del usuario.
     */
    @FXML
    TextField tfNombre;

    /**
     * Campo de texto para ingresar el número de teléfono del usuario.
     */
    @FXML
    TextField tfZip;

    /**
     * Campo de texto para ingresar el correo electrónico del usuario.
     */
    @FXML
    TextField tfCorreo;

    /**
     * Campo de texto para ingresar la ciudad del usuario.
     */
    @FXML
    TextField tfCiudad;

    /**
     * Campo de contraseña para ingresar la contraseña del usuario.
     */
    @FXML
    PasswordField pfPass;

    /**
     * Campo de contraseña para repetir la contraseña del usuario.
     */
    @FXML
    PasswordField pfPass2;

    /**
     * Campo de texto para mostrar la contraseña en texto plano (alternativa a
     * pfPass).
     */
    @FXML
    TextField tfPass;

    /**
     * Campo de texto para mostrar la segunda contraseña en texto plano
     * (alternativa a pfPass2).
     */
    @FXML
    TextField tfPass2;

    /**
     * Botón para confirmar el registro del usuario.
     */
    @FXML
    Button btnSingUp;

    /**
     * Botón para salir de la aplicación.
     */
    @FXML
    Button btnSalir;

    /**
     * Botón para alternar la visibilidad de la contraseña principal.
     */
    @FXML
    Button btnVerPass;

    /**
     * Botón para alternar la visibilidad de la contraseña repetida.
     */
    @FXML
    Button btnVerPass2;

    /**
     * Campo de texto para ingresar la calle del usuario.
     */
    @FXML
    TextField tfStreet;

    /**
     * Campo para saber si el usuario esta activo o no
     */
    @FXML
    CheckBox cbActive;

    @FXML
    BorderPane bpPrincipal;

    /**
     * Método que gestiona el registro del usuario utilizando los datos
     * proporcionados en los campos de texto. Valida que los campos no estén
     * vacíos, que las contraseñas coincidan, que el número de teléfono tenga 8
     * dígitos, y que el correo sea válido con formato de Gmail.
     *
     * @param event Evento que se dispara cuando el usuario intenta registrarse.
     */
    @FXML
    private void signUp(ActionEvent event) {
        User user;
        Message mensaje;
        if (tfNombre.getText().equals("") || tfZip.getText().equals("") || tfCorreo.getText().equals("")
                || tfCiudad.getText().equals("") || pfPass.getText().equals("") || pfPass2.getText().equals("") || tfStreet.getText().equals("")) {
            new Alert(Alert.AlertType.ERROR, "Te Falta algun campo por rellenar", ButtonType.OK).showAndWait();
        } else if (pfPass.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$")) {
            new Alert(Alert.AlertType.ERROR, "La contraseña no es valida", ButtonType.OK).showAndWait();
        } else if (!pfPass.getText().equalsIgnoreCase(pfPass2.getText())) {
            new Alert(Alert.AlertType.ERROR, "La contraseña no es igual en los dos campos", ButtonType.OK).showAndWait();
        } else if (tfZip.getText().length() != 5 || !tfZip.getText().matches("\\d+")) {
            new Alert(Alert.AlertType.ERROR, "Codigo Postal Incorrecto", ButtonType.OK).showAndWait();
        } else if (!tfCorreo.getText().matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
            new Alert(Alert.AlertType.ERROR, "Ese correo electronico no es valido", ButtonType.OK).showAndWait();
        } else {
            user = new User();
            mensaje = new Message();
            user.setName(tfNombre.getText());
            user.setZip(tfZip.getText());
            user.setCity(tfCiudad.getText());
            user.setStreet(tfStreet.getText());
            user.setEmail(tfCorreo.getText());
            user.setPassword(pfPass.getText());
            user.setActive(cbActive.isSelected());
            mensaje.setUser(user);
            mensaje.setRequest(Request.SING_UP_REQUEST);

            try {
                if (SignableFactory.getSignable().signUp(mensaje)) {
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
            } catch (IOException ex) {
                Logger.getLogger(SignInWindowController.class.getName()).log(Level.SEVERE, null, ex);
                new Alert(Alert.AlertType.ERROR, "Error en la sincronización de ventanas, intentalo más tarde.", ButtonType.OK).showAndWait();
            } catch (InternalServerErrorException | UserExitsException | NoConnectionsAvailableException ex) {
                Logger.getLogger(SignInWindowController.class.getName()).log(Level.SEVERE, null, ex);
                new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK).showAndWait();
            }

        }
    }

    /**
     * Sirve para cerrar la aplicaion
     *
     * @param event
     */
    @FXML
    private void salir(ActionEvent event) {
        Platform.exit();
    }

    /**
     * metodo para que alterne entre visible y no visible la contraseña cambia
     * entre (pfPass/tfPass)
     *
     * @param event
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
     * metodo para que alterne entre visible y no visible la contraseña repetida
     * cambia entre (pfPass/tfPass)
     *
     * @param event
     */
    @FXML
    private void verPass2(ActionEvent event) {
        if (tfPass2.isVisible()) {
            pfPass2.setText(tfPass2.getText());
            pfPass2.setVisible(true);
            tfPass2.setVisible(false);
        } else {
            tfPass2.setText(pfPass2.getText());
            tfPass2.setVisible(true);
            pfPass2.setVisible(false);
        }
    }

    /**
     * Metodo para cambiar de ventana llendo a la vista vistaLogIn
     */
    @FXML
    private void irSignIn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewSignIn.fxml"));
            Parent root = loader.load();

            // Obtener el Stage desde el botón que disparó el evento
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Crear una nueva escena con el contenido cargado
            Scene scene = new Scene(root);

            // Establecer la nueva escena en el Stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SignInWindowController.class.getName()).log(Level.SEVERE, null, ex);
            new Alert(Alert.AlertType.ERROR, "Error en la sincronización de ventanas, intentalo más tarde.", ButtonType.OK).showAndWait();
        }
    }

    private void controlMenuConceptual(MouseEvent event, ContextMenu menu) {
        if (event.getButton() == MouseButton.SECONDARY) {
            menu.show(bpPrincipal, event.getScreenX(), event.getScreenY());
        } else {
            menu.hide();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            Stage stage = (Stage) bpPrincipal.getScene().getWindow();
            stage.setResizable(false);
            stage.setTitle("Registro");
        });
        Tooltip tooltip = new Tooltip("Nombre y Dos Apellidos");
        tfNombre.setTooltip(tooltip);
        Tooltip tooltip2 = new Tooltip("Correo valido que termine @gmail.com");
        tfCorreo.setTooltip(tooltip2);
        Tooltip tooltip3 = new Tooltip("Introduce solo numeros");
        tfZip.setTooltip(tooltip3);
        Tooltip tooltip4 = new Tooltip("Minimo 6 caracteres minimo una letra mayuscula y otra minuscula y un numero");
        tfPass.setTooltip(tooltip4);
        pfPass.setTooltip(tooltip4);
        tfPass2.setTooltip(tooltip4);
        pfPass2.setTooltip(tooltip4);

        ContextMenu contextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Cambiar a tema oscuro");
        MenuItem item2 = new MenuItem("Cambiar a tema claro");
        contextMenu.getItems().addAll(item1, item2);

        bpPrincipal.setOnMouseClicked(event -> controlMenuConceptual(event, contextMenu));
        
        hlSignIn.setOnAction(this::irSignIn);
        btnSalir.setOnAction(this::salir);
        btnVerPass.setOnAction(this::verPass);
        btnVerPass2.setOnAction(this::verPass2);
        btnSingUp.setOnAction(this::signUp);
    }
}
