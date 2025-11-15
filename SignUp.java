// package com.home;


// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
// import javafx.geometry.*;
// import javafx.scene.Scene;
// import javafx.scene.control.*;
// import javafx.scene.layout.*;
// import javafx.scene.paint.Color;
// import javafx.scene.text.*;
// import javafx.stage.Stage;

// public class SignUp {
//     Scene page1Scene, page2Scene, page3Scene;
//     public Stage primaryStage; 
    
//     public VBox createScene (Runnable back) {

//         VBox vb = new VBox(15);
//         vb.setPadding(new Insets(30));
//         vb.setSpacing(20);
//         vb.setAlignment(Pos.CENTER);
//         vb.setStyle("-fx-background-color: white; -fx-background-radius: 20; -fx-effect: dropshadow(gaussian, gray, 10, 0.2, 0, 2);");

//         Label title = new Label("Create Account");
//         title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
//         title.setTextFill(Color.web("#C8A2C8"));

//         Label subtitle = new Label("Fill your information below");
//         subtitle.setWrapText(true);
//         subtitle.setTextFill(Color.GRAY);
//         subtitle.setFont(Font.font("Arial", 12));

//         Label nameLabel = new Label("Name");
//         TextField nameField = new TextField();
//         nameField.setPromptText("Enter Your Name");
//         nameField.setMaxWidth(300);
//         nameField.setPrefHeight(30);
//         nameField.setStyle("-fx-background-radius: 20; -fx-border-color: #C8A2C8; -fx-border-radius: 20;");

//         Label emailLabel = new Label("Email");
//         TextField emailField = new TextField();
//         emailField.setPromptText("Enter Your Email");
//         emailField.setMaxWidth(300);
//         emailField.setPrefHeight(30);
//         emailField.setStyle("-fx-background-radius: 20; -fx-border-color: #C8A2C8; -fx-border-radius: 20;");

//         Label passLabel = new Label("Password");
//         PasswordField passField = new PasswordField();
//         passField.setPromptText("Enter Your Password");
//         passField.setMaxWidth(300);
//         passField.setPrefHeight(30);
//         passField.setStyle("-fx-background-radius: 20; -fx-border-color: #C8A2C8; -fx-border-radius: 20;");

//         CheckBox termsBox = new CheckBox("Agree with");
//         Hyperlink termsLink = new Hyperlink("Terms & Conditions");
//         termsLink.setTextFill(Color.web("#C8A2C8"));
//         termsLink.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
//         HBox termsBoxContainer = new HBox(5, termsBox, termsLink);
//         termsBoxContainer.setAlignment(Pos.CENTER);

//         Button signUpBtn = new Button("Sign Up");
//         signUpBtn.setMaxWidth(150);
//         signUpBtn.setPrefHeight(40);
//         signUpBtn.setAlignment(Pos.CENTER);
//         signUpBtn.setStyle("-fx-background-color: #C8A2C8; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20;");

//         signUpBtn.setOnAction(new EventHandler<ActionEvent>() {
//             @Override
//             public void handle(ActionEvent arg0) {
//                 String email = emailField.getText().trim();
//                 String password = passField.getText().trim();
//                 ApiController api = new ApiController();
//                 boolean success = api.authenticateUser(email, password, true);

//               if (success) {
//               initializePage3();
//               primaryStage.setScene(page3Scene);
//               } else {
//                 showAlert(Alert.AlertType.ERROR, "Sign Up Failed", "Something went wrong. Please try again.");
//               }

//             }
//         });

//         HBox signinBox = new HBox();
//         Label already = new Label("Already have an account?");
//         Hyperlink signInLink = new Hyperlink("Sign In");
//         signInLink.setTextFill(Color.web("#C8A2C8"));
//         signInLink.setFont(Font.font("Arial", FontWeight.BOLD, 12));
//         // signInLink.setOnAction(e -> {
//         //     initializePage1();
//         //     primaryStage.setScene(page2Scene);
//         // });


//         signInLink.setOnAction(e -> {
//            back.run();
//         });



//         // ⬅ Back Button to go back to Select_role
//         Button backButton = new Button("⬅ Back");
//         backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #C8A2C8; -fx-font-size: 14; -fx-font-weight: bold;");
//         backButton.setOnAction(e -> back.run());
//         backButton.setAlignment(Pos.TOP_LEFT);


//         signinBox.getChildren().addAll(already, signInLink);
//         signinBox.setAlignment(Pos.CENTER);
//         signinBox.setSpacing(5);

//         vb.getChildren().addAll(
//                 title, subtitle,
//                 nameLabel, nameField,
//                 emailLabel, emailField,
//                 passLabel, passField,
//                 termsBoxContainer,
//                 signUpBtn,
//                 signinBox,backButton
//         );
//         return vb;      
// }

//     private void initializePage1() {
//     SignIn page2 = new SignIn();
//     page2.setStage(primaryStage);  
//     page2Scene = new Scene(page2.createScene(this::handleBackButton), 1500, 800);
//     page2.setScene(page2Scene);    
//     primaryStage.setScene(page2Scene);
// }
//     private void handleBackButton() {
//         primaryStage.setScene(page1Scene);
//     }

// private void initializePage3() {
//     Profile page3 = new Profile();

//     page3Scene = new Scene(page3.createScene(() -> {
//         SignIn signIn = new SignIn();
//         signIn.setStage(primaryStage);
//         Scene signInScene = new Scene(signIn.createScene(() -> {
//         }), 1500, 800);
//         primaryStage.setScene(signInScene);
//         primaryStage.setTitle("Login");
//     }), 1500, 800);

//     page3.setScene(page3Scene);
//     page3.setStage(primaryStage);
//     primaryStage.setScene(page3Scene);
// }
//      private void showAlert(Alert.AlertType type, String title, String message) {
//         Alert alert = new Alert(type);
//         alert.setTitle(title);
//         alert.setHeaderText(null);
//         alert.setContentText(message);
//         alert.showAndWait();
//     }

//      public void setStage(Stage primaryStage) {
//         this.primaryStage = primaryStage;
// }




    
// }



package com.home;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class SignUp {
    Scene page3Scene;
    public Stage primaryStage;

    // ✅ Accept two Runnables
    public VBox createScene(Runnable backToSelectRole, Runnable goToSignIn) {
        VBox vb = new VBox(15);
        vb.setPadding(new Insets(30));
        vb.setSpacing(20);
        vb.setAlignment(Pos.CENTER);
        vb.setStyle("-fx-background-color: white; -fx-background-radius: 20; -fx-effect: dropshadow(gaussian, gray, 10, 0.2, 0, 2);");

        Label title = new Label("Create Account");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextFill(Color.web("#C8A2C8"));

        Label subtitle = new Label("Fill your information below");
        subtitle.setWrapText(true);
        subtitle.setTextFill(Color.GRAY);
        subtitle.setFont(Font.font("Arial", 12));

        Label nameLabel = new Label("Name");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Your Name");
        nameField.setMaxWidth(300);
        nameField.setPrefHeight(30);
        nameField.setStyle("-fx-background-radius: 20; -fx-border-color: #C8A2C8; -fx-border-radius: 20;");

        Label emailLabel = new Label("Email");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter Your Email");
        emailField.setMaxWidth(300);
        emailField.setPrefHeight(30);
        emailField.setStyle("-fx-background-radius: 20; -fx-border-color: #C8A2C8; -fx-border-radius: 20;");

        Label passLabel = new Label("Password");
        PasswordField passField = new PasswordField();
        passField.setPromptText("Enter Your Password");
        passField.setMaxWidth(300);
        passField.setPrefHeight(30);
        passField.setStyle("-fx-background-radius: 20; -fx-border-color: #C8A2C8; -fx-border-radius: 20;");

        CheckBox termsBox = new CheckBox("Agree with");
        Hyperlink termsLink = new Hyperlink("Terms & Conditions");
        termsLink.setTextFill(Color.web("#C8A2C8"));
        termsLink.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        HBox termsBoxContainer = new HBox(5, termsBox, termsLink);
        termsBoxContainer.setAlignment(Pos.CENTER);

        Button signUpBtn = new Button("Sign Up");
        signUpBtn.setMaxWidth(150);
        signUpBtn.setPrefHeight(40);
        signUpBtn.setAlignment(Pos.CENTER);
        signUpBtn.setStyle("-fx-background-color: #C8A2C8; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20;");

        signUpBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                String email = emailField.getText().trim();
                String password = passField.getText().trim();
                ApiController api = new ApiController();
                boolean success = api.authenticateUser(email, password, true);

                if (success) {
                    initializePage3();
                    primaryStage.setScene(page3Scene);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Sign Up Failed", "Something went wrong. Please try again.");
                }
            }
        });

        HBox signinBox = new HBox();
        Label already = new Label("Already have an account?");
        Hyperlink signInLink = new Hyperlink("Sign In");
        signInLink.setTextFill(Color.web("#C8A2C8"));
        signInLink.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        signInLink.setOnAction(e -> goToSignIn.run()); // ✅ go to SignIn

        signinBox.getChildren().addAll(already, signInLink);
        signinBox.setAlignment(Pos.CENTER);
        signinBox.setSpacing(5);

        Button backButton = new Button("⬅ Back");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #C8A2C8; -fx-font-size: 14; -fx-font-weight: bold;");
        backButton.setOnAction(e -> backToSelectRole.run()); // ✅ back to Select_role

        vb.getChildren().addAll(
                title, subtitle,
                nameLabel, nameField,
                emailLabel, emailField,
                passLabel, passField,
                termsBoxContainer,
                signUpBtn,
                signinBox,
                backButton
        );
        return vb;
    }

    private void initializePage3() {
        Profile page3 = new Profile();
        page3Scene = new Scene(page3.createScene(() -> {
            SignIn signIn = new SignIn();
            signIn.setStage(primaryStage);
            Scene signInScene = new Scene(signIn.createScene(() -> {}), 1500, 800);
            primaryStage.setScene(signInScene);
            primaryStage.setTitle("Login");
        }), 1500, 800);

        page3.setScene(page3Scene);
        page3.setStage(primaryStage);
        primaryStage.setScene(page3Scene);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
