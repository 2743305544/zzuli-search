package com.search;

import com.search.constant.SystemConstants;
import com.search.dynamic.FadeTransitionTools;
import com.search.entry.Items;
import com.search.utils.Jsutils;
import com.search.utils.Jumputils;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

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
        new Thread(() -> {
            FontIcon icon = new FontIcon(AntDesignIconsOutlined.SEARCH);
            icon.setIconSize(12);
            searchButton.setGraphic(icon);
            FontIcon icon1 = new FontIcon(AntDesignIconsOutlined.ARROW_LEFT);
            icon1.setIconSize(20);
            BackButton.setGraphic(icon1);
        }).start();
        new Thread(() -> {
            FadeTransitionTools.fadeout(indexPane);
            ArrayList<Items> items = new ArrayList<>();
            items.add(new Items("11","11"));
            ObservableList<String> DataList = ListItemsToObservableList(strList,items);
            List.setItems(DataList);
        }).start();
        MyLabel.setText("公告通知");
    }
    @FXML
    public void Back(ActionEvent event) {
        FadeTransitionTools.fade(indexPane);
        Jumputils.Jump(SystemConstants.INDEX_PAGE_NAME,event);
    }
    @FXML
    public void ListClicked(MouseEvent event) {
        System.out.println(List.getSelectionModel().getSelectedValue());
        Stage WebStage = new Stage();
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);
//        webEngine.executeScript(Jsutils.readJsFileAsString("h5player.js"));//加载脚本
        webEngine.setUserAgent("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 Chrome/44.0.2403.155 Safari/537.36");
        webEngine.load("https://info.zzuli.edu.cn/_t961/2024/1014/c2464a321063/page.htm");
        StackPane webRoot = new StackPane();
        webRoot.getChildren().add(webView);
        Scene webScene = new Scene(webRoot,1040,620);
        WebStage.setScene(webScene);
        WebStage.show();
    }
}
