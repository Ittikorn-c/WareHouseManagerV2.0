package common;


import Sale.controllers.DataManager;
import Warehouse.controllers.ReqConfirmation;
import Warehouse.controllers.ShowsOrderPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWareHouse extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        DataManager dataManager = new DataManager();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/Warehouse/ShowsOrderPage.fxml"));

        primaryStage.setScene(new Scene((Parent) root.load(), 1000, 600));
        primaryStage.setTitle("Hello World");
        primaryStage.show();

        ShowsOrderPageController controller = root.getController();
        controller.setDataManager(dataManager);
        controller.reTableConfirm();
        controller.reTableRe();
        System.out.println(controller);
        System.out.println("test##########################################################################");

        controller.setDataManager(dataManager);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
