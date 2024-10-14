package com.search;

import com.search.dynamic.FadeTransitionTools;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Msg1Controller implements Initializable {
    @FXML
    private MFXTextField TextField;
    @FXML
    private Label MyLabel;
    @FXML
    private MFXButton searchButton;
    @FXML
    private MFXListView<String> List;

    @FXML
    private AnchorPane indexPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransitionTools.fadeout(indexPane);
    }
}
