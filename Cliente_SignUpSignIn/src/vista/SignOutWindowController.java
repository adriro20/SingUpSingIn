/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.IOException;
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
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Controlador de la ventana principal, con la que puedes hacer cerrar sesión
 * (viewSignOut).
 *
 * Esta clase funciona como la ventana principal de la app, a la que se accede
 * una vez iniciado sesión, aunque en este reto la aplicación no tiene una
 * funcionalidad más allá de iniciar sesión y registrar usuarios, por lo que
 * unicamente permite cerras sesión.
 *
 * Implementa la interfaz Initializable para inicializar los componentes de la
 * interfaz creados desde un archivo FXML.
 *
 * @author Julen Hidalgo, Adrián Rocha.
 */
public class SignOutWindowController implements Initializable {

    /**
     * Botón para cerrar la applicación.
     */
    @FXML
    private Button btnCerrarAplicacion;

    /**
     * Botón para cerrar sesión.
     */
    @FXML
    private Button btnCerrarSesion;

    /**
     * Panel principal que contiene los elementos de la interfaz.
     */
    @FXML
    private BorderPane bpPrincipal;

    /**
     * Panel que contiene los elementos situados en el centro que actua como
     * fondo.
     */
    @FXML
    StackPane stackPane;

    /**
     * Es el metodo qeu inicializa la ventana de inicio de sesión, además es la
     * que le da las propiedades de recoger eventos a todos los botones.
     *
     * @param url Ubicación del archivo FXML utilizado para crear la interfaz.
     * @param resources Recursos utilizados para la interfaz.
     */
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        //Se le quita la propiedad Resizable a la ventana y se le añade el título.
        Platform.runLater(() -> {
            Stage stage = (Stage) bpPrincipal.getScene().getWindow();
            stage.setResizable(false);
            stage.setTitle("Inicio");
        });

        //Se añaden los listeners a todos los botones.
        btnCerrarAplicacion.setOnAction(this::closeApp);
        btnCerrarSesion.setOnAction(this::signOut);

        //Se crea el menú contextual, el cual se mostrará si se hace clic con el 
        //botón izquierdo del ratón.
        ContextMenu contextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Cambiar a tema oscuro");
        item1.setOnAction(this::cambiarTemaOscuro);
        MenuItem item2 = new MenuItem("Cambiar a tema claro");
        item2.setOnAction(this::cambiarTemaClaro);
        MenuItem item3 = new MenuItem("Cerrar sesión");
        item3.setOnAction(this::signOut);
        contextMenu.getItems().addAll(item1, item2, item3);

        bpPrincipal.setOnMouseClicked(event -> controlMenuConceptual(event, contextMenu));
    }

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
     * Maneja el proceso de cerrar sesión.
     *
     * Lanza una alerta para comprobar si realmente el cliente quiere cerrar
     * sesión.
     *
     * @param event Evento disparado al hacer clic en el botón "SignOut".
     */
    private void signOut(ActionEvent event) {
        //Se lanza un Alert preguntando si realmente quiere cerrar sesión y se 
        //recoge la respuesta del cliente.
        Optional<ButtonType> confirmar = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro de que desea cerrar sesión?", ButtonType.YES, ButtonType.NO).showAndWait();

        //Se comprueba la respuesta.
        if (confirmar.get() == ButtonType.YES) {
            //En el caso de que sea verdadera se cambia la ventana para mostrar la vista viewSignIn.
            try {
                // Se carga el FXML con la información de la vista viewSignIn.
                FXMLLoader loader = new FXMLLoader(getClass().getResource("viewSignIn.fxml"));
                Parent root = loader.load();
                
                // Obtener el Stage desde el botón que disparó el evento.
                Stage stage;
                if(event.getSource() instanceof javafx.scene.control.MenuItem){
                    stage = (Stage) ((javafx.scene.control.MenuItem) event.getSource()).getParentPopup().getOwnerWindow().getScene().getWindow();  
                }else{
                    stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                }
                


                // Crear una nueva escena con el contenido cargado.
                Scene scene = new Scene(root);

                // Establecer la nueva escena en el Stage.
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
    }

    /**
     *
     * Cambia el tema del fondo a oscuro.
     *
     * @param event Evento que se dispara cuando el usuario hace clic en el
     * apartado de cambiar a tema oscuro en el menú contextual.
     */
    private void cambiarTemaOscuro(ActionEvent event) {
        //Se obtiene el estilo del fondo.
        String estilo = stackPane.getStyle();

        //Se quita la imagen del fondo.
        String estiloNuevo = estilo.replace("-fx-background-image: url\\('.*'\\);", "");

        //Se añade al fondo la imagen con el tema oscuro
        stackPane.setStyle(estiloNuevo + "-fx-background-image: url('/img/imgFondoNegro.jpg');");
    }

    /**
     *
     * Cambia el tema del fondo a oscuro.
     *
     * @param event Evento que se dispara cuando el usuario hace clic en el
     * apartado de cambiar a tema oscuro en el menú contextual.
     */
    private void cambiarTemaClaro(ActionEvent event) {
        //Se obtiene el estilo del fondo.
        String estilo = stackPane.getStyle();

        //Se quita la imagen del fondo.
        String estiloNuevo = estilo.replace("-fx-background-image: url\\('.*'\\);", "");

        //Se añade al fondo la imagen con el tema oscuro
        stackPane.setStyle(estiloNuevo + "-fx-background-image: url('/img/imgFondo.jpg');");
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
}
