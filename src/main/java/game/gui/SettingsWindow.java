package game.gui;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SettingsWindow extends Stage {

    private Map<String, String> config;
    private ComboBox<String> configSelector;
    private GridPane settingsGrid;
    private ScrollPane scrollPane;
    private String currentConfigFile;
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
        config = new LinkedHashMap<>(); // Using LinkedHashMap to preserve order
        settingsGrid = new GridPane();
        scrollPane = createScrollPane(settingsGrid);

        configSelector = new ComboBox<>();
        configSelector.getItems().addAll(configFiles);
        configSelector.setOnAction(e -> loadConfig(configSelector.getValue()));

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveConfig());

        Button saveAndCloseButton = new Button("Save & Close");
        saveAndCloseButton.setOnAction(e -> {
            saveConfig();
            close();
        });

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> closeWithoutSaving());

        HBox buttonBox = new HBox(10, saveButton, saveAndCloseButton, closeButton);

        VBox root = new VBox(10, configSelector, scrollPane, buttonBox);
        Scene scene = new Scene(root, 400, 300);

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

    private void loadConfig(String fileName) {
        if (currentConfigFile != null && !currentConfigFile.equals(fileName)) {
            // Close the current config file
            closeConfigFile();
        }
        currentConfigFile = fileName;

        settingsGrid.getChildren().clear();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Ignore comments and empty lines
                if (!line.trim().startsWith("#") && !line.trim().isEmpty()) {
                    int index = line.indexOf("=");
                    if (index != -1) {
                        String key = line.substring(0, index).trim();
                        String value = line.substring(index + 1).trim();
                        config.put(key, value);
                    }
                }
            }

            int row = 0;
            for (Map.Entry<String, String> entry : config.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
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
        // Clear properties and close the file
        config.clear();
        currentConfigFile = null;
    }

    private void saveConfig() {
        for (javafx.scene.Node node : settingsGrid.getChildren()) {
            if (node instanceof TextField) {
                TextField textField = (TextField) node;
                config.put(textField.getId(), textField.getText());
            }
        }
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(currentConfigFile))) {
            for (Map.Entry<String, String> entry : config.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reset the selected config file and clear the settings grid
        configSelector.setValue(null);
        settingsGrid.getChildren().clear();
    }

    private void closeWithoutSaving() {
        close();
    }
}
