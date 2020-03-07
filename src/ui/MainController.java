package ui;

import controller.CsvController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import model.SubjectsGCal;
import utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MainController {
    private String newSchedule;
    private SubjectsGCal subs;
    @FXML
    private Circle circle;
    @FXML
    private VBox paneSubjects;
    @FXML
    private Label txtLabelMiddle;
    @FXML
    private Button btnDownload;
    @FXML
    private Button btnSubjects;
    @FXML
    void onDragDroppedCircle(DragEvent event) {
        getFile(event);
    }

    private void getFile(DragEvent event) {
        final Dragboard db = event.getDragboard();
        boolean isAccepted = db.getFiles().get(0).getName().toLowerCase().endsWith(".csv");
        if(db.hasFiles() && isAccepted){
            processFile(db.getFiles().get(0));
            btnDownload.setDisable(true);
        }
    }

    private void processFile(File file) {
        String newSch = "bla";
        String sch = null;
        try {
            sch = FileUtils.readFile(file);
        } catch (IOException e) {
            txtLabelMiddle.setText("Not good file");
        }
        subs = CsvController.getGoogleFromAlgebra(sch);
        txtLabelMiddle.setText("File Processed");
        subs.getSubjects().forEach(x->{
            CheckBox cb = new CheckBox(x);
            cb.setSelected(true);
            paneSubjects.getChildren().add(cb);
        });
        btnSubjects.setDisable(false);
    }

    @FXML
    void onBtnSubjectsClick(ActionEvent event) {
        Set<String> strings = new HashSet<>();
        for (Node child : paneSubjects.getChildren()) {
            if(child instanceof CheckBox){
                CheckBox cb = (CheckBox) child;
                if (cb.isSelected()){
                    String text = cb.getText();
                    strings.add(text);
                }
            }
        }
        subs.setSubjects(strings);
        newSchedule = subs.getLines();
        btnDownload.setDisable(false);
    }

    @FXML
    void onDragEnterCircle(DragEvent event) {
        circle.setFill(Color.GREEN);
        event.consume();
    }

    @FXML
    void onDragExitedCircle(DragEvent event) {
        circle.setFill(Color.RED);
    }

    @FXML
    void onDragOverCircle(DragEvent event) {
        event.acceptTransferModes(TransferMode.COPY);
        event.consume();
    }

    @FXML
    public void onBtnDownloadClick(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files(*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(txtLabelMiddle.getScene().getWindow());
        try {
            FileUtils.saveStringToFile(file, newSchedule,".csv");
        } catch (FileNotFoundException e) {
            txtLabelMiddle.setText(e.getMessage());
        }
    }
}
