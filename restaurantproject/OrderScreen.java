package apply;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class OrderScreen {
    private final ObservableList<MenuItem> menuItems = FXCollections.observableArrayList();
    private final List<MenuItem> selectedItems = new ArrayList<>();
    private final String customerName;
    public OrderScreen(String customerName) {
        this.customerName = customerName;
        loadMenu();
    }
    private void loadMenu() {
        menuItems.addAll(
            new MenuItem(1, "Veg Biryani", 180, true),
            new MenuItem(2, "Chicken Burger", 150, true),
            new MenuItem(3, "Cold Coffee", 90, true),
            new MenuItem(4, "Ice Cream", 100, true),
            new MenuItem(5, "Butter Naan", 30, true),
            new MenuItem(6, "Butter Chicken", 300, true),
            new MenuItem(7, "Mojito", 200, true),
            new MenuItem(8, "Special Dish", 500, true)
        );
    }
    public void show(Stage primaryStage) {
        primaryStage.setTitle("Place Your Order");
        ListView<MenuItem> menuList = new ListView<>(menuItems);
        menuList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(MenuItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.name + " - ₹" + item.price);
                }
            }
        });
        Button addButton = new Button("Add to Order");
        Button payButton = new Button("Pay Now");

        Label totalLabel = new Label("Total: ₹0");

        addButton.setOnAction(e -> {
            MenuItem selected = menuList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selectedItems.add(selected);
                updateTotal(totalLabel);
            }
        });
        payButton.setOnAction(e -> {
            double total = getTotal();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Payment");
            alert.setHeaderText("Total Amount: ₹" + total);
            alert.setContentText("Confirm payment?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Alert paid = new Alert(Alert.AlertType.INFORMATION);
                    paid.setTitle("Success");
                    paid.setHeaderText("Payment Successful!");
                    paid.setContentText("Thank you for your order.");
                    paid.show();
                    System.out.println("Order Paid at: " + LocalDateTime.now());
                    selectedItems.clear();
                    updateTotal(totalLabel);
                }
            });
        });
        VBox layout = new VBox(10, menuList, addButton, totalLabel, payButton);
        layout.setPadding(new Insets(10));
        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void updateTotal(Label totalLabel) {
        double total = getTotal();
        totalLabel.setText("Total: ₹" + total);
    }
    private double getTotal() {
        return selectedItems.stream().mapToDouble(item -> item.price).sum();
    }
    public static class MenuItem {
        int id;
        String name;
        double price;
        boolean available;
        public MenuItem(int id, String name, double price, boolean available) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.available = available;
        }
        @Override
        public String toString() {
            return name + " - ₹" + price;
        }
    }
}