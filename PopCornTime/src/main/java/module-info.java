module com.blackpool.PopCornTime {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	requires com.jfoenix;
	requires java.sql;
	requires org.json;
	requires com.fasterxml.jackson.databind;
	requires javafx.base;
	requires activation;
	requires mail;
	
	exports com.blackpool.PopCornTime.Model to com.fasterxml.jackson.databind;
    opens com.blackpool.PopCornTime.UIController to javafx.fxml;
    opens com.blackpool.PopCornTime.LoginRegister to javafx.fxml;
    exports com.blackpool.PopCornTime to javafx.graphics;
    exports com.blackpool.PopCornTime.LoginRegister to javafx.graphics;
}