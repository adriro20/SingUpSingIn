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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class SignOutWindowController implements Initializable {

    @FXML
    private Button btnCerrarAplicacion;
    @FXML
    private Button btnCerrarSesion;
    @FXML
    private BorderPane bpPrincipal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnCerrarAplicacion.setOnAction(this::closeApp);
        btnCerrarSesion.setOnAction(this::signOut);

        ContextMenu contextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Cambiar a tema oscuro");
        MenuItem item2 = new MenuItem("Cambiar a tema claro");
        MenuItem item3 = new MenuItem("Cerrar sesión");
        contextMenu.getItems().addAll(item1, item2, item3);

        bpPrincipal.setOnMouseClicked(event -> controlMenuConceptual(event, contextMenu));
    }

    private void closeApp(ActionEvent event) {
        Optional<ButtonType> confirmar = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro de que desea salir?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (confirmar.get() == ButtonType.YES) {
            Platform.exit();
        }
    }

    private void signOut(ActionEvent event) {
        Optional<ButtonType> confirmar = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro de que desea cerrar sesión?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (confirmar.get() == ButtonType.YES) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaSignIn.fxml"));
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
    }

    private void controlMenuConceptual(MouseEvent event, ContextMenu menu) {
        if (event.getButton() == MouseButton.SECONDARY) {
            menu.show(bpPrincipal, event.getScreenX(), event.getScreenY());
        } else {
            menu.hide();
        }
    }
}
