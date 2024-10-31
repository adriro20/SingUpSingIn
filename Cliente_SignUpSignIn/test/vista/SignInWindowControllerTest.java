/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javafx.stage.Stage;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 *
 * @author 2dam
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignInWindowControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new Main().start(stage);
    }

    @Test
    public void testSignInOK() {
        //Datos guardados en la BD
        clickOn("#tfCorreo");
        write("mnbv@gmail.com");
        clickOn("#pfPass");
        write("Abcd*1234");
        clickOn("#btnSignIn");

        verifyThat("#btnCerrarSesion", isVisible());
    }

    @Test
    public void testSignInErrorCredencialesIncorrectas() {
        //Datos no guardados en la BD
        clickOn("#tfCorreo");
        write("asofhre@gmail.org");
        clickOn("#pfPass");
        write("Abcd*1234");
        clickOn("#btnSignIn");

        verifyThat("El correo y/o la contraseña no coinciden con el de ningún usuario registrado.", isVisible());

        clickOn("Aceptar");

    }

    @Test
    public void testSignInErrorUserNotActive() {
        //Datos no guardados en la BD
        clickOn("#tfCorreo");
        write("azul@gmail.com");
        clickOn("#pfPass");
        write("AZsxdc12");
        clickOn("#btnSignIn");

        verifyThat("El usuario no está activo.", isVisible());

        clickOn("Aceptar");

    }

    @Test
    public void testSignInErrorCamposVacios() {
        //Datos no guardados en la BD
        clickOn("#tfCorreo");
        write("azul@gmail.com");
        clickOn("#btnSignIn");
        verifyThat("Los campos no pueden estar vacíos", isVisible());
        clickOn("Aceptar");

        clickOn("#tfCorreo");
        eraseText(14);
        clickOn("#pfPass");
        write("AZsxdc12");
        clickOn("#btnSignIn");
        verifyThat("Los campos no pueden estar vacíos", isVisible());
        clickOn("Aceptar");

        clickOn("#pfPass");
        eraseText(8);
        clickOn("#btnSignIn");
        verifyThat("Los campos no pueden estar vacíos", isVisible());
        clickOn("Aceptar");
    }

}
