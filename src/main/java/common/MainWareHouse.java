package common;


import Sale.controllers.DataManager;
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

        primaryStage.setScene(new Scene((Parent) root.load(), 1209, 792));
        primaryStage.setTitle("Warehouse Management");

        ShowsOrderPageController controller = root.getController();

        controller.setDataManager(dataManager);
        controller.reTableConfirm();
        controller.reTableRe();
        controller.reTableGoods();
        controller.initComboBox();
        controller.reTableImp();
//        controller.reTablePetch();
        controller.rePoIdTable();
        System.out.println(controller);
        System.out.println("filterNameType##########################################################################");

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
