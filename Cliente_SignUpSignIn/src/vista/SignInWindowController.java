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
import java.util.Optional;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Controlador de la ventana de inicio de sesión (viewSignIn).
 *
 * Esta clase gestiona la interacción de los controles de la interfaz gráfica
 * con el usuario, como iniciar sesión, alternar la visibilidad de la
 * contraseña, cambiar a la ventana de registro y salir de la aplicación.
 *
 * Implementa la interfaz Initializable para inicializar los componentes de la
 * interfaz creados desde un archivo FXML.
 *
 * @author Julen Hidalgo, Adrián Rocha.
 */
public class SignInWindowController implements Initializable {

    /**
     * Botón para salir de la aplicación.
     */
    @FXML
    Button btnSalir;

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
     * Panel principal que contiene los elementos de la interfaz.
     */
    @FXML
    BorderPane bpPrincipal;

    /**
     * Cierra la aplicación.
     *
     * Solicita confirmación del usuario antes de cerrar la aplicación.
     *
     * @param event Evento que se dispara cuando el usuario hace clic en el
     * botón "Salir".
     */
    private void closeApp(ActionEvent event) {
        //Se muestra un Alert con dos opciones para confirmar que el usuario 
        //quiere cerrar la app.
        Optional<ButtonType> confirmar = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro de que desea salir?", ButtonType.YES, ButtonType.NO).showAndWait();

        //Si la confirmación del Alert es verdadera, se cierra el programa.
        if (confirmar.get() == ButtonType.YES) {
            Platform.exit();
        }
    }

    /**
     * Maneja el proceso de inicio de sesión.
     *
     * Verifica que los campos de correo y contraseña no estén vacíos y envía
     * una solicitud de inicio de sesión. Si la autenticación es exitosa, cambia
     * a la ventana principal. Si falla, muestra un mensaje de error dependiendo
     * de el tipo de error que haya sido.
     *
     * @param event Evento disparado al hacer clic en el botón "Sign In".
     */
    private void signIn(ActionEvent event) {
        //Se crea un objeto de la clase Message.
        Message mensaje = new Message();

        //Se comprueba que los campos no estén vacios.
        if (tfCorreo.getText().equals("") || pfPass.getText().equals("")) {
            //En el caso de que algún campo esté vacío se manda un Alert con el 
            //texto "Los campos no pueden estar vacíos".
            new Alert(Alert.AlertType.ERROR, "Los campos no pueden estar vacíos", ButtonType.OK).showAndWait();
        } else {
            //En el caso de que no haya ningún campo vacío se crea un nuevo 
            //objeto de la clase User y se le añaden los atributos Email y 
            //Password con los valores introducidos en los campos.
            User user = new User();
            user.setEmail(tfCorreo.getText());
            user.setPassword(pfPass.getText());

            //Se añade el User creado al Message.
            mensaje.setUser(user);

            //Se le añade al Message el Request SING_IN_REQUEST.
            mensaje.setRequest(Request.SING_IN_REQUEST);

            try {
                // Se manda el Message creado al servidor, en caso de que no 
                // salte ninguna excepción significa que todo ha ido correctamente.
                SignableFactory.getSignable().signIn(mensaje);

                // Se carga el FXML con la información de la vista viewSignOut.
                FXMLLoader loader = new FXMLLoader(getClass().getResource("viewSignOut.fxml"));
                Parent root = loader.load();

                // Obtener el Stage desde el nodo que disparó el evento.
                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

                // Se crea un nuevo objeto de la clase Scene con el FXML cargado.
                Scene scene = new Scene(root);

                // Se muestra en la ventana el Scene creado.
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                // Si salta una IOException significa que ha habido algún 
                // problema al cargar el FXML o al intentar llamar a la nueva 
                // ventana, por lo que se mostrará un Alert con el mensaje 
                // "Error en la sincronización de ventanas, intentalo más tarde".
                Logger.getLogger(SignInWindowController.class.getName()).log(Level.SEVERE, null, ex);
                new Alert(Alert.AlertType.ERROR, "Error en la sincronización de ventanas, intentalo más tarde", ButtonType.OK).showAndWait();
            } catch (InternalServerErrorException | LogInDataException | NoConnectionsAvailableException ex) {
                // Si salta alguna de las excepciones creadas por nosotros se 
                // muestra un Alert con el mensaje correspondiente de 
                // cada una de ellas.
                Logger.getLogger(SignInWindowController.class.getName()).log(Level.SEVERE, null, ex);
                new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK).showAndWait();
            }
        }
    }

    /**
     * Cambia a la ventana de registro (viewSignUp).
     *
     * @param event Evento disparado al hacer clic en el enlace "Sign Up".
     */
    private void signUp(ActionEvent event) {
        try {
            // Se carga el FXML con la información de la vista viewSignUp.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewSignUp.fxml"));
            Parent root = loader.load();

            // Obtener el Stage desde el nodo que disparó el evento.
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Se crea un nuevo objeto de la clase Scene con el FXML cargado.
            Scene scene = new Scene(root);

            // Se muestra en la ventana el Scene creado.
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            // Si salta una IOException significa que ha habido algún 
            // problema al cargar el FXML o al intentar llamar a la nueva 
            // ventana, por lo que se mostrará un Alert con el mensaje 
            // "Error en la sincronización de ventanas, intentalo más tarde".
            Logger.getLogger(SignInWindowController.class.getName()).log(Level.SEVERE, null, ex);
            new Alert(Alert.AlertType.ERROR, "Error en la sincronización de ventanas, intentalo más tarde.", ButtonType.OK).showAndWait();
        }
    }

    /**
     * Alterna la visibilidad de la contraseña (pfPass/tfPass). Si el PasswordField 
     * está visible, la contraseña no se puede leer, en cambio si el campo que 
     * está visible es el TextField la contraseña tiene formato de texto plano.
     *
     * @param event Evento que se dispara cuando el usuario hace clic en el
     * botón "Ver Pass".
     */
    private void verPass(ActionEvent event) {
        //Se obtiene el estilo del botón VerPass.
        String estilo = btnVerPass.getStyle();
        
        //Se quita la imagen del fondo del botón.
        String estiloNuevo = estilo.replace("-fx-background-image: url\\('.*'\\);", "");
        
        //Se comprueba si el PasswordField o el TextField es visible.
        if (tfPass.isVisible()) {
            //Si el TextField está visible, se alterna la visibilidad, se copia 
            //el texto en el PasswordField y se cambia la imagen de fondo.
            pfPass.setText(tfPass.getText());
            pfPass.setVisible(true);
            tfPass.setVisible(false);
            btnVerPass.setStyle(estiloNuevo + "-fx-background-image: url('/img/iconoOjoAbierto.png');");
        } else {
            //Si el PasswordField está visible, se alterna la visibilidad, se copia 
            //el texto en el PasswordField y se cambia la imagen de fondo.
            tfPass.setText(pfPass.getText());
            tfPass.setVisible(true);
            pfPass.setVisible(false);
            btnVerPass.setStyle(estiloNuevo + "-fx-background-image: url('/img/iconoOjoCerrado.png');");
        }
    }

    /**
     * Metodo para abrir el menú conceptual en el caso de que se haga clic con 
     * el botón derecho del ratón, en cambio, si se hace clic con cualqier otro 
     * botón del raton se cierra el menú.
     * 
     * @param event Es el evento que compureba que botón del ratón se clica.
     * @param menu Es el menú contextual que se muestra.
     */
    private void controlMenuConceptual(MouseEvent event, ContextMenu menu) {
        //Se comprueba si se hace clic con el borón derecho del ratón.
        if (event.getButton() == MouseButton.SECONDARY) {
            //Si es así se abre el menú contextual.
            menu.show(bpPrincipal, event.getScreenX(), event.getScreenY());
        } else {
            //Si no, se cierra el mismo.
            menu.hide();
        }
    }

    /**
     * Es el metodo que inicializa la ventana de inicio de sesión, además es la 
     * que le da las propiedades de recoger eventos a todos los botones.
     *
     * @param location Ubicación del archivo FXML utilizado para crear la
     * interfaz.
     * @param resources Recursos utilizados para la interfaz.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Se le quita la propiedad Resizable a la ventana y se le añade el título.
        Platform.runLater(() -> {
            Stage stage = (Stage) bpPrincipal.getScene().getWindow();
            stage.setResizable(false);
            stage.setTitle("Inicio de sesión");
        });

        //Se añaden los listeners a todos los botones.
        btnSalir.setOnAction(this::closeApp);
        btnVerPass.setOnAction(this::verPass);
        btnSignIn.setOnAction(this::signIn);
        hlSignUp.setOnAction(this::signUp);
        hlCrear.setOnAction(this::signUp);

        //Se crea el menú contextual, el cual se mostrará si se hace clic con el 
        //botón izquierdo del ratón.
        ContextMenu contextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Cambiar a tema oscuro");
        MenuItem item2 = new MenuItem("Cambiar a tema claro");
        contextMenu.getItems().addAll(item1, item2);
        bpPrincipal.setOnMouseClicked(event -> controlMenuConceptual(event, contextMenu));

    }

}
