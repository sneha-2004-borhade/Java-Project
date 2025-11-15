package com.home;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import javafx.scene.Parent;

public class AboutUs {

        private Text createNormalText(String content) {
    Text text = new Text(content);
    text.setFont(Font.font("Segoe UI", FontWeight.MEDIUM, 17));
    text.setFill(Color.web("#3B2C35"));
    return text;
}

        private Text boldText(String content) {
    Text text = new Text(content);
    text.setFont(Font.font("Segoe UI", FontWeight.BOLD, 17)); 
    text.setFill(Color.web("#3B2C35"));
    return text;
}


    public Parent getView(Runnable onBack) {
        VBox mainBox = new VBox(20);
        mainBox.setPadding(new Insets(30));
        mainBox.setStyle("-fx-background-color: #FFF9F3;");

        VBox card = new VBox(20);
        card.setPadding(new Insets(30));
        card.setStyle("-fx-background-color: white; -fx-background-radius: 20;");
        card.setAlignment(Pos.TOP_LEFT);

        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(4.0);
        shadow.setColor(Color.web("rgba(93, 57, 84, 0.2)"));
        card.setEffect(shadow);

        
        FadeTransition fade = new FadeTransition(Duration.millis(800), card);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        fade.play();

        ImageView logo = new ImageView(new Image(getClass().getResource("/assets/images/C2Wlogo.jpg").toExternalForm()));
        logo.setFitWidth(120);
        logo.setPreserveRatio(true);
      
        Text texLabel = new Text("glamONrent");
        texLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 30));
        texLabel.setFill(Color.web("#C8A2C8"));

        Label welcome = createContentLabel("Welcome to Glam On Rent!\n\nWe are your go-to destination for premium ethnic and western wear on rent. Whether it’s a wedding, festive event, or a glamorous party — why buy expensive outfits for one-time use when you can rent stunning designer wear at affordable rates?");

        
        Label visionTitle = createSectionHeader("💡Our Vision");
        Label visionText = createContentLabel("To revolutionize the way people dress for special occasions — by promoting fashion sustainability through outfit rentals.");


        Label whyTitle = createSectionHeader("🌿Why Choose Us");
        Label whyList = createContentLabel(
                "• High-quality designer outfits\n" +
                "• Affordable rental pricing\n" +
                "• Easy booking & return process\n" +
                "• Cleaned & sanitized garments\n" +
                "• Style without the stress of ownership");

        
        Label howTitle = createSectionHeader("👗How It Works");
        Label howList = createContentLabel(
                "→ Browse & select your desired outfit\n" +
                "→ Pick rental duration\n" +
                "→ Confirm your booking\n" +
                "→ Receive, wear & slay it\n" +
                "→ Return hassle-free");

        Label team = createSectionHeader("Team : AlphaAchievers");
        Label teamlist = createContentLabel(
                "Team members - \n" +
                "• Vaishnavi Ghuge\n" +
                "• Sneha Borhade\n" +
                "• Shraddha Hande\n");

        ImageView sirView = new ImageView(new Image(getClass().getResource("/assets/images/SirImage.jpg").toExternalForm()));
        sirView.setFitWidth(120);
        sirView.setPreserveRatio(true);

        
        Label thankyou = createSectionHeader(" A Special Thank You ");

TextFlow thankyouText = new TextFlow();
thankyouText.setLineSpacing(5);
thankyouText.setPrefWidth(1000);

thankyouText.getChildren().addAll(
    createNormalText("We extend our heartfelt gratitude to "),
    boldText("Shashi Sir"),
    createNormalText(" for his constant guidance, support, and encouragement throughout the development of this project. "
        + "His insights, mentorship, and unwavering belief in us played a vital role in bringing this idea to life.\n"),

    createNormalText("We are also sincerely thankful to our instructors — "),
    boldText("Pramod Sir"), createNormalText(", "),
    boldText("Sachin Sir"), createNormalText(", "),
    boldText("Akshay Sir"),
    createNormalText(" — as well as our mentors "),
    boldText("Shiv Sir"), createNormalText(" and "),
    boldText("Subodh Sir"),
    createNormalText(", and our team lead "),
    boldText("Samruddhi Dhongade"),
    createNormalText(".\n\n"),

    createNormalText("This project would not have been possible without the collective efforts of our entire team. "
        + "Every brainstorming session, late-night debugging, and collaborative discussion brought us one step closer to our vision.\n"),

    createNormalText("Throughout this journey, we not only built a project but also evolved as learners, problem-solvers, and team players.\n\n"),

    createNormalText("Thank you, Sir, and the entire team, for inspiring us to push our boundaries and for being a true guiding force in our journey.")
);


        Button backBtn = new Button("← Back");
        backBtn.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-font-size: 16px;");
        backBtn.setOnMouseEntered(e -> backBtn.setStyle("-fx-background-color:#8743abff; -fx-text-fill: white; -fx-cursor: hand;"));
        backBtn.setOnMouseExited(e -> backBtn.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-cursor: hand;"));
        backBtn.setOnAction(e -> onBack.run());

        HBox hb = new HBox( 40,sirView,logo);

        card.getChildren().addAll(
    texLabel,welcome,
    visionTitle, visionText,
    whyTitle, whyList,
    howTitle, howList,
    team, teamlist,thankyou,
    thankyouText, hb 
);


        mainBox.getChildren().addAll(card, backBtn);

        ScrollPane scrollPane = new ScrollPane(mainBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #FFF9F3;");

        return scrollPane;
    }

    private Label createSectionHeader(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        label.setTextFill(Color.WHITE);
        label.setPadding(new Insets(6, 12, 6, 12));
        label.setStyle("-fx-background-color: #C8A2C8; -fx-background-radius: 10;");
        label.setAlignment(Pos.CENTER_LEFT);
        return label;
    }

    private Label createContentLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Segoe UI Medium", 16));
        label.setTextFill(Color.web("#3B2C35"));
        label.setWrapText(true);
        return label;
    }
}



