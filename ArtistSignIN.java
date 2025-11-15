package com.home;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class ArtistSignIN {

    OwnerController authcontroller = new OwnerController();
    Scene p2Scene;
    Stage p2Stage;

    public void setScene(Scene scene) {
        this.p2Scene = scene;
    }

    public void setStage(Stage stage) {
        this.p2Stage = stage;
    }

    public VBox createScene(Runnable back) {
        VBox vb = new VBox(15);
        vb.setPadding(new Insets(30));
        vb.setSpacing(20);
        vb.setStyle("-fx-background-color: white; -fx-background-radius: 20; -fx-effect: dropshadow(gaussian, gray, 10, 0.2, 0, 2);");
        vb.setAlignment(Pos.CENTER);

        Label title = new Label("Owner Sign In");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        title.setTextFill(Color.web("#C8A2C8"));

        Label subtitle = new Label("Hi! Welcome back, you've been missed");
        subtitle.setWrapText(true);
        subtitle.setTextFill(Color.GRAY);
        subtitle.setFont(Font.font("Arial", 12));

        Label emailLabel = new Label("Email");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter Your Email");
        emailField.setMaxWidth(300);
        emailField.setPrefHeight(30);
        emailField.setStyle("-fx-background-radius: 20; -fx-border-color: #C8A2C8; -fx-border-radius: 20;");

        Label passLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Your Password");
        passwordField.setMaxWidth(300);
        passwordField.setPrefHeight(30);
        passwordField.setStyle("-fx-background-radius: 20; -fx-border-color: #C8A2C8; -fx-border-radius: 20;");

        HBox passwordRow = new HBox(10);
        passwordRow.setAlignment(Pos.CENTER);
        Label eyeIcon = new Label("👁");
        eyeIcon.setStyle("-fx-cursor: hand; -fx-text-fill: #C8A2C8;");
        Hyperlink forgotPassword = new Hyperlink("Forgot Password?");
        forgotPassword.setTextFill(Color.BLACK);
        passwordRow.getChildren().addAll(eyeIcon, forgotPassword);

        Button signInBtn = new Button("SIGN IN");
        Label resultlabel = new Label();
        signInBtn.setMaxWidth(150);
        signInBtn.setPrefHeight(40);
        signInBtn.setStyle("-fx-background-color: #C8A2C8; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20;");

        signInBtn.setOnAction(event -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            boolean success = authcontroller.authenticateUser(email, password, false);

            if (success) {
                OwnerHomePage homePage = new OwnerHomePage();
                homePage.start(p2Stage);
            } else {
                resultlabel.setText("Invalid email or password.");
            }
        });

        // ✅ Sign Up link
        HBox signupBox = new HBox();
        signupBox.setAlignment(Pos.CENTER);
        signupBox.setSpacing(5);
        Label noAccount = new Label("Don’t have an account?");
        Hyperlink signupLink = new Hyperlink("Sign Up");
        signupLink.setTextFill(Color.web("#C8A2C8"));
        signupLink.setOnAction(e -> back.run());  // Call back to go to OwnerSignUp
        signupBox.getChildren().addAll(noAccount, signupLink);

        vb.getChildren().addAll(
                title, subtitle,
                emailLabel, emailField,
                passLabel, passwordField,
                passwordRow,
                resultlabel,
                signInBtn,
                signupBox
        );

        return vb;
    }
}


