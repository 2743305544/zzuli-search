package com.search;

import com.search.dynamic.FadeTransitionTools;
import com.search.entry.Items;
import com.search.utils.Jumputils;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import static com.search.utils.Listutils.ListItemsToObservableList;

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

    @FXML
    private MFXButton BackButton;


    ObservableList<String> strList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransitionTools.fadeout(indexPane);
        ArrayList<Items> items = new ArrayList<>();
        items.add(new Items("11","11"));
        ObservableList<String> DataList = ListItemsToObservableList(strList,items);
        List.setItems(DataList);
        MyLabel.setText("公告通知");
    }
    @FXML
    public void Back(ActionEvent event) {
        FadeTransitionTools.fade(indexPane);
        Jumputils.Jump("Index.fxml",event);
    }
}
