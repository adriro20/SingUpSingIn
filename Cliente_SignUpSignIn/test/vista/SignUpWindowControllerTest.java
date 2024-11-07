/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controller.Main;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 * testb para comprobar la si todo funciona de manera correcta en la vista
 * viewSingUP
 *
 * @author 2dam
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpWindowControllerTest extends ApplicationTest {

    /**
     * Abre la vista ViewSingIn
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        new Main().start(stage);
        clickOn("#hlCrear");
    }

    /**
     * abre la ventana ViewSingUp y comprueba que el registro se hace de manera
     * correcta
     */
    @Test
    public void A_testSingUpOK() {
        clickOn("#tfNombre");
        write("Elena Martinez");
        clickOn("#tfCorreo");
        write("elena.mart@email.com");
        clickOn("#tfZip");
        write("08025");
        clickOn("#tfCiudad");
        write("Barcelona");
        clickOn("#tfCalle");
        write("Calle de la Marina");
        clickOn("#pfPass");
        write("Elena2024!");
        clickOn("#pfPass2");
        write("Elena2024!");
        clickOn("#btnSignUp");
        verifyThat("#btnCerrarSesion", isVisible());
    }

    /**
     * comprueba si el email está introducido en la BD
     */
    @Test
    public void B_testSingUpError() {
        clickOn("#tfNombre");
        write("Elena Martinez");
        clickOn("#tfCorreo");
        write("elena.mart@email.com");
        clickOn("#tfZip");
        write("08025");
        clickOn("#tfCiudad");
        write("Barcelona");
        clickOn("#tfCalle");
        write("Calle de la Marina");
        clickOn("#pfPass");
        write("Elena2024!");
        clickOn("#pfPass2");
        write("Elena2024!");
        clickOn("#btnSignUp");
        verifyThat("#btnCerrarSesion", isVisible());
    }
    
    /**
     * Registra un usuario no activo, para hacer comprobacion en el testSignIn
     */
    @Test
    public void C_testSingUpOKNotActive() {
        clickOn("#tfNombre");
        write("Andres Gomez");
        clickOn("#tfCorreo");
        write("andresgomez@gmail.com");
        clickOn("#tfZip");
        write("28014");
        clickOn("#tfCiudad");
        write("Madrid");
        clickOn("#tfCalle");
        write("Avenida de la Constitucion");
        clickOn("#pfPass");
        write("SolVerde8");
        clickOn("#pfPass2");
        write("SolVerde8");
        clickOn("#btnSignUp");
        verifyThat("#btnCerrarSesion", isVisible());
    }
    
    /**
     * Registra un usuario no activo, para hacer comprobacion en el testSignIn
     */
    @Test
    public void C_testSingUpPassDistintas() {
        clickOn("#tfNombre");
        write("Andres Gomez");
        clickOn("#tfCorreo");
        write("andresgomez@gmail.com");
        clickOn("#tfZip");
        write("28014");
        clickOn("#tfCiudad");
        write("Madrid");
        clickOn("#tfCalle");
        write("Avenida de la Constitucion");
        clickOn("#pfPass");
        write("SolAzul5");
        clickOn("#btnVerPass");
        clickOn("#pfPass2");
        write("SolVerde8");
        clickOn("#btnVerPass2");
        clickOn("#btnSignUp");
        verifyThat("La contraseña no es igual en los dos campos", isVisible());
        clickOn("Aceptar");
    }

    /**
     * comprueba el alert que salta cuando un campo está vacío
     */
    @Test
    public void D_testSingUpErrorCamposVacios() {
        clickOn("#tfCorreo");
        write("elena.mart@email.com");
        clickOn("#tfZip");
        write("08025");
        clickOn("#tfCiudad");
        write("Barcelona");
        clickOn("#tfCalle");
        write("Calle de la Marina");
        clickOn("#pfPass");
        write("Elena2024!");
        clickOn("#pfPass2");
        write("Elena2024!");
        clickOn("#btnSignUp");
        verifyThat("Te Falta algun campo por rellenar", isVisible());
        clickOn("Aceptar");

        clickOn("#tfNombre");
        write("Elena Martinez");
        clickOn("#tfCorreo");
        eraseText(24);
        clickOn("#btnSignUp");
        verifyThat("Te Falta algun campo por rellenar", isVisible());
        clickOn("Aceptar");

        clickOn("#tfCorreo");
        write("elena.mart@email.com");
        clickOn("#tfZip");
        eraseText(5);
        clickOn("#btnSignUp");
        verifyThat("Te Falta algun campo por rellenar", isVisible());
        clickOn("Aceptar");

        clickOn("#tfZip");
        write("08025");
        clickOn("#tfCiudad");
        eraseText(9);
        clickOn("#btnSignUp");
        verifyThat("Te Falta algun campo por rellenar", isVisible());
        clickOn("Aceptar");

        clickOn("#tfCiudad");
        write("Barcelona");
        clickOn("#tfCalle");
        eraseText(19);
        clickOn("#btnSignUp");
        verifyThat("Te Falta algun campo por rellenar", isVisible());
        clickOn("Aceptar");

        clickOn("#tfCalle");
        write("Calle de la Marina");
        clickOn("#pfPass");
        eraseText(10);
        clickOn("#btnSignUp");
        verifyThat("Te Falta algun campo por rellenar", isVisible());
        clickOn("Aceptar");

        
        clickOn("#pfPass");
        write("Elena2024!");
        clickOn("#pfPass2");
        eraseText(10);
        clickOn("#btnSignUp");
        verifyThat("Te Falta algun campo por rellenar", isVisible());
        clickOn("Aceptar");
    }
    
    @Test
    public void testSingUpPasswdNoValid(){
        clickOn("#tfNombre");
        write("Elena Martinez");
        clickOn("#tfCorreo");
        write("elena.mart@email.com");
        clickOn("#tfZip");
        write("08025");
        clickOn("#tfCiudad");
        write("Barcelona");
        clickOn("#tfCalle");
        write("Calle de la Marina");
        clickOn("#btnVerPass");
        clickOn("#btnVerPass2");
       
        //verificar si no tiene mayusculas
        clickOn("#tfPass");
        write("test1234");
        clickOn("#tfPass2");
        write("test1234");
        clickOn("#btnSignUp");
        verifyThat("La contraseña no es valida", isVisible());
        clickOn("Aceptar");
       
    }
    
     /**
     * abre la ventana ViewSingUp y
     * comrpueba si el codigo postal son numeros o no
     */
    @Test
    public void testSingUpErrorZip() {
        clickOn("#tfNombre");
        write("Elena Martinez");
        clickOn("#tfCorreo");
        write("elena.mart@email.com");
        clickOn("#tfZip");
        write("48i10");
        clickOn("#tfCiudad");
        write("Barcelona");
        clickOn("#tfCalle");
        write("Calle de la Marina");
        clickOn("#pfPass");
        write("Test1234");
        clickOn("#pfPass2");
        write("Test1234");    
        clickOn("#btnSignUp");
        verifyThat("Codigo Postal Incorrecto", isVisible());
        clickOn("Aceptar");
    }
}
