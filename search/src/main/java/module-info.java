module com.search {
    requires javafx.web;
    requires org.kordamp.ikonli.core;
    requires animatefx;
    requires eu.iamgio.animated;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires MaterialFX;

    opens com.search to javafx.fxml;
    exports com.search;
}