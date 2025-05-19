import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.paint.Color;

public class LiftGameGUI extends Application {

    private Label statusLabel = new Label("Lift is on Floor 0 🟩");
    private int currentFloor = 0;
    private int totalFloors = 10;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("🚀 Lift Simulator with Emergency Features");

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        Label title = new Label("🏢 Lift Game System UI");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextFill(Color.DARKBLUE);

        GridPane floorButtons = new GridPane();
        floorButtons.setHgap(10);
        floorButtons.setVgap(10);

        for (int i = 0; i <= totalFloors; i++) {
            int floor = i;
            Button floorButton = new Button("Go to Floor " + i);
            floorButton.setOnAction(e -> moveLift(floor));
            floorButtons.add(floorButton, i % 5, i / 5);
        }

        Button emergencyBtn = new Button("🚨 Emergency Call");
        emergencyBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        emergencyBtn.setOnAction(e -> handleEmergency());

        Button helpBtn = new Button("🆘 Help");
        helpBtn.setOnAction(e -> showHelp());

        HBox bottomMenu = new HBox(20, emergencyBtn, helpBtn);

        statusLabel.setFont(Font.font("Consolas", 18));
        statusLabel.setTextFill(Color.GREEN);

        root.getChildren().addAll(title, floorButtons, statusLabel, bottomMenu);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void moveLift(int floor) {
        statusLabel.setTextFill(Color.ORANGE);
        statusLabel.setText("🚧 Moving from Floor " + currentFloor + " to Floor " + floor);
        currentFloor = floor;
        new Thread(() -> {
            try {
                Thread.sleep(1500);
                javafx.application.Platform.runLater(() -> {
                    statusLabel.setTextFill(Color.GREEN);
                    statusLabel.setText("✅ Arrived at Floor " + floor);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void handleEmergency() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("🚨 Emergency");
        alert.setHeaderText("ALERT - Emergency Call Initiated");
        alert.setContentText("Location: Floor " + currentFloor + "\nHelp is on the way! ⛑️\nTime: " + java.time.LocalTime.now());
        alert.showAndWait();
    }

    private void showHelp() {
        Alert help = new Alert(Alert.AlertType.INFORMATION);
        help.setTitle("Help Center 🧠");
        help.setHeaderText("How to Use the Lift Simulator");
        help.setContentText("\n- Select a floor using buttons.\n- Use Emergency button in case of problems.\n- Admin mode is coming soon.");
        help.showAndWait();
    }
}
