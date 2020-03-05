package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainController {
    private String newSchedule;
    @FXML
    private Circle circle;

    @FXML
    private Pane paneSubjects;
    @FXML
    private Label txtLabelMiddle;

    @FXML
    private Button btnDownload;

    @FXML
    void onDragDroppedCircle(DragEvent event) {
        getFile(event);
    }

    private void getFile(DragEvent event) {
        final Dragboard db = event.getDragboard();
        boolean isAccepted = db.getFiles().get(0).getName().toLowerCase().endsWith(".csv");
        if(db.hasFiles() && isAccepted){
            newSchedule = processFile(db.getFiles().get(0));
            btnDownload.setDisable(false);
        }
    }

    private String processFile(File file) {
        String newSch = "bla";
        try {
            newSch = utils.CsvAlgebraImporter.getNewScheduleFile(file);
        } catch (IOException e) {
            txtLabelMiddle.setText("Not good file");
        } finally {
            return newSch;
        }
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
        File file =fileChooser.showSaveDialog(txtLabelMiddle.getScene().getWindow());
        try {
            FileUtils.saveStringToFile(file, newSchedule,".csv");
        } catch (FileNotFoundException e) {
            txtLabelMiddle.setText(e.getMessage());
        }
    }
}
