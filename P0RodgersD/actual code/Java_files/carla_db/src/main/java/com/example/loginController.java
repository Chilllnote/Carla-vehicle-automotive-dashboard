package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import com.example.PostgresRepository;

public class loginController {

    @FXML
    private Button loginButton;

    @FXML
    private Button quitButton;

    @FXML
    private Label logMessage;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField enterPasswordField;

    private windowStateInformation stateInfo;

    private PostgresRepository postRepo = new PostgresRepository();

    @FXML
    public void initialize() {
        System.out.println("Controller loaded!");
    }

    public void loginButtonOnAction(ActionEvent event) throws Exception {

        Boolean Login = validateLogin();

        if (Login) {
            openSecondWindow();
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        }
    }

    public void quitButtonOnAction(ActionEvent event) {
        // Retrieve the "Stage" in which the button belongs to
        Stage stage = (Stage) quitButton.getScene().getWindow();

        // Close the stage.
        stage.close();
    }

    public Boolean validateLogin() throws Exception {
        PostgresRepository repo = new PostgresRepository();
        String username = usernameTextField.getText();
        String pass = enterPasswordField.getText();

        boolean userExists = repo.findUser(username);

        if (userExists) {
            logMessage.setText("User found!");
            if (repo.findUserPass(username, pass)) {
                logMessage.setText("password and user match!");
                return true;
            } else {
                logMessage.setText("Password does not match. Please enter new pass");
                return false;
            }
        } else {
            logMessage.setText("User not found");
            return false;
        }
    }

    public void openSecondWindow() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard.fxml"));
            Parent root = loader.load();

            dashboardController controller = loader.getController();
            controller.setEmployeeID(this.postRepo.getEmployeeIDByUsername(this.usernameTextField.getText()));

            Stage stage = new Stage();
            stage.setTitle("Employee Dashboard");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setStateInfo(windowStateInformation stateInfo) {
        this.stateInfo = stateInfo;
    }
}
