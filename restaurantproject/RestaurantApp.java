package apply;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class RestaurantApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label welcomeLabel = new Label("Welcome to Can-Do Crew Restaurant");
        welcomeLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: darkred; -fx-font-weight: bold;");
        Button reserveBtn = new Button("Reserve Table");
        Button orderBtn = new Button("Place Order");
        Button adminBtn = new Button("Admin Panel");
        Button feedbackBtn = new Button("Feedback");
        Button exitBtn = new Button("Exit");
        reserveBtn.setOnAction(e -> new ReserveTableForm().start(new Stage()));
        orderBtn.setOnAction(e -> new PlaceOrderForm().start(new Stage()));
        adminBtn.setOnAction(e -> new AdminPanel().start(new Stage()));
        feedbackBtn.setOnAction(e -> new FeedbackForm().start(new Stage()));
        exitBtn.setOnAction(e -> {
            Alert exitAlert = new Alert(Alert.AlertType.INFORMATION);
            exitAlert.setTitle("Thank You");
            exitAlert.setHeaderText("Thanks for visiting Can-Do Crew Restaurant!");
            exitAlert.showAndWait();
            primaryStage.close();
        });
        VBox root = new VBox(20, welcomeLabel, reserveBtn, orderBtn, adminBtn, feedbackBtn, exitBtn);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 40;");
        Scene scene = new Scene(root, 500, 450);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Can-Do Crew Restaurant");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}