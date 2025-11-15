/*package com.home;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Select_role {

    public Stage primaryStage;
    public Scene page1Scene;

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    public VBox createScene(Runnable back) {
        Text txt1 = new Text("Select Role");
        txt1.setStyle("-fx-font-size: 50px;-fx-font-weight: bold;");
        txt1.setFont(Font.font("Georgia"));
        txt1.setFill(Color.PURPLE);
double buttonWidth = 300;

Button customerBtn = new Button("Customer");
customerBtn.setPrefHeight(70);
customerBtn.setPrefWidth(buttonWidth);
customerBtn.setStyle(
    "-fx-background-color: #d09cd0ff;" +
    "-fx-font-weight: bold;" +
    "-fx-font-size: 25;" +
    "-fx-text-fill: white;" +
    "-fx-background-radius: 20;"
);
customerBtn.setOnMousePressed(e ->
    customerBtn.setStyle("-fx-background-color: #a05fa0; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 20;")
);

customerBtn.setOnMouseReleased(e ->
    customerBtn.setStyle("-fx-background-color: #d09cd0; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 20;")
);

Button shopOwnerBtn = new Button("Rental Shop Owner");
shopOwnerBtn.setPrefHeight(70);
shopOwnerBtn.setPrefWidth(buttonWidth);
shopOwnerBtn.setStyle(
    "-fx-background-color: #d09cd0ff;" +
    "-fx-font-weight: bold;" +
    "-fx-font-size: 25;" +
    "-fx-text-fill: white;" +
    "-fx-background-radius: 20;"
);
shopOwnerBtn.setOnMousePressed(e ->
    shopOwnerBtn.setStyle("-fx-background-color: #a05fa0; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 20;")
);

shopOwnerBtn.setOnMouseReleased(e ->
    shopOwnerBtn.setStyle("-fx-background-color: #d09cd0; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 20;")
);


        customerBtn.setOnAction(e -> {
            SignUp signUpPage = new SignUp();
            signUpPage.setStage(primaryStage);
            SignIn signInPage = new SignIn();
            signInPage.setStage(primaryStage);

            Runnable backToSelectRole = () -> {
                VBox selectRoleRoot = this.createScene(() -> {});
                primaryStage.setScene(new Scene(selectRoleRoot, 1500, 800));
            };

            Runnable[] goToSignIn = new Runnable[1];
            goToSignIn[0] = () -> {
                VBox signInRoot = signInPage.createScene(() -> {
                    VBox backToSignUp = signUpPage.createScene(backToSelectRole, goToSignIn[0]);
                    primaryStage.setScene(new Scene(backToSignUp, 1500, 800));
                });
                primaryStage.setScene(new Scene(signInRoot, 1500, 800));
            };

            VBox signUpRoot = signUpPage.createScene(backToSelectRole, goToSignIn[0]);
            primaryStage.setScene(new Scene(signUpRoot, 1500, 800));
        });

        // ✅ SHOP OWNER BUTTON CLICK ➝ OwnerSignUp with 2-way nav
        shopOwnerBtn.setOnAction(e -> {
            OwnerSignUp ownerSignUpPage = new OwnerSignUp();
            ownerSignUpPage.setStage(primaryStage);
            OwnerSignIN ownerSignInPage = new OwnerSignIN();
            ownerSignInPage.setStage(primaryStage);

            Runnable backToSelectRole = () -> {
                VBox selectRoleRoot = this.createScene(() -> {});
                primaryStage.setScene(new Scene(selectRoleRoot, 1500, 800));
            };

            Runnable[] goToSignIn = new Runnable[1];
            goToSignIn[0] = () -> {
                VBox signInRoot = ownerSignInPage.createScene(() -> {
                    VBox backToSignUp = ownerSignUpPage.createScene(backToSelectRole, goToSignIn[0]);
                    primaryStage.setScene(new Scene(backToSignUp, 1500, 800));
                });
                primaryStage.setScene(new Scene(signInRoot, 1500, 800));
            };

            VBox signUpRoot = ownerSignUpPage.createScene(backToSelectRole, goToSignIn[0]);
            primaryStage.setScene(new Scene(signUpRoot, 1500, 800));
        });

        VBox vb1 = new VBox(40, txt1, customerBtn, shopOwnerBtn);
        vb1.setAlignment(Pos.CENTER);
        return vb1;
    }
}*/





