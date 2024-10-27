package com.search;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.search.constant.SystemConstants;
import com.search.dynamic.FadeTransitionTools;
import com.search.entry.Items;
import com.search.entry.ListData;
import com.search.utils.BeanCopyUtils;
import com.search.utils.Jumputils;
import com.search.utils.OkHttpUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.search.utils.Listutils.ListItemsToObservableList;

/**
 * @author 34011
 */
public class Msg1Controller implements Initializable {
    @FXML
    private MFXTextField TextField;
    @FXML
    private Label MyLabel;
    @FXML
    private MFXButton searchButton;
    @FXML
    private MFXListView<String> List;

    private static Integer trig = 0;

    @FXML
    private AnchorPane indexPane;

    @FXML
    private MFXButton BackButton;

    private final OkHttpClient okHttpClient = OkHttpUtil.getOkHttpClient(300, 300, 300);

    ObservableList<String> strList = FXCollections.observableArrayList();

    AtomicReference<java.util.List<ListData>> data = new AtomicReference<>();
    Map<String, String> collect = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(() -> {
            if (trig == 0) {
                String s = OkHttpUtil.get(okHttpClient, "http://localhost:9090/api/refreshAllData");
                System.out.println(s);
                trig = 1;
            }
        }).start();
//        TypeReference<Response<ListData>> typeReference =new TypeReference<>() {};
//        Response<ListData> listDataResponse = JSON.parseObject(s, typeReference);
//        java.util.List<ListData> data = listDataResponse.getData();

        new Thread(() -> {
            FontIcon icon = new FontIcon(AntDesignIconsOutlined.SEARCH);
            icon.setIconSize(12);
            searchButton.setGraphic(icon);
            FontIcon icon1 = new FontIcon(AntDesignIconsOutlined.ARROW_LEFT);
            icon1.setIconSize(20);
            BackButton.setGraphic(icon1);
        }).start();
        new Thread(() -> {
//            java.util.List<Items> items1 = BeanCopyUtils.copyBeanList(data, Items.class);
            String s = OkHttpUtil.get(okHttpClient, "http://localhost:9090/api/getMsg1");
            System.out.println(s);
            TypeReference<Response<ListData>> typeReference =new TypeReference<>() {};
            Response<ListData> listDataResponse = JSON.parseObject(s, typeReference);
            System.out.println(listDataResponse);
             data.set(listDataResponse.getData());
             collect = listDataResponse.getData().stream().collect(Collectors.toMap(ListData::getTitle, ListData::getUrl));
            System.out.println(data.get());
            java.util.List<Items> items = BeanCopyUtils.copyBeanList(data.get(), Items.class);
            ObservableList<String> dataList = ListItemsToObservableList(strList,items);
            System.out.println(dataList);
            Platform.runLater(() -> {
                List.getItems().clear();
                List.setItems(dataList);
            });
        }).start();
        MyLabel.setText("公告通知");
        FadeTransitionTools.fadeout(indexPane);
    }
    @FXML
    public void Back(ActionEvent event) {
        FadeTransitionTools.fade(indexPane);
        Jumputils.Jump(SystemConstants.INDEX_PAGE_NAME,event);
    }
    @FXML
    public void ListClicked(MouseEvent event) {
        String selectedValue = List.getSelectionModel().getSelectedValue();
        if(StringUtils.hasText(selectedValue)) {
            System.out.println(selectedValue);
            Stage WebStage = new Stage();
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            webEngine.setJavaScriptEnabled(true);
//        webEngine.executeScript(Jsutils.readJsFileAsString("h5player.js"));//加载脚本
            webEngine.setUserAgent("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 Chrome/44.0.2403.155 Safari/537.36");
            String selectLink = collect.get(selectedValue.substring(0,selectedValue.length()-14));
            System.out.println(selectLink);
            webEngine.load(selectLink);
            StackPane webRoot = new StackPane();
            webRoot.getChildren().add(webView);
            Scene webScene = new Scene(webRoot,1040,620);
            WebStage.setScene(webScene);
            WebStage.show();
        }
    }
    @FXML
    public void tosearch(ActionEvent event) {
        String s = OkHttpUtil.zzuliDataSearch(okHttpClient, "http://localhost:9090/api/searchMsg1", TextField.getText());
        TypeReference<Response<ListData>> typeReference =new TypeReference<>() {};
        Response<ListData> listDataResponse = JSON.parseObject(s, typeReference);
        collect = listDataResponse.getData().stream().collect(Collectors.toMap(ListData::getTitle, ListData::getUrl));
        java.util.List<Items> items = BeanCopyUtils.copyBeanList(listDataResponse.getData(), Items.class);
        ObservableList<String> strList1 = ListItemsToObservableList(strList, items);
        List.setItems(strList1);
    }
}
