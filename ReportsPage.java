package com.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class ReportsPage {

    public VBox getView(Runnable onBack) {
        VBox root = new VBox(30);
        root.setPadding(new Insets(50));
        root.setStyle("-fx-background-color: #FFF9F3;");
        root.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("📊 Reports Dashboard");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        title.setTextFill(Color.web("#5D3954"));
        title.setPadding(new Insets(10, 0, 20, 0));

        VBox statsContainer = new VBox(20);
        statsContainer.setAlignment(Pos.CENTER);

        updateStats(statsContainer);

        Button back = new Button("← Back");
        back.setFont(Font.font("Arial", 16));
        back.setStyle("-fx-background-color: #C8A2C8; -fx-text-fill: white;");
       // back.setAlignment(Pos.TOP_LEFT);
        back.setEffect(new DropShadow());
        back.setOnAction(e -> onBack.run());
        VBox.setMargin(back, new Insets(40, 0, 0, 0));

        HBox backBox = new HBox(back);
backBox.setAlignment(Pos.TOP_LEFT);
VBox.setMargin(backBox, new Insets(40, 0, 0, 20));

        root.getChildren().addAll(backBox,title, statsContainer);
        return root;
    }

   

    private void updateStats(VBox statsContainer) {
    new Thread(() -> {
        ReportController.fetchSummaryFromFirestore();

        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

        ReportController controller = new ReportController();
        ReportController.ReportData data = controller.fetchReportData();

        javafx.application.Platform.runLater(() -> {
            statsContainer.getChildren().clear();
            statsContainer.setPrefWidth(80);
            statsContainer.setPrefHeight(70);
            statsContainer.getChildren().addAll(
                createStat("Total Bookings", String.valueOf(data.totalBookings)),
                createStat("Total Revenue", "₹" + data.totalRevenue),
                createStat("Completed Bookings", String.valueOf(data.completedBookings)),
                createStat("Pending Bookings", String.valueOf(data.pendingBookings))
            );
        });
    }).start();
}


    private HBox createStat(String label, String value) {
        Label keyLabel = new Label(label + ":");
        keyLabel.setFont(Font.font("Arial", FontWeight.MEDIUM, 20));
        keyLabel.setTextFill(Color.web("#3B2C35"));

        Label valLabel = new Label(value);
        valLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        valLabel.setTextFill(Color.web("#5D3954"));

        HBox box = new HBox(15, keyLabel, valLabel);
        box.setPadding(new Insets(10));
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-border-radius: 12; -fx-effect: dropshadow(gaussian, rgba(93, 57, 84, 0.15), 8, 0, 0, 4);");
        box.setPrefWidth(400);
        return box;
    }
}