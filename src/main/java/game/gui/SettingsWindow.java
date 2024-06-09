package game.gui;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SettingsWindow extends Stage {

    private Properties config;
    private ComboBox<String> configSelector;
    private GridPane settingsGrid;
    private ScrollPane scrollPane;
    private String currentConfigFile;
    private Map<String, Path> originalConfigFiles;
    private static final String[] configFiles = {
            "src/main/resources/config/RoundsRewards.properties",
            "src/main/resources/config/EmberfallDominionDiceValue.properties",
            "src/main/resources/config/EmberfallDominionScore.properties",
            "src/main/resources/config/EmberfallDominionRewards.properties",
            "src/main/resources/config/TideAbyssScore.properties",
            "src/main/resources/config/TideAbyssRewards.properties",
            "src/main/resources/config/TerrasHeartland.properties",
            "src/main/resources/config/TerrasHeartlandRewards.properties",
            "src/main/resources/config/MysticalSkyRewards.properties",
            "src/main/resources/config/RadiantSvannaRewards.properties",
            "src/main/resources/config/RadiantSvannaMultipliers.properties"
    };

    public SettingsWindow() {
        config = new Properties();
        settingsGrid = new GridPane();
        scrollPane = createScrollPane(settingsGrid);
        originalConfigFiles = new HashMap<>();

        // Create a copy of the original config files
        createOriginalConfigFiles();

        configSelector = new ComboBox<>();
        configSelector.getItems().addAll(configFiles);
        configSelector.setOnAction(e -> loadConfig(configSelector.getValue()));

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveConfig());

        VBox root = new VBox(10, configSelector, scrollPane, saveButton);
        Scene scene = new Scene(root, 400, 300);

        // Hook into window close event
        setOnCloseRequest(event -> onClose());

        this.setTitle("Settings");
        this.setScene(scene);
    }

    private ScrollPane createScrollPane(GridPane content) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        return scrollPane;
    }

    private void createOriginalConfigFiles() {
        for (String configFile : configFiles) {
            Path originalFile = Paths.get(configFile + ".original");
            Path configFileSource = Paths.get(configFile);
            try {
                Files.copy(configFileSource, originalFile, StandardCopyOption.REPLACE_EXISTING);
                originalConfigFiles.put(configFile, originalFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadConfig(String fileName) {
        if (currentConfigFile != null && !currentConfigFile.equals(fileName)) {
            // Close the current config file
            closeConfigFile();
        }
        currentConfigFile = fileName;

        settingsGrid.getChildren().clear();
        try {
            config.load(Files.newInputStream(Paths.get(fileName)));

            int row = 0;
            for (String key : config.stringPropertyNames()) {
                String value = config.getProperty(key);
                settingsGrid.add(new Label(key), 0, row);
                TextField valueField = new TextField(value);
                valueField.setId(key);
                settingsGrid.add(valueField, 1, row);
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConfigFile() {
        // Restore the original config file
        Path originalFile = originalConfigFiles.get(currentConfigFile);
        Path configFile = Paths.get(currentConfigFile);
        try {
            Files.copy(originalFile, configFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveConfig() {
        for (javafx.scene.Node node : settingsGrid.getChildren()) {
            if (node instanceof TextField) {
                TextField textField = (TextField) node;
                config.setProperty(textField.getId(), textField.getText());
            }
        }
        try {
            config.store(Files.newOutputStream(Paths.get(currentConfigFile)), null);
            // Close the window after saving
            this.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onClose() {
        // Delete temporary files
        originalConfigFiles.values().forEach(file -> {
            try {
                Files.deleteIfExists(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
