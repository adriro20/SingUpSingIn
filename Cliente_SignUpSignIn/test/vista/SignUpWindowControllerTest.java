/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 *testb para comprobar la si todo funciona de 
 * manera correcta en la vista viewSingUP
 * @author 2dam
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpWindowControllerTest extends ApplicationTest{
    
    public SignUpWindowControllerTest() {
    }
    /**
     * Abre la vista ViewSingIn
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception{
        new Main().start(stage);
    }
    
    /**
     * abre la ventana ViewSingUp y 
     * comprueba que el registro se hace de manera correcta
     */
    @Test
    public void testSingUpOK() {
        clickOn("#hlCrear");
        clickOn("#tfNombre");
        write("Diu garcia");
        clickOn("#tfCorreo");
        write("test@gmail.com");
        clickOn("#tfZip");
        write("48610");
        clickOn("#tfCiudad");
        write("Urduliz");
        clickOn("#tfCalle");
        write("calle");
        clickOn("#cbActive");
        clickOn("#pfPass");
        write("Test1234");
        clickOn("#pfPass2");
        write("Test1234");
        clickOn("#cbActive");
        clickOn("#btnSignUp");
        verifyThat("#btnCerrarSesion", isVisible());
    }
    
    /**
     * comprueba que comprueba si el email ya esta
     */
    @Test
    public void testSingUpError(){
        clickOn("#tfNombre");
        write("Diu garcia");
        clickOn("#tfCorreo");
        write("test@gmail.com");
        clickOn("#tfZip");
        write("48610");
        clickOn("#tfCiudad");
        write("Urduliz");
        clickOn("#tfCalle");
        write("calle");
        clickOn("#pfPass");
        write("Test1234");
        clickOn("#pfPass2");
        write("Test1234");
        clickOn("#btnSignIn");
        verifyThat("El correo con el que intentas registrarte ya existe.", isVisible());
        clickOn("OK");
    }
}
