package dz.sba.univ.controllers;

import dz.sba.univ.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button loginBtn;

    @FXML
    private Button registerBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void handleRegister() throws IOException {
        mainApp.showRegisterView();
    }
}
