package dz.sba.univ.controllers;

import com.google.firebase.auth.FirebaseAuthException;
import dz.sba.univ.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    TextField nameTextField;

    @FXML
    TextField emailTextField;

    @FXML
    TextField passwordTextField;

    @FXML
    Button loginBtn;

    @FXML
    Button registerBtn;

    private Main mainApp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void handleRegister() {
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();

        if (mainApp.register(name, email, password)) {
            try {
                System.out.println("user uid :" + mainApp.mAuth.getUserByEmail(email).getUid());
            } catch (FirebaseAuthException e) {
                e.printStackTrace();
            }
        }
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void handleLogin() throws IOException {
        mainApp.showLoginView();
    }
}
