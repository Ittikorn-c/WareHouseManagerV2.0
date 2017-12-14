package common;


import Sale.controllers.DataManager;
import Sale.controllers.RequisitionPageController;
import Warehouse.controllers.ShowOrderPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWareHouse extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        DataManager dataManager = new DataManager();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/Warehouse/ShowOrdersTab.fxml"));

        primaryStage.setScene(new Scene((Parent) root.load(), 1000, 600));
        primaryStage.setTitle("Hello World");
        primaryStage.show();

        ShowOrderPageController controller = root.getController();
        controller.setDataManager(dataManager);
        System.out.println(controller);
        System.out.println("test##########################################################################");

        controller.setDataManager(dataManager);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