package com.home;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Select_role {

    public Stage primaryStage;
    public Scene page1Scene;

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    public VBox createScene(Runnable back) {
        Text txt1 = new Text("Select Role");
        txt1.setStyle("-fx-font-size: 50px;-fx-font-weight: bold;");
        txt1.setFont(Font.font("Georgia"));
        txt1.setFill(Color.PURPLE);
double buttonWidth = 300;

Button customerBtn = new Button("Customer");
customerBtn.setPrefHeight(70);
customerBtn.setPrefWidth(buttonWidth);
customerBtn.setStyle(
    "-fx-background-color: #d09cd0ff;" +
    "-fx-font-weight: bold;" +
    "-fx-font-size: 25;" +
    "-fx-text-fill: white;" +
    "-fx-background-radius: 20;"
);
customerBtn.setOnMousePressed(e ->
    customerBtn.setStyle("-fx-background-color: #a05fa0; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 20;")
);

customerBtn.setOnMouseReleased(e ->
    customerBtn.setStyle("-fx-background-color: #d09cd0; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 20;")
);

Button shopOwnerBtn = new Button("Rental Shop Owner");
shopOwnerBtn.setPrefHeight(70);
shopOwnerBtn.setPrefWidth(buttonWidth);
shopOwnerBtn.setStyle(
    "-fx-background-color: #d09cd0ff;" +
    "-fx-font-weight: bold;" +
    "-fx-font-size: 25;" +
    "-fx-text-fill: white;" +
    "-fx-background-radius: 20;"
);
shopOwnerBtn.setOnMousePressed(e ->
    shopOwnerBtn.setStyle("-fx-background-color: #a05fa0; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 20;")
);

shopOwnerBtn.setOnMouseReleased(e ->
    shopOwnerBtn.setStyle("-fx-background-color: #d09cd0; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 20;")
);


        customerBtn.setOnAction(e -> {
            SignUp signUpPage = new SignUp();
            signUpPage.setStage(primaryStage);
            SignIn signInPage = new SignIn();
            signInPage.setStage(primaryStage);

            Runnable backToSelectRole = () -> {
                VBox selectRoleRoot = this.createScene(() -> {});
                primaryStage.setScene(new Scene(selectRoleRoot, 1500, 800));
            };

            Runnable[] goToSignIn = new Runnable[1];
            goToSignIn[0] = () -> {
                VBox signInRoot = signInPage.createScene(() -> {
                    VBox backToSignUp = signUpPage.createScene(backToSelectRole, goToSignIn[0]);
                    primaryStage.setScene(new Scene(backToSignUp, 1500, 800));
                });
                primaryStage.setScene(new Scene(signInRoot, 1500, 800));
            };

            VBox signUpRoot = signUpPage.createScene(backToSelectRole, goToSignIn[0]);
            primaryStage.setScene(new Scene(signUpRoot, 1500, 800));
        });

        // ✅ SHOP OWNER BUTTON CLICK ➝ OwnerSignUp with 2-way nav
        shopOwnerBtn.setOnAction(e -> {
            OwnerSignUp ownerSignUpPage = new OwnerSignUp();
            ownerSignUpPage.setStage(primaryStage);
            OwnerSignIN ownerSignInPage = new OwnerSignIN();
            ownerSignInPage.setStage(primaryStage);

            Runnable backToSelectRole = () -> {
                VBox selectRoleRoot = this.createScene(() -> {});
                primaryStage.setScene(new Scene(selectRoleRoot, 1500, 800));
            };

            Runnable[] goToSignIn = new Runnable[1];
            goToSignIn[0] = () -> {
                VBox signInRoot = ownerSignInPage.createScene(() -> {
                    VBox backToSignUp = ownerSignUpPage.createScene(backToSelectRole, goToSignIn[0]);
                    primaryStage.setScene(new Scene(backToSignUp, 1500, 800));
                });
                primaryStage.setScene(new Scene(signInRoot, 1500, 800));
            };

            VBox signUpRoot = ownerSignUpPage.createScene(backToSelectRole, goToSignIn[0]);
            primaryStage.setScene(new Scene(signUpRoot, 1500, 800));
        });

        Button artistBtn = new Button("MakeUP Artist");
artistBtn.setPrefHeight(70);
artistBtn.setPrefWidth(buttonWidth);
artistBtn.setStyle(
    "-fx-background-color: #d09cd0ff;" +
    "-fx-font-weight: bold;" +
    "-fx-font-size: 25;" +
    "-fx-text-fill: white;" +
    "-fx-background-radius: 20;"
);
artistBtn.setOnMousePressed(e ->
    artistBtn.setStyle("-fx-background-color: #a05fa0; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 20;")
);

artistBtn.setOnMouseReleased(e ->
    artistBtn.setStyle("-fx-background-color: #d09cd0; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 20;")
);


artistBtn.setOnAction(e -> {
            ArtistSignUp artistSignUpPage = new ArtistSignUp();
            artistSignUpPage.setStage(primaryStage);
            ArtistSignIN artistSignInPage = new ArtistSignIN();
            artistSignInPage.setStage(primaryStage);

            Runnable backToSelectRole = () -> {
                VBox selectRoleRoot = this.createScene(() -> {});
                primaryStage.setScene(new Scene(selectRoleRoot, 1500, 800));
            };

            Runnable[] goToSignIn = new Runnable[1];
            goToSignIn[0] = () -> {
                VBox signInRoot = artistSignInPage.createScene(() -> {
                    VBox backToSignUp = artistSignUpPage.createScene(backToSelectRole, goToSignIn[0]);
                    primaryStage.setScene(new Scene(backToSignUp, 1500, 800));
                });
                primaryStage.setScene(new Scene(signInRoot, 1500, 800));
            };

            VBox signUpRoot = artistSignUpPage.createScene(backToSelectRole, goToSignIn[0]);
            primaryStage.setScene(new Scene(signUpRoot, 1500, 800));
        });

        VBox vb1 = new VBox(40, txt1, customerBtn, shopOwnerBtn,artistBtn);
        vb1.setAlignment(Pos.CENTER);
        return vb1;
    }

}


