package apply;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;
public class PlaceOrderForm {
    private static ObservableList<String> orderHistory = FXCollections.observableArrayList();
    public static ObservableList<String> getOrderHistory() {
        return orderHistory;
    }
    public void start(Stage stage) {
        Label title = new Label("Place Order");
        title.setStyle("-fx-font-size: 24px; -fx-text-fill: darkblue;");
        Label nameLabel = new Label("Customer Name:");
        TextField nameField = new TextField();
        Map<String, Integer> menu = new LinkedHashMap<>();
        menu.put("Burger", 100);
        menu.put("Pizza", 150);
        menu.put("Pasta", 120);
        menu.put("Sandwich", 80);
        menu.put("Fries", 60);
        menu.put("Coke", 40);
        VBox itemBox = new VBox(5);
        List<CheckBox> itemCheckboxes = new ArrayList<>();
        for (String item : menu.keySet()) {
            CheckBox cb = new CheckBox(item + " - ₹" + menu.get(item));
            itemCheckboxes.add(cb);
            itemBox.getChildren().add(cb);
        }
        ScrollPane scrollPane = new ScrollPane(itemBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(150);
        Button placeOrderBtn = new Button("Place Order");
        VBox vbox = new VBox(15, title, nameLabel, nameField, new Label("Select Items:"), scrollPane, placeOrderBtn);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);
        placeOrderBtn.setOnAction(e -> {
            String customerName = nameField.getText().trim();
            List<String> selectedItems = new ArrayList<>();
            final int[] total = {0};
            for (CheckBox cb : itemCheckboxes) {
                if (cb.isSelected()) {
                    String item = cb.getText().split(" - ")[0];
                    selectedItems.add(item);
                    total[0] += menu.get(item);
                }
            }
            if (customerName.isEmpty() || selectedItems.isEmpty()) {
                showAlert("Error", "Please enter your name and select at least one item.");
                return;
            }
            boolean paymentDone = false;
            while (!paymentDone) {
                TextInputDialog payDialog = new TextInputDialog();
                payDialog.setTitle("Payment");
                payDialog.setHeaderText("Your total bill is ₹" + total[0]);
                payDialog.setContentText("Please pay exact amount (₹" + total[0] + "):");
                var result = payDialog.showAndWait();
                if (result.isPresent()) {
                    try {
                        int paid = Integer.parseInt(result.get().trim());
                        if (paid == total[0]) {
                            Alert processing = new Alert(Alert.AlertType.INFORMATION);
                            processing.setTitle("Processing");
                            processing.setHeaderText("Processing Payment...");
                            processing.setContentText("Please wait...");
                            processing.showAndWait();
                            Alert success = new Alert(Alert.AlertType.INFORMATION);
                            success.setTitle("Success");
                            success.setHeaderText("Payment Successful");
                            success.setContentText("Thank you for your payment, " + customerName + "!");
                            success.showAndWait();
                            Alert placed = new Alert(Alert.AlertType.INFORMATION);
                            placed.setTitle("Order Placed");
                            placed.setHeaderText(null);
                            placed.setContentText("Your order has been placed successfully!");
                            placed.showAndWait();
                            Alert served = new Alert(Alert.AlertType.INFORMATION);
                            served.setTitle("Order Served");
                            served.setHeaderText(null);
                            served.setContentText("Your order has been served. Enjoy your meal!");
                            served.showAndWait();
                            orderHistory.add("Customer: " + customerName +
                                    "\nItems: " + String.join(", ", selectedItems) +
                                    "\nTotal: ₹" + total[0] + "\n");
                            paymentDone = true;
                        } else {
                            showAlert("Incorrect Amount", "Please enter the exact amount: ₹" + total[0]);
                        }
                    } catch (NumberFormatException ex) {
                        showAlert("Invalid Input", "Please enter a valid number.");
                    }
                } else {
                    break; 
                }
            }
        });
        Scene scene = new Scene(vbox, 400, 520);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Place Order - Can-Do Crew Restaurant");
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