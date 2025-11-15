package com.home;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class NavBar {
    private Stage stage;

    public NavBar(Stage stage) {
        this.stage = stage;
    }

    public HBox getBar() {
        HBox bar = new HBox(100);

        TextField search = new TextField();
        search.setPromptText("Search...");
        search.setPrefWidth(600);

        Button wishlistBtn = new Button("❤️");
        wishlistBtn.setOnAction(e -> {
            WishlistPage wishlistPage = new WishlistPage();
            Scene scene = new Scene(wishlistPage.getView(() -> stage.setScene(new Scene(new CustomerPage().getView(stage), 1500, 800))), 1500, 800);
            stage.setScene(scene);
        });

        Button cartBtn = new Button("🛒");
        cartBtn.setOnAction(e -> {
            CartPage cartPage = new CartPage();
            Scene scene = new Scene(cartPage.getView(() -> stage.setScene(new Scene(new CustomerPage().getView(stage), 1500, 800))), 1500, 800);
            stage.setScene(scene);
        });

        // Button profileBtn = new Button("👤");
        // profileBtn.setOnAction(e -> {
        //     customerProfile profilePage = new customerProfile();
        //     Scene scene = new Scene(profilePage.getView(() -> stage.setScene(new Scene(new CustomerPage().getView(stage), 1500, 800))), 1500, 800);
        //     stage.setScene(scene);
        // });

        Button profileBtn = new Button("👤");
profileBtn.setOnAction(e -> {
    customerProfile profilePage = new customerProfile();
    Scene scene = new Scene(profilePage.getView(stage, () -> 
        stage.setScene(new Scene(new CustomerPage().getView(stage), 1500, 800))
    ), 1500, 800);
    stage.setScene(scene);
});

        Button ordersBtn = new Button("📦");
        ordersBtn.setOnAction(e -> {
            OrdersPage ordersPage = new OrdersPage();
            Scene scene = new Scene(ordersPage.getView(() -> stage.setScene(new Scene(new CustomerPage().getView(stage), 1500, 800))), 1500, 800);
            stage.setScene(scene);
        });

        Button homeBtn = new Button("🏠");
        homeBtn.setOnAction(e -> {
            Scene scene = new Scene(new CustomerPage().getView(stage), 1500, 800);
            stage.setScene(scene);
        });

        bar.getChildren().addAll(search, homeBtn, wishlistBtn, cartBtn, profileBtn, ordersBtn);
        bar.setAlignment(Pos.TOP_LEFT);
        return bar;
    }
}


