import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;
import java.util.*;

public class SmartLiftSimulatorGUI extends Application {
    private Label statusLabel = new Label("Lift is on Floor 0 🟩");
    private int currentFloor = 0;
    private final int totalFloors = 10;
    private final List<Integer> floorRequests = new ArrayList<>();
    private ImageView liftIcon;
    private VBox liftShaft;
    private AudioClip alarmSound;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        
        alarmSound = new AudioClip(getClass().getResource("alarm.wav").toString());

        primaryStage.setTitle("🚀 Smart Lift Simulator with AI & Emergency Features");

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        Label title = new Label("🏢 Smart Lift System");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextFill(Color.DARKBLUE);

        liftShaft = new VBox();
        liftShaft.setMinHeight(552);
        liftShaft.setAlignment(Pos.TOP_CENTER);
        liftShaft.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: black; -fx-border-width: 2px;");

        liftIcon = new ImageView(new Image(getClass().getResourceAsStream("lift.png")));
        liftIcon.setFitHeight(50);
        liftIcon.setFitWidth(50);
        double pixelPerFloor = 50.0;
        liftIcon.setTranslateY(pixelPerFloor * totalFloors);
        liftShaft.getChildren().add(liftIcon);

        GridPane floorButtons = new GridPane();
        floorButtons.setHgap(10);
        floorButtons.setVgap(10);

        for (int i = 0; i <= totalFloors; i++) {
            int floor = i;
            Button floorButton = new Button("Floor " + i);
            floorButton.setOnAction(e -> handleRequest(floor));
            floorButtons.add(floorButton, i % 5, i / 5);
        }

        Button emergencyBtn = new Button("🚨 Emergency Call");
        emergencyBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        emergencyBtn.setOnAction(e -> handleEmergency());

        Button helpBtn = new Button("🆘 Help");
        helpBtn.setOnAction(e -> showHelp());

        Button aiBtn = new Button("🤖 Smart Run");
        aiBtn.setOnAction(e -> runAISmartLift());

        HBox bottomMenu = new HBox(15, emergencyBtn, helpBtn, aiBtn);
        bottomMenu.setAlignment(Pos.CENTER);

        statusLabel.setFont(Font.font("Consolas", 18));
        statusLabel.setTextFill(Color.GREEN);

        root.getChildren().addAll(title, liftShaft, floorButtons, statusLabel, bottomMenu);

        Scene scene = new Scene(root, 700, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleRequest(int floor) {
        if (!floorRequests.contains(floor)) {
            floorRequests.add(floor);
            statusLabel.setTextFill(Color.ORANGE);
            statusLabel.setText("📥 Request received for Floor " + floor);
        }
    }

    private void runAISmartLift() {
        if (floorRequests.isEmpty()) {
            statusLabel.setTextFill(Color.GRAY);
            statusLabel.setText("🤖 No requests to process.");
            return;
        }
        floorRequests.sort(Comparator.comparingInt(f -> Math.abs(f - currentFloor)));
        int nextFloor = floorRequests.remove(0);
        moveLift(nextFloor);
    }

    private void moveLift(int floor) {
        statusLabel.setTextFill(Color.BLUE);
        statusLabel.setText("🚧 Moving to Floor " + floor);

        double pixelPerFloor = 50.0;  
        double targetY = pixelPerFloor * (totalFloors - floor);  

        TranslateTransition transition = new TranslateTransition(Duration.seconds(2), liftIcon);
        transition.setToY(targetY);
        transition.setOnFinished(e -> {
            currentFloor = floor;
            statusLabel.setTextFill(Color.GREEN);
            statusLabel.setText("✅ Arrived at Floor " + floor);
        });
        transition.play();
    }

    private void handleEmergency() {
        alarmSound.play();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("🚨 Emergency");
        alert.setHeaderText("ALERT - Emergency Call Initiated");
        alert.setContentText("Location: Floor " + currentFloor +
                "\nHelp is on the way! ⛑️\nTime: " + java.time.LocalTime.now());
        alert.showAndWait();
    }

    private void showHelp() {
        Alert help = new Alert(Alert.AlertType.INFORMATION);
        help.setTitle("Help Center 🧠");
        help.setHeaderText("How to Use the Lift Simulator");
        help.setContentText("- Select a floor using buttons.\n" +
                "- Use Emergency button in case of problems.\n" +
                "- AI mode will optimize lift routes.\n" +
                "- Enjoy graphics, sounds, and a smooth UX!");
        help.showAndWait();
    }
}
