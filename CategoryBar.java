package com.home;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CategoryBar {
    
    public HBox getBar(Stage stage) {
        HBox bar = new HBox(30);
        bar.setStyle("-fx-background-color: #f5f5f5;");

       Button sareesBtn = new Button("Sarees");
        sareesBtn.setStyle("-fx-background-color: #d71351ae; -fx-text-fill: white;");
        sareesBtn.setOnAction(e -> {
            SareesPage sareesPage = new SareesPage(stage);
            Scene sareesScene = new Scene(sareesPage.getView(() -> {
                CustomerPage homePage = new CustomerPage();
                Scene homeScene = new Scene(homePage.getView(stage), 1500, 800);
                stage.setScene(homeScene);
            }), 1500, 800);
            stage.setScene(sareesScene);
        });

        Button gownsBtn = new Button("Gowns");
        gownsBtn.setStyle("-fx-background-color: #d71351ae; -fx-text-fill: white;");
        gownsBtn.setOnAction(e -> {
            GownPage gownsPage = new GownPage(stage);
            Scene gownsScene = new Scene(gownsPage.getView(() -> {
                CustomerPage homePage = new CustomerPage();
                Scene homeScene = new Scene(homePage.getView(stage), 1500, 800);
                stage.setScene(homeScene);
            }), 1500, 800);
            stage.setScene(gownsScene);
        });

        Button blouseBtn = new Button("Blouse");
        blouseBtn.setStyle("-fx-background-color: #d71351ae; -fx-text-fill: white;");
        blouseBtn.setOnAction(e -> {
            BlousePage blousePage = new BlousePage(stage);
            Scene blouseScene = new Scene(blousePage.getView(() -> {
                CustomerPage homePage = new CustomerPage();
                Scene homeScene = new Scene(homePage.getView(stage), 1500, 800);
                stage.setScene(homeScene);
            }), 1500, 800);
            stage.setScene(blouseScene);
        });

        Button lehengasBtn = new Button("Lehengas");
        lehengasBtn.setStyle("-fx-background-color: #d71351ae; -fx-text-fill: white;");
        lehengasBtn.setOnAction(e -> {
            LehengasPage lehengasPage = new LehengasPage(stage);
            Scene lehengasScene = new Scene(lehengasPage.getView(() -> {
                CustomerPage homePage = new CustomerPage();
                Scene homeScene = new Scene(homePage.getView(stage), 1500, 800);
                stage.setScene(homeScene);
            }), 1500, 800);
            stage.setScene(lehengasScene);
        });

        Button westernBtn = new Button("Western");
        westernBtn.setStyle("-fx-background-color: #d71351ae; -fx-text-fill: white;");
        westernBtn.setOnAction(e -> {
            WesternPage westernPage = new WesternPage(stage);
            Scene westernScene = new Scene(westernPage.getView(() -> {
                CustomerPage homePage = new CustomerPage();
                Scene homeScene = new Scene(homePage.getView(stage), 1500, 800);
                stage.setScene(homeScene);
            }), 1500, 800);
            stage.setScene(westernScene);
        });

        Button suitsBtn = new Button("Suits");
        suitsBtn.setStyle("-fx-background-color: #d71351ae; -fx-text-fill: white;");
        suitsBtn.setOnAction(e -> {
            SuitsPage suitsPage = new SuitsPage(stage);
            Scene suitsScene = new Scene(suitsPage.getView(() -> {
                CustomerPage homePage = new CustomerPage();
                Scene homeScene = new Scene(homePage.getView(stage), 1500, 800);
                stage.setScene(homeScene);
            }), 1500, 800);
            stage.setScene(suitsScene);
        });

        Button goldenJewelleryBtn = new Button("Golden Jewellery");
        goldenJewelleryBtn.setStyle("-fx-background-color: #d71351ae; -fx-text-fill: white;");
        goldenJewelleryBtn.setOnAction(e -> {
            GoldenJewelleryPage page = new GoldenJewelleryPage(stage);
            Scene scene = new Scene(page.getView(() -> {
                CustomerPage home = new CustomerPage();
                Scene homeScene = new Scene(home.getView(stage), 1500, 800);
                stage.setScene(homeScene);
            }), 1500, 800);
            stage.setScene(scene);
        });

        Button diamondJewelleryBtn = new Button("Diamond Jewellery");
        diamondJewelleryBtn.setStyle("-fx-background-color: #d71351ae; -fx-text-fill: white;");
        diamondJewelleryBtn.setOnAction(e -> {
            DiamondJewelleryPage page = new DiamondJewelleryPage(stage);
            Scene scene = new Scene(page.getView(() -> {
                CustomerPage home = new CustomerPage();
                Scene homeScene = new Scene(home.getView(stage), 1500, 800);
                stage.setScene(homeScene);
            }), 1500, 800);
            stage.setScene(scene);
        });

        Button makeupArtistBtn = new Button("Makeup-Artist");
        makeupArtistBtn.setStyle("-fx-background-color: #d71351ae; -fx-text-fill: white;");
        makeupArtistBtn.setOnAction(e -> {
            MakeupArtistPage page = new MakeupArtistPage(stage);
            Scene scene = new Scene(page.getView(() -> {
                CustomerPage home = new CustomerPage();
                Scene homeScene = new Scene(home.getView(stage), 1500, 800);
                stage.setScene(homeScene);
            }), 1500, 800);
            stage.setScene(scene);
        });

        bar.getChildren().addAll(
            westernBtn
        );

        return bar;
    }
}
