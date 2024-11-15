/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import clases.Message;
import clases.User;
import controller.SignableFactory;
import excepciones.InternalServerErrorException;
import excepciones.NoConnectionsAvailableException;
import excepciones.ServerClosedException;
import excepciones.UserExitsException;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import static javafx.scene.paint.Color.rgb;
import static javafx.scene.paint.Color.rgb;
import static javafx.scene.paint.Color.rgb;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static javax.management.Query.value;

/**
 * Controlador de la ventana de registro (viewSignUp).
 *
 * Esta clase gestiona la interacción de los controles de la interfaz gráfica
 * con el usuario, como registrarse, alternar la visibilidad de la contraseña,
 * cambiar a la ventana de inicio de sesión y salir de la aplicación.
 *
 * Implementa la interfaz Initializable para inicializar los componentes de la
 * interfaz creados desde un archivo FXML.
 *
 * @author Erlantz Rey.
 */
public class SignUpWindowController implements Initializable {

    /**
     * Panel principal que contiene los elementos de la interfaz.
     */
    @FXML
    BorderPane bpPrincipal;

    /**
     * Enlace para redirigir al usuario a la vista de inicio de sesión (Sign
     * In).
     */
    @FXML
    Hyperlink hlSignIn;

    /**
     * Botón para salir de la aplicación.
     */
    @FXML
    Button btnSalir;

    /**
     * Campo de texto para ingresar el nombre del usuario.
     */
    @FXML
    TextField tfNombre;
    
    @FXML
    Label lblNombre;

    /**
     * Campo de texto para ingresar el correo electrónico del usuario.
     */
    @FXML
    TextField tfCorreo;
    
    @FXML
    Label lblCorreo;
    /**
     * Campo de texto para ingresar la ciudad del usuario.
     */
    @FXML
    TextField tfCiudad;
    
    @FXML
    Label lblCiudad;
    /**
     * Campo de texto para ingresar la calle del usuario.
     */
    @FXML
    TextField tfCalle;
    
    @FXML
    Label lblCalle;
    
    /**
     * Campo de texto para ingresar el número de teléfono del usuario.
     */
    @FXML
    TextField tfZip;
    
    @FXML
    Label lblZip;
    
    /**
     * Campo de contraseña para ingresar la contraseña del usuario.
     */
    @FXML
    PasswordField pfPass;
    
    @FXML
    Label lblPasswd;

    /**
     * Campo de contraseña para repetir la contraseña del usuario.
     */
    @FXML
    PasswordField pfPass2;
    
    @FXML
    Label lblPasswd2;

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
     * Campo para saber si el usuario esta activo o no
     */
    @FXML
    CheckBox cbActive;

    /**
     * Enlace para redirigir al usuario a la vista de registro (Sign Up).
     */
    @FXML
    Hyperlink hlTienes;

    /**
     * Botón para confirmar el registro del usuario.
     */
    @FXML
    Button btnSignUp;

    /**
     * Panel que contiene los elementos situados en el centro que actua como
     * fondo.
     */
    @FXML
    StackPane stackPane;
    
    /**Variable para saber si el tema esta en oscuro o claro*/
    private boolean oscuro;
    
   
      
    private void validarNombre(){
       
        if (tfNombre.getText().equals("")) {
            //Si hay alguno vacío se muestra un mensaje.
            

           
        }
    }

    
    
