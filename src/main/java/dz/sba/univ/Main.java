package dz.sba.univ;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import dz.sba.univ.controllers.LoginController;
import dz.sba.univ.controllers.RegisterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    public final FirebaseApp app;
    public final FirebaseAuth mAuth;

    private AnchorPane root;

    public Main() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("service-account-file.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        app = FirebaseApp.initializeApp(options);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void start(Stage stage) throws Exception {

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getClassLoader().getResource("dz.sba.univ/scene.fxml")));
        Scene scene = new Scene(root);

        stage.setTitle("Chat App");
        stage.setScene(scene);
        stage.show();

        showLoginView();
    }

    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }

    public void showRegisterView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("dz.sba.univ/register.fxml"));
            AnchorPane view = loader.load();

            root.getChildren().clear();
            root.getChildren().add(view);


            RegisterController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLoginView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("dz.sba.univ/login.fxml"));
            AnchorPane view = loader.load();

            root.getChildren().clear();
            root.getChildren().add(view);


            LoginController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean register(String name, String email, String password) {
        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setDisplayName(name)
                    .setEmail(email)
                    .setEmailVerified(true)
                    .setPassword(password)
                    .setDisabled(false);
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            System.out.println("Successfully created new user: " + userRecord.getUid());

            return true;

        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }

        return false;
    }
}
