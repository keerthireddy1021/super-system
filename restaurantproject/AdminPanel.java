package apply;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
public class AdminPanel {
    public void start(Stage stage) {
        Label title = new Label("Admin Panel");
        title.getStyleClass().add("form-title");
        Label orderLabel = new Label("Order History:");
        ListView<String> orderList = new ListView<>(PlaceOrderForm.getOrderHistory());
        orderList.setPrefHeight(120);
        Label tableLabel = new Label("Reserved Tables:");
        ListView<String> tableList = new ListView<>(ReserveTableForm.getReservationHistory());
        tableList.setPrefHeight(120);
        Label feedbackLabel = new Label("Customer Feedback:");
        ListView<String> feedbackList = new ListView<>(FeedbackForm.getFeedbackHistory());
        feedbackList.setPrefHeight(120);
        Label paymentLabel = new Label("Payment History:");
        ListView<String> paymentList = new ListView<>(PlaceOrderForm.getOrderHistory());
        paymentList.setPrefHeight(120);
        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> stage.close());
        VBox layout = new VBox(15,
                title,
                orderLabel, orderList,
                tableLabel, tableList,
                feedbackLabel, feedbackList,
                paymentLabel, paymentList,
                closeBtn
        );
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER_LEFT);
        Scene scene = new Scene(layout, 600, 800);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Admin Panel - Can-Do Crew");
        stage.setScene(scene);
        stage.show();
    }
}