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
 *
 * @author 2dam
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignInWindowControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception{
        new Main().start(stage);
    }
    
    @Test
    public void testSignInOK(){
        //Datos guardados en la BD
        clickOn("#tfCorreo");
        write("julen@gmail.com");
        clickOn("#pfPass");
        write("Abcd*1234");
        clickOn("#btnSignIn");
        
        verifyThat("#btnCerrarSesion",isVisible());
        
    }
    
    @Test
    public void testSignInError(){
        //Datos no guardados en la BD
        clickOn("#tfCorreo");
        write("julen@gmail.org");
        clickOn("#pfPass");
        write("Abcd*1234");
        clickOn("#btnSignIn");
        
        verifyThat("El correo y/o la contraseña no coinciden con el de ningún usuario registrado.",isVisible());
        
        clickOn("OK");
        
    }

}
