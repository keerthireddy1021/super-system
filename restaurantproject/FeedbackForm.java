package apply;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class FeedbackForm {
    private static ObservableList<String> feedbackHistory = FXCollections.observableArrayList();
    public static ObservableList<String> getFeedbackHistory() {
        return feedbackHistory;
    }
    public void start(Stage stage) {
        Label title = new Label("Customer Feedback");
        title.getStyleClass().add("form-title");
        Label nameLabel = new Label("Customer Name:");
        TextField nameField = new TextField();
        Label feedbackLabel = new Label("Feedback:");
        TextArea feedbackArea = new TextArea();
        feedbackArea.setWrapText(true);
        feedbackArea.setPrefRowCount(5);
        Button submitBtn = new Button("Submit Feedback");
        VBox vbox = new VBox(15, title, nameLabel, nameField, feedbackLabel, feedbackArea, submitBtn);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);
        submitBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String feedback = feedbackArea.getText().trim();
            if (name.isEmpty() || feedback.isEmpty()) {
                showAlert("Error", "Please enter both name and feedback.");
                return;
            }
            feedbackHistory.add("Customer: " + name + "\nFeedback: " + feedback + "\n");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thank You");
            alert.setHeaderText("Feedback Submitted");
            alert.setContentText("Thanks for your feedback, " + name + "!");
            alert.showAndWait();
            nameField.clear();
            feedbackArea.clear();
        });
        Scene scene = new Scene(vbox, 400, 400);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Feedback - Can-Do Crew Restaurant");
        stage.setScene(scene);
        stage.show();
    }
    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}