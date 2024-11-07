/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controller.Main;
import javafx.stage.Stage;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 *
 * @author 2dam
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InternalServerErrorTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new Main().start(stage);
    }

    @Test
    public void testSignInInternalServerError() {

        clickOn("#tfCorreo");
        write("mnbv@gmail.com");
        clickOn("#pfPass");
        write("Abcd*1234");
        clickOn("#btnSignIn");

        verifyThat("Error interno del servidor.", isVisible());
        clickOn("Aceptar");

    }

    @Test
    public void testSignUpInternalServerError() {
        clickOn("#hlCrear");

        clickOn("#tfNombre");
        write("Diu garcia");
        clickOn("#tfCorreo");
        write("test3@gmail.com");
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

        verifyThat("Error interno del servidor.", isVisible());

        clickOn("Aceptar");
    }

}