    /**
     * Maneja el proceso de registro.
     *
     * Verifica que los campos de texto no estén vacíos y envía una solicitud de
     * registro. Si la autenticación es exitosa, cambia a la ventana principal.
     * Si falla, muestra un mensaje de error dependiendo de el tipo de error que
     * haya sido.
     *
     * @param event Evento disparado al hacer clic en el botón "Sign In".
     */
    @FXML
    private void signUp(ActionEvent event) {
        //Se crea un nuevo User y Messsage
        User user = new User();
        Message mensaje = new Message();
        
        //Se comprueban los campos para ver si hay alguno vacío.
        if (tfNombre.getText().equals("")) {
            //Si hay alguno vacío se muestra un mensaje.

            lblNombre.setText(ponerAsterisco(lblNombre.getText()));
            Optional<ButtonType> confirmar = new Alert(Alert.AlertType.ERROR, "Te Falta por rellenar el nombre", ButtonType.OK).showAndWait();
            if (confirmar.get() == ButtonType.OK) {
                lblNombre.setStyle("-fx-text-fill:red");
            }

        } else if (tfCorreo.getText().equals("")) {

            lblCorreo.setText(ponerAsterisco(lblCorreo.getText()));
            Optional<ButtonType> confirmar2 = new Alert(Alert.AlertType.ERROR, "Te Falta por rellenar el el correo", ButtonType.OK).showAndWait();
            if (confirmar2.get() == ButtonType.OK) {
                lblCorreo.setStyle("-fx-text-fill:red");
            }

        } else if (tfCiudad.getText().equals("")) {

            lblCiudad.setText(ponerAsterisco(lblCiudad.getText()));
            Optional<ButtonType> confirmar3 = new Alert(Alert.AlertType.ERROR, "Te Falta por rellenar la ciudad", ButtonType.OK).showAndWait();
            if (confirmar3.get() == ButtonType.OK) {
                lblCiudad.setStyle("-fx-text-fill:red");
            }

        } else if (tfCalle.getText().equals("")) {

            lblCalle.setText(ponerAsterisco(lblCalle.getText()));
            Optional<ButtonType> confirmar4 = new Alert(Alert.AlertType.ERROR, "Te Falta por rellenar la calle", ButtonType.OK).showAndWait();
            if (confirmar4.get() == ButtonType.OK) {
                lblCalle.setStyle("-fx-text-fill:red");
            }

        } else if (tfZip.getText().equals("")) {

            lblZip.setText(ponerAsterisco(lblZip.getText()));
            Optional<ButtonType> confirmar5 = new Alert(Alert.AlertType.ERROR, "Te Falta por rellenar el codigo postal", ButtonType.OK).showAndWait();
            if (confirmar5.get() == ButtonType.OK) {
                lblZip.setStyle("-fx-text-fill:red");
            }
        } else if (pfPass.getText().equals("")) {

            lblPasswd.setText(ponerAsterisco(lblPasswd.getText()));
            Optional<ButtonType> confirmar6 = new Alert(Alert.AlertType.ERROR, "Te Falta por rellenar la contraseña", ButtonType.OK).showAndWait();
            if (confirmar6.get() == ButtonType.OK) {
                lblPasswd.setStyle("-fx-text-fill:red");
            }
        } else if (pfPass2.getText().equals("")) {

            lblPasswd2.setText(ponerAsterisco(lblPasswd2.getText()));
            Optional<ButtonType> confirmar7 = new Alert(Alert.AlertType.ERROR, "Te Falta por rellenar la segunda contraseña", ButtonType.OK).showAndWait();
            if (confirmar7.get() == ButtonType.OK) {
                lblPasswd2.setStyle("-fx-text-fill:red");
            }

        } else if (!pfPass.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$")) {
            //Si no es válida se muestra un mensaje.
            Optional<ButtonType> confirmar8 = new Alert(Alert.AlertType.ERROR, "Contraseña no valida", ButtonType.OK).showAndWait();
            if (confirmar8.get() == ButtonType.OK) {
                lblPasswd.setStyle("-fx-text-fill:red");;
            }
            //Se comprueba que los dos campos de contraseña tengan el mismo texto.
        } else if (!pfPass.getText().equalsIgnoreCase(pfPass2.getText())) {
            //Si no lo son, se muestra un mensaje.

            Optional<ButtonType> confirmar9 = new Alert(Alert.AlertType.ERROR, "Contraseñas diferentes", ButtonType.OK).showAndWait();
            if (confirmar9.get() == ButtonType.OK) {
                lblPasswd.setStyle("-fx-text-fill:red");
                lblPasswd2.setStyle("-fx-text-fill:red");
            }

            //Se comprueba si el código postal tiene 5 números.
        } else if (tfZip.getText().length() != 5 || !tfZip.getText().matches("\\d+")) {
            //Si no tiene 5 números se muestra un mensaje.

            Optional<ButtonType> confirmar0 = new Alert(Alert.AlertType.ERROR, "codigo postal incorrecto", ButtonType.OK).showAndWait();
            if (confirmar0.get() == ButtonType.OK) {
                lblZip.setStyle("-fx-text-fill:red");
            }

            //Se comprueban los requisitos del email.
        } else if (!tfCorreo.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]+$")) {
            //Si no los cumple se muestra un mensaje.    
            Optional<ButtonType> confirmar11 = new Alert(Alert.AlertType.ERROR, "Correo no valido", ButtonType.OK).showAndWait();
            if (confirmar11.get() == ButtonType.OK) {
                lblCorreo.setStyle("-fx-text-fill:red");
            }
            //Si todo ha sido correcto, se crea el Message que se va a mandar al servidor.
        } else {
            //Se cargan los datos de los campos de texto en el User.
            user.setName(tfNombre.getText());
            user.setZip(tfZip.getText());
            user.setCity(tfCiudad.getText());
            user.setStreet(tfCalle.getText());
            user.setEmail(tfCorreo.getText());
            user.setPassword(pfPass.getText());
            user.setActive(cbActive.isSelected());

            try {
                // Se manda el User con los datos introducidos al servidor, en caso de que no 
                // salte ninguna excepción significa que todo ha ido correctamente.
                SignableFactory.getSignable().signUp(user);

                // Se carga el FXML con la información de la vista viewSignOut.
                FXMLLoader loader = new FXMLLoader(getClass().getResource("viewSignOut.fxml"));
                Parent root = loader.load();
                //abrir el controlado de la vista para poner el tema oscuro o claro
                SignOutWindowController controler = loader.getController();
                if (oscuro) {
                    controler.cambiarTemaOscuro(event);
                } else {
                    controler.cambiarTemaClaro(event);
                }
                // Obtener el Stage desde el nodo que disparó el evento
                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

                // Crear una nueva escena con el contenido cargado
                Scene scene = new Scene(root);

                // Establecer la nueva escena en el Stage
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                // Si salta una IOException significa que ha habido algún 
                // problema al cargar el FXML o al intentar llamar a la nueva 
                // ventana, por lo que se mostrará un Alert con el mensaje 
                // "Error en la sincronización de ventanas, intentalo más tarde".
                Logger.getLogger(SignInWindowController.class.getName()).log(Level.SEVERE, null, ex);
                new Alert(Alert.AlertType.ERROR, "Error en la sincronización de ventanas, intentalo más tarde.", ButtonType.OK).showAndWait();
            } catch (InternalServerErrorException | UserExitsException | NoConnectionsAvailableException | ServerClosedException ex) {
                // Si salta alguna de las excepciones creadas por nosotros se 
                // muestra un Alert con el mensaje correspondiente de 
                // cada una de ellas.
                Logger.getLogger(SignInWindowController.class.getName()).log(Level.SEVERE, null, ex);
                new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK).showAndWait();
            }

        }
    }
    
    
    
    /**
     * Cierra la aplicación cuando le haces clic en el botón de salir.
     *
     * Solicita confirmación del usuario antes de cerrar la aplicación.
     *
     * @param event Evento que se dispara cuando el usuario hace clic en el
     * botón "Salir".
     */
    private void closeAppFromButton(ActionEvent event) {
        //Se muestra un Alert con dos opciones para confirmar que el usuario 
        //quiere cerrar la app.
        Optional<ButtonType> confirmar = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro de que desea salir?", ButtonType.YES, ButtonType.NO).showAndWait();

        //Si la confirmación del Alert es verdadera, se cierra el programa.
        if (confirmar.get() == ButtonType.YES) {
            Platform.exit();
        }
    }

    /**
     * Cierra la aplicación cuando le haces clic en la X de la ventana.
     *
     * Solicita confirmación del usuario antes de cerrar la aplicación.
     *
     * @param event Evento que se dispara cuando el usuario hace clic en el
     * botón "Salir".
     */
    private void closeAppFromX(WindowEvent event) {
        //Se muestra un Alert con dos opciones para confirmar que el usuario 
        //quiere cerrar la app.
        Optional<ButtonType> confirmar = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro de que desea salir?", ButtonType.YES, ButtonType.NO).showAndWait();

        //Si la confirmación del Alert es verdadera, se cierra el programa.
        if (confirmar.get() == ButtonType.YES) {
            Platform.exit();
        }else{
            event.consume();
        }
    }

    /**
     * Alterna la visibilidad de la contraseña (pfPass/tfPass). Si el
     * PasswordField está visible, la contraseña no se puede leer, en cambio si
     * el campo que está visible es el TextField la contraseña tiene formato de
     * texto plano.
     *
     * @param event Evento que se dispara cuando el usuario hace clic en el
     * botón "verPass".
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
     * Alterna la visibilidad de la contraseña (pfPass2/tfPass2). Si el
     * PasswordField está visible, la contraseña no se puede leer, en cambio si
     * el campo que está visible es el TextField la contraseña tiene formato de
     * texto plano.
     *
     * @param event Evento que se dispara cuando el usuario hace clic en el
     * botón "verPass2".
     */
    private void verPass2(ActionEvent event) {
        //Se obtiene el estilo del botón VerPass2.
        String estilo = btnVerPass2.getStyle();

        //Se quita la imagen del fondo del botón.
        String estiloNuevo = estilo.replace("-fx-background-image: url\\('.*'\\);", "");

        //Se comprueba si el PasswordField o el TextField es visible.
        if (tfPass2.isVisible()) {
            //Si el TextField está visible, se alterna la visibilidad, se copia 
            //el texto en el PasswordField y se cambia la imagen de fondo.
            pfPass2.setText(tfPass2.getText());
            pfPass2.setVisible(true);
            tfPass2.setVisible(false);
            btnVerPass2.setStyle(estiloNuevo + "-fx-background-image: url('/img/iconoOjoAbierto.png');");
        } else {
            //Si el PasswordField está visible, se alterna la visibilidad, se copia 
            //el texto en el PasswordField y se cambia la imagen de fondo.
            tfPass2.setText(pfPass2.getText());
            tfPass2.setVisible(true);
            pfPass2.setVisible(false);
            btnVerPass2.setStyle(estiloNuevo + "-fx-background-image: url('/img/iconoOjoCerrado.png');");
        }
    }

    /**
     * Cambia a la ventana de inicio de sesión (viewSignIn).
     *
     * @param event Evento disparado al hacer clic en el enlace "hlSingIn".
     */
    @FXML
    private void irSignIn(ActionEvent event) {
        try {
            // Se carga el FXML con la información de la vista viewSignIn.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewSignIn.fxml"));
            Parent root = loader.load();
            SignInWindowController controler = loader.getController();
             if(oscuro){
                controler.cambiarTemaOscuro(event);
            }else{
                controler.cambiarTemaClaro(event);
            }
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
     *
     * Cambia el tema del fondo a oscuro.
     *
     * @param event Evento que se dispara cuando el usuario hace clic en el
     * apartado de cambiar a tema oscuro en el menú contextual.
     */
    public void cambiarTemaOscuro(ActionEvent event) {
        //Se obtiene el estilo del fondo.
        String estilo = stackPane.getStyle();

        //Se quita la imagen del fondo.
        String estiloNuevo = estilo.replace("-fx-background-image: url\\('.*'\\);", "");

        //Se añade al fondo la imagen con el tema oscuro
        stackPane.setStyle(estiloNuevo + "-fx-background-image: url('/img/imgFondoNegro.jpg');");
        //cambiar el boolean oscuro a true
        oscuro=true;
    }

    /**
     *
     * Cambia el tema del fondo a oscuro.
     *
     * @param event Evento que se dispara cuando el usuario hace clic en el
     * apartado de cambiar a tema oscuro en el menú contextual.
     */
    public void cambiarTemaClaro(ActionEvent event) {
        //Se obtiene el estilo del fondo.
        String estilo = stackPane.getStyle();

        //Se quita la imagen del fondo.
        String estiloNuevo = estilo.replace("-fx-background-image: url\\('.*'\\);", "");

        //Se añade al fondo la imagen con el tema oscuro
        stackPane.setStyle(estiloNuevo + "-fx-background-image: url('/img/imgFondo.jpg');");
        //cambiar el boolean oscuro a false
        oscuro=false;
    }
    private String ponerAsterisco(String nombre) {
        String ultimo = nombre.substring(nombre.length() - 1);
        if (!ultimo.equalsIgnoreCase("*")) {
            nombre=(nombre + " *");
        }
        return nombre;
    }
    /**
     * sirve para al ver contraseña pasar la informacion
     * @param event 
     */
    private void escribirPassEnTf(KeyEvent event) {
        tfPass.setText(pfPass.getText());
        tfPass2.setText(pfPass2.getText());
    }
    
    /**
     * sirve para al ver contraseña pasar la informacion hacia el otro lado
     * @param event 
     */
    private void escribirPassenPf(KeyEvent event) {
        pfPass.setText(tfPass.getText());
        pfPass2.setText(tfPass2.getText());
    }
  
    /**
     * Es el metodo que inicializa la ventana de registro, además es la que le
     * da las propiedades de recoger eventos a todos los botones.
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
            stage.setTitle("Registro");
            stage.setOnCloseRequest(this::closeAppFromX);
        });
          
       
        //Se crean los tooltips para todos los campos de texto.
        Tooltip tooltip = new Tooltip("Nombre y nos apellidos");
        tfNombre.setTooltip(tooltip);
        Tooltip tooltip2 = new Tooltip("Correo válido");
        tfCorreo.setTooltip(tooltip2);
        Tooltip tooltip3 = new Tooltip("Introduce solo numeros");
        tfZip.setTooltip(tooltip3);
        Tooltip tooltip4 = new Tooltip("Mínimo 6 caracteres minimo una letra mayuscula y otra minuscula y un número");
        tfPass.setTooltip(tooltip4);
        pfPass.setTooltip(tooltip4);
        tfPass2.setTooltip(tooltip4);
        pfPass2.setTooltip(tooltip4);
        
        
        pfPass.setOnKeyReleased(this::escribirPassEnTf);
        tfPass.setOnKeyReleased(this::escribirPassenPf);
        pfPass2.setOnKeyReleased(this::escribirPassEnTf);
        tfPass2.setOnKeyReleased(this::escribirPassenPf);
        //Se crea el menú contextual, el cual se mostrará si se hace clic con el 
        //botón izquierdo del ratón.
        ContextMenu contextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Cambiar a tema oscuro");
        item1.setOnAction(this::cambiarTemaOscuro);
        MenuItem item2 = new MenuItem("Cambiar a tema claro");
        item2.setOnAction(this::cambiarTemaClaro);
        contextMenu.getItems().addAll(item1, item2);

        bpPrincipal.setOnMouseClicked(event -> controlMenuConceptual(event, contextMenu));

        //Se añaden los listeners a todos los botones.
        hlSignIn.setOnAction(this::irSignIn);
        btnSalir.setOnAction(this::closeAppFromButton);
        btnVerPass.setOnAction(this::verPass);
        btnVerPass2.setOnAction(this::verPass2);
        btnSignUp.setOnAction(this::signUp);
        hlTienes.setOnAction(this::irSignIn);
        
        tfNombre.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    if (tfNombre.getText().equals("")) {
                        lblNombre.setStyle("-fx-text-fill:red");
                        lblNombre.setText(ponerAsterisco(lblNombre.getText()));
                    }

                }

            }
        });
        tfCorreo.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    if (tfCorreo.getText().equals("")) {
                        lblCorreo.setStyle("-fx-text-fill:red");
                        lblCorreo.setText(ponerAsterisco(lblCorreo.getText()));
                    } else if (!tfCorreo.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]+$")) {
                        //S
                        lblCorreo.setStyle("-fx-text-fill:red");
                    }
                }
            }
        });
        tfCiudad.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    if (tfCiudad.getText().equals("")) {
                        lblCiudad.setStyle("-fx-text-fill:red");
                        lblCiudad.setText(ponerAsterisco(lblCiudad.getText()));
                    }
                }
            }
        });
        tfCalle.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    if (tfCalle.getText().equals("")) {
                        lblCalle.setStyle("-fx-text-fill:red");
                        lblCalle.setText(ponerAsterisco(lblCalle.getText()));
                    }
                }
            }
        });
        tfZip.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    if (tfZip.getText().equals("")) {
                        lblZip.setStyle("-fx-text-fill:red");
                        lblZip.setText(ponerAsterisco(lblZip.getText()));
                    } else if (tfZip.getText().length() != 5 || !tfZip.getText().matches("\\d+")) {
                        //Si no tiene 5 números se muestra un mensaje.
                        lblZip.setStyle("-fx-text-fill:red");
                    }
                }
            }
        });
        pfPass.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    if (pfPass.getText().equals("")) {
                        lblPasswd.setStyle("-fx-text-fill:red");
                        lblPasswd.setText(ponerAsterisco(lblPasswd.getText()));
                    } else if (!pfPass.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$")) {
                        //Si no es válida se muestra un mensaje.
                        lblPasswd.setStyle("-fx-text-fill:red");
                    }
                }
            }
        });
         pfPass2.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    if (pfPass2.getText().equals("")) {
                        lblPasswd2.setStyle("-fx-text-fill:red");
                        lblPasswd2.setText(ponerAsterisco(lblPasswd2.getText()));
                    } else if (!pfPass2.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$")) {
                        //Si no es válida se muestra un mensaje.
                        lblPasswd2.setStyle("-fx-text-fill:red");
                    }
                }
            }
        });

    }
}
