package com.home;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CustomerPage {

    public void start(Stage stage) {
        Scene scene = new Scene(getView(stage), 1500, 800);
        stage.setTitle("glamONrent");
        stage.setScene(scene);
        stage.show();
    }

    public VBox getView(Stage stage) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: white;");
        layout.setPadding(new Insets(10));
        Label label = new Label("Dress like a dream, rent like a queen — only on glamONrent");
        label.setTextFill(Color.WHITE);
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font("Arial", 13));
        label.setPadding(new Insets(5));

        Region spacerTop = new Region();
        HBox.setHgrow(spacerTop, Priority.ALWAYS);

        Button aboutUsBtn = new Button("About Us");
        aboutUsBtn.setFont(Font.font("Arial", 12));
        aboutUsBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-cursor: hand;");

    aboutUsBtn.setOnAction(e -> {
    AboutUs aboutUsPage = new AboutUs();
    Scene aboutScene = new Scene(aboutUsPage.getView(() -> stage.setScene(new Scene(getView(stage), 1500, 800))), 1500, 800);
    stage.setScene(aboutScene);
    });

HBox topBar = new HBox(10, label, spacerTop, aboutUsBtn);
topBar.setStyle("-fx-background-color: black;");
topBar.setAlignment(Pos.CENTER_LEFT);
topBar.setPadding(new Insets(5));


        HBox topNav = new HBox(20);
        topNav.setPadding(new Insets(10));
        topNav.setAlignment(Pos.CENTER_LEFT);
        topNav.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0;");

        Label logo = new Label("glamONrent");
        logo.setFont(Font.font("Georgia", FontWeight.BOLD, 30));
        logo.setTextFill(Color.web("#C8A2C8"));

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search for outfits, jewelry, artists...");
        searchBar.setStyle("-fx-background-radius: 20; -fx-border-radius: 20; -fx-padding: 5 10 5 10;");
        searchBar.setPrefWidth(280);

        Button wishlist = new Button("♥");
        wishlist.setOnAction(e -> stage.setScene(new Scene(new WishlistPage().getView(() -> stage.setScene(new Scene(getView(stage), 1500, 800))), 1500, 800)));

        Button cart = new Button("🛒");
        cart.setOnAction(e -> stage.setScene(new Scene(new CartPage().getView(() -> stage.setScene(new Scene(getView(stage), 1500, 800))), 1500, 800)));

        Button profile = new Button("👤");
        profile.setOnAction(e -> stage.setScene(new Scene(
    new customerProfile().getView(stage, () -> stage.setScene(new Scene(getView(stage), 1500, 800))),
1500, 800)));


        Button orders = new Button("📦");
        orders.setOnAction(e -> {
        OrdersPage ordersPage = new OrdersPage();
        Scene scene = new Scene(ordersPage.getView(() -> stage.setScene(new Scene(getView(stage), 1500, 800))), 1500, 800);
        stage.setScene(scene);
});

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        topNav.getChildren().addAll(logo, spacer, searchBar, wishlist, cart, profile, orders);

        FlowPane categories = new FlowPane();
        categories.setHgap(30);
        categories.setVgap(10);
        categories.setPadding(new Insets(10));
        categories.setAlignment(Pos.CENTER);

        Button sarees = new Button("Western");
        sarees.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white;-fx-font-size: 14px;-fx-background-radius: 20;");
        sarees.setOnMouseEntered(e -> sarees.setStyle("-fx-background-color:#C8A2C8; -fx-text-fill: white; -fx-cursor: hand;"));
        sarees.setOnMouseExited(e -> sarees.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white; -fx-cursor: hand;"));
        sarees.setOnAction(e -> stage.setScene(new Scene(new SareesPage(stage).getView(() -> 
        stage.setScene(new Scene(new CustomerPage().getView(stage), 1500, 800))
    ), 1500, 800))
);
        Button gowns = new Button("Gowns");
        gowns.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white;-fx-font-size: 14px;-fx-background-radius: 20;");
        gowns.setOnMouseEntered(e -> gowns.setStyle("-fx-background-color:#C8A2C8; -fx-text-fill: white; -fx-cursor: hand;"));
        gowns.setOnMouseExited(e -> gowns.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white; -fx-cursor: hand;"));
        gowns.setOnAction(e -> stage.setScene(new Scene(new GownPage(stage).getView(() -> 
        stage.setScene(new Scene(new CustomerPage().getView(stage), 1500, 800))
    ), 1500, 800))
);

    Button AI = new Button("Glam-Bot");
        AI.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white;-fx-font-size: 14px;-fx-background-radius: 20;");
        AI.setOnMouseEntered(e -> AI.setStyle("-fx-background-color:#C8A2C8; -fx-text-fill: white; -fx-cursor: hand;"));
        AI.setOnMouseExited(e -> AI.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white; -fx-cursor: hand;"));
        AI.setOnAction(e -> stage.setScene(new Scene(new AIsuggestionPage(stage).getView(() -> 
        stage.setScene(new Scene(new CustomerPage().getView(stage), 1500, 800))
    ), 1500, 800))
);

        Button blouse = new Button("Blouse");
        blouse.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white;-fx-font-size: 14px;-fx-background-radius: 20;");
        blouse.setOnMouseEntered(e -> blouse.setStyle("-fx-background-color:#C8A2C8; -fx-text-fill: white; -fx-cursor: hand;"));
        blouse.setOnMouseExited(e -> blouse.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white; -fx-cursor: hand;"));
        blouse.setOnAction(e -> stage.setScene(new Scene(new BlousePage(stage).getView(() -> 
        stage.setScene(new Scene(new CustomerPage().getView(stage), 1500, 800))
    ), 1500, 800))
);

        Button lehengas = new Button("Lehengas");
        lehengas.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white;-fx-font-size: 14px;-fx-background-radius: 20;");
        lehengas.setOnMouseEntered(e -> lehengas.setStyle("-fx-background-color:#C8A2C8; -fx-text-fill: white; -fx-cursor: hand;"));
        lehengas.setOnMouseExited(e -> lehengas.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white; -fx-cursor: hand;"));
        lehengas.setOnAction(e -> stage.setScene(new Scene(new LehengasPage(stage).getView(() -> 
        stage.setScene(new Scene(new CustomerPage().getView(stage), 1500, 800))
    ), 1500, 800))
);


        Button western = new Button("Saree");
        western.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white;-fx-font-size: 14px;-fx-background-radius: 20;");
        western.setOnMouseEntered(e -> western.setStyle("-fx-background-color:#C8A2C8; -fx-text-fill: white; -fx-cursor: hand;"));
        western.setOnMouseExited(e -> western.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white; -fx-cursor: hand;"));
        western.setOnAction(e -> stage.setScene(new Scene(new WesternPage(stage).getView(() -> 
        stage.setScene(new Scene(new CustomerPage().getView(stage), 1500, 800))
    ), 1500, 800))
);

        Button suits = new Button("Suits");
        suits.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white;-fx-font-size: 14px;-fx-background-radius: 20;");
        suits.setOnMouseEntered(e -> suits.setStyle("-fx-background-color:#C8A2C8; -fx-text-fill: white; -fx-cursor: hand;"));
        suits.setOnMouseExited(e -> suits.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white; -fx-cursor: hand;"));
        suits.setOnAction(e -> stage.setScene(new Scene(new SuitsPage(stage).getView(() -> 
        stage.setScene(new Scene(new CustomerPage().getView(stage), 1500, 800))
    ), 1500, 800))
);

        Button golden = new Button("Golden Jewellery");
        golden.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white;-fx-font-size: 14px;-fx-background-radius: 20;");
        golden.setOnMouseEntered(e -> golden.setStyle("-fx-background-color:#C8A2C8; -fx-text-fill: white; -fx-cursor: hand;"));
        golden.setOnMouseExited(e -> golden.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white; -fx-cursor: hand;"));
        golden.setOnAction(e -> stage.setScene(new Scene(new GoldenJewelleryPage(stage).getView(() -> 
        stage.setScene(new Scene(new CustomerPage().getView(stage), 1500, 800))
    ), 1500, 800))
);
        Button diamond = new Button("Diamond Jewellery");
         diamond.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white;-fx-font-size: 14px;-fx-background-radius: 20;");
        diamond.setOnMouseEntered(e -> diamond.setStyle("-fx-background-color:#C8A2C8; -fx-text-fill: white; -fx-cursor: hand;"));
        diamond.setOnMouseExited(e -> diamond.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white; -fx-cursor: hand;"));
        diamond.setOnAction(e -> stage.setScene(new Scene(new DiamondJewelleryPage(stage).getView(() -> 
        stage.setScene(new Scene(new CustomerPage().getView(stage), 1500, 800))
    ), 1500, 800))
);
        Button makeup = new Button("Makeup Artist");
        makeup.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white;-fx-font-size: 14px;-fx-background-radius: 20;");
        makeup.setOnMouseEntered(e -> makeup.setStyle("-fx-background-color:#C8A2C8; -fx-text-fill: white; -fx-cursor: hand;"));
        makeup.setOnMouseExited(e -> makeup.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white; -fx-cursor: hand;"));
        makeup.setOnAction(e -> stage.setScene(new Scene(new MakeupArtistPage(stage).getView(() -> 
        stage.setScene(new Scene(new CustomerPage().getView(stage), 1500, 800))
    ), 1500, 800))
);
        categories.getChildren().addAll(sarees,gowns,western,suits,lehengas,blouse,golden,diamond,makeup,AI);

     Label sellBanner = new Label("✨Proudly  renting  our  boutique  collection  on  glamONrent  style  for  every  occasion✨");
        sellBanner.setFont(Font.font("Georgia", FontWeight.BOLD, 25));
        sellBanner.setTextFill(Color.BLACK);
        sellBanner.setStyle("-fx-cursor: hand;");


        FadeTransition fade = new FadeTransition(Duration.seconds(1.5), sellBanner);
        fade.setFromValue(1.0);
        fade.setToValue(0.4);
        fade.setCycleCount(FadeTransition.INDEFINITE);
        fade.setAutoReverse(true);
        fade.play();
        sellBanner.setOnMouseClicked(e -> {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Become a Seller");
        alert.setHeaderText(null);
        alert.setContentText("Redirecting to Seller Registration Page... (Coming Soon)");
        alert.show();
        });


    HBox banner = new HBox(sellBanner);
    banner.setAlignment(Pos.CENTER);
    banner.setPadding(new Insets(20));

    banner.setStyle("""
    -fx-background-color: linear-gradient(to right, #fdd8d4, #f9bdb6, #fce3db);
    -fx-background-radius: 18;
    -fx-padding: 15;
    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 15, 0.3, 0, 5);
    -fx-border-color: #f4c7bd;
    -fx-border-width: 1.5;
    -fx-border-radius: 18;
""");
     sellBanner.setStyle("""
    -fx-cursor: hand;
    -fx-effect: dropshadow(gaussian, rgba(255, 155, 155, 0.9), 8, 0.5, 0, 1);
""");

        HBox imageBox = new HBox(25);
        imageBox.setPadding(new Insets(20));
        imageBox.setAlignment(Pos.CENTER_LEFT);

        String[] imagePaths = {
                "assets/Images/saree50.jpg",
                "assets/Images/suits1.jpg",
                "assets/Images/lehanga42.jpg",
                "assets/Images/diamond8.jpg",
                "assets/Images/laven.jpg",
                "assets/Images/saree51.jpg",
                "assets/Images/gold23.jpg",
                "assets/Images/lavendar.jpg",
                "assets/Images/sareebanner.jpg",
                "assets/Images/goldbanner.jpg",
                "assets/Images/lehanga41.jpg"
        };

        for (String path : imagePaths) {
            Image image = new Image(path);
            ImageView img = new ImageView(image);
            img.setFitWidth(300);
            img.setFitHeight(450);
            img.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.08), 5, 0, 0, 4);");
            imageBox.getChildren().add(img);
        }

        ScrollPane scrollPane = new ScrollPane(imageBox);
        scrollPane.setPrefHeight(500);
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        Timeline timeline = new Timeline();
        Duration delay = Duration.seconds(0.5);
        int totalImages = imagePaths.length;
        for (int i = 0; i < totalImages; i++) {
            int index = i;
            timeline.getKeyFrames().add(new KeyFrame(delay, e -> {
                double scrollTo = (double) index / (totalImages - 1);
                scrollPane.setHvalue(scrollTo);
            }));
            delay = delay.add(Duration.seconds(1.5));
        }
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        layout.getChildren().addAll(topBar, topNav, categories, banner, scrollPane);
        return layout;
    }


     
}




