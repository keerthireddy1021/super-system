package apply;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.HashSet;
import java.util.Set;
public class ReserveTableForm {
    private static ObservableList<String> reservationHistory = FXCollections.observableArrayList();
    private static Set<String> reservedTables = new HashSet<>();
    public static ObservableList<String> getReservationHistory() {
        return reservationHistory;
    }
    public void start(Stage stage) {
        Label title = new Label("Reserve a Table");
        title.setStyle("-fx-font-size: 24px; -fx-text-fill: darkgreen;");
        Label nameLabel = new Label("Customer Name:");
        TextField nameField = new TextField();
        Label tableLabel = new Label("Select Table:");
        ComboBox<String> tableBox = new ComboBox<>();
        for (int i = 1; i <= 10; i++) {
            tableBox.getItems().add("Table " + i);
        }
        Button reserveBtn = new Button("Reserve Table");
        VBox layout = new VBox(15, title, nameLabel, nameField, tableLabel, tableBox, reserveBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        reserveBtn.setOnAction(e -> {
            String customerName = nameField.getText().trim();
            String table = tableBox.getValue();
            if (customerName.isEmpty() || table == null) {
                showAlert("Error", "Please enter your name and select a table.");
                return;
            }
            if (reservedTables.contains(table)) {
                showAlert("Unavailable", table + " is already reserved. Please choose another table.");
                return;
            }
            reservedTables.add(table);
            reservationHistory.add("Customer: " + customerName + "\nReserved: " + table + "\n");
            showAlert("Success", "Table reserved successfully for " + customerName + "!");
        });
        Scene scene = new Scene(layout, 400, 350);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Table Reservation - Can-Do Crew Restaurant");
        stage.setScene(scene);
        stage.show();
    }
    private void showAlert(String title, String message) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(message);
        a.showAndWait();
    }
}