package common;


import Sale.controllers.DataManager;
import Sale.controllers.RequisitionPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainSale extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        DataManager dataManager = new DataManager();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/Sale/RequisitionPage.fxml"));

        primaryStage.setScene(new Scene((Parent) root.load(), 1092, 600));
        primaryStage.setTitle("Warehouse Management for Sale");

        RequisitionPageController controller = root.getController();
        controller.setDataManager(dataManager);
        controller.reTableGoodses();
        controller.initComboBox();

        System.out.println(controller);
        System.out.println("filterNameType##########################################################################");

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
