package Sale.dataSources;

import Sale.models.Goods;
import Sale.models.Requisition;
import Sale.models.RequisitionGoods;
import Warehouse.models.PurchaseOrder;
import Warehouse.models.PurchaseOrderGoods;
import Warehouse.models.Supplier;

import java.sql.*;
import java.util.ArrayList;

public class DBConnector {
    Connection conn;
    String url;

    public DBConnector() {
        this.url = "//127.0.0.1:3306/sa_database";
    }

    void connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql:" + url, "root", "");
    }

    public ResultSet getData(String tableName) throws SQLException {
        ResultSet resultSet = null;
        try {
            connect();
            String query = "SELECT * FROM " + tableName;
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        conn.close();
        return resultSet;
    }

    public ArrayList<Goods> loadAllGoodses() {
        ArrayList<Goods> goodses = new ArrayList<Goods>();
        try {
            connect();
            String query = "SELECT * FROM goodses";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String type = resultSet.getString(2);
                String brand = resultSet.getString(3);
                String name = resultSet.getString(4);
                int amount = resultSet.getInt(5);
                Goods goods = new Goods(id,type,brand,name,amount);

                goodses.add(goods);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return goodses;
    }

    public ArrayList<Supplier> loadAllSuppliers() {
        ArrayList<Supplier> sups = new ArrayList<Supplier>();
        try {
            connect();
            String query = "SELECT * FROM suppliers";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String addr = resultSet.getString(3);
                String tel = resultSet.getString(4);
                Supplier sup = new Supplier(id, name, addr, tel);

                sups.add(sup);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return sups;
    }

    public void insertGoodes(Goods goods){
        try {
            connect();
            String query = "INSERT INTO goodses(id,type,brand,name,quantity,available) VALUES (NULL," + goods.getType() + "," + goods.getBrand() + "," + goods.getName() + "," + "0,0" + ")";
            query = String.format("INSERT INTO `goodses` (`id`, `type`, `brand`, `name`, `quantity`, `available`) VALUES (NULL,'%s', '%s', '%s', 0, 0)",goods.getType(),goods.getBrand(),goods.getName());
            System.out.println(query);
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);

            query = String.format("SELECT MAX(`id`) FROM `goodses`");
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            int good_id = resultSet.getInt(1);
            goods.setId(good_id);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  void insertRequisition(Requisition requisition){
        try {
            connect();
            String query = String.format("INSERT INTO `requisitions` (`req_id`, `status`) VALUES (NULL,'%s')",requisition.getStatus());
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);

            query = String.format("SELECT MAX(`req_id`) FROM `requisitions`");
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            int req_id = resultSet.getInt(1);

            for (RequisitionGoods g : requisition.getRequisitionGoodsArrayList()) {
                query = String.format("INSERT INTO `requisitiongoods` (`req_id`, `good_id`, `amount`) VALUES (%d, %d, %d)",req_id,g.getId(),g.getAmount());
                statement.executeUpdate(query);
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Requisition> getAllRequisitions() throws SQLException {
        ArrayList<Requisition> requisitionArrayList = new ArrayList<Requisition>();

        try {
            connect();
            String query = "SELECT * FROM `requisitions`";
            Statement statement = conn.createStatement();
            ResultSet resultR = statement.executeQuery(query);
            while (resultR.next()) {
                int id = resultR.getInt(1);
                String status = resultR.getString(2);
                query = String.format("SELECT good_id, type, brand, name, amount " +
                        "FROM requisitiongoods, goodses WHERE req_id=%d AND requisitiongoods.good_id = goodses.id",id);
                statement = conn.createStatement();
                ResultSet resultO = statement.executeQuery(query);

                ArrayList<RequisitionGoods> goods = new ArrayList<RequisitionGoods>();

                while (resultO.next()){
                    int good_id = resultO.getInt(1);
                    String type = resultO.getString(2);
                    String brand = resultO.getString(3);
                    String name = resultO.getString(4);
                    int amount = resultO.getInt(5);

                    RequisitionGoods good = new RequisitionGoods(good_id, type,brand,name, amount);
                    goods.add(good);
                }

                Requisition requisition = new Requisition(id, status, goods);
                requisitionArrayList.add(requisition);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        conn.close();
        return requisitionArrayList;

    }

    public void setRequisitionStatus(int id, String status) {
        try {
            connect();
            if (conn != null) {
                String query = String.format("UPDATE `requisitions` SET `status` = '%s' WHERE req_id = %d",status, id);
                Statement statement = conn.createStatement();
                statement.executeUpdate(query);
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateGoods(Goods goods) {
        try {
            connect();
            if (conn != null) {
                String query = String.format("UPDATE goodses SET quantity = %d WHERE id = %d",goods.getQuantity(), goods.getId());
                Statement statement = conn.createStatement();
                statement.executeUpdate(query);
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PurchaseOrder> getAllPurchases() throws SQLException {
        ArrayList<PurchaseOrder> purchaseOrderArrayList = new ArrayList<PurchaseOrder>();

        try {
            connect();
            String query = "SELECT * FROM `orders`";
            Statement statement = conn.createStatement();
            ResultSet resultR = statement.executeQuery(query);
            while (resultR.next()) {
                int id = resultR.getInt(1);
                int supplier_id = resultR.getInt(2);
                String status = resultR.getString(3);
                query = String.format("SELECT good_id, type, brand, name, amount " +
                        "FROM ordergoods, goodses WHERE order_id=%d AND ordergoods.good_id = goodses.id",id);
                statement = conn.createStatement();
                ResultSet resultO = statement.executeQuery(query);

                ArrayList<PurchaseOrderGoods> goods = new ArrayList<PurchaseOrderGoods>();

                while (resultO.next()){
                    int good_id = resultO.getInt(1);
                    String type = resultO.getString(2);
                    String brand = resultO.getString(3);
                    String name = resultO.getString(4);
                    int amount = resultO.getInt(5);

                    PurchaseOrderGoods good = new PurchaseOrderGoods(good_id, type,brand,name, amount);
                    goods.add(good);
                }

                query = String.format("SELECT * FROM suppliers WHERE id = %d", supplier_id);
                statement = conn.createStatement();
                ResultSet resultSetSup = statement.executeQuery(query);
                resultSetSup.next();
                int supId = resultSetSup.getInt(1);
                String name = resultSetSup.getString(2);
                String addr = resultSetSup.getString(3);
                String tel = resultSetSup.getString(4);
                Supplier sup = new Supplier(supId, name, addr, tel);

                PurchaseOrder purchaseOrder = new PurchaseOrder(id, status, sup, goods);
                purchaseOrderArrayList.add(purchaseOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        conn.close();

        return purchaseOrderArrayList;
    }

    public void setPurchaseOrderStatus(int id, String status) {
        try {
            connect();
            if (conn != null) {
                String query = String.format("UPDATE `orders` SET `status` = '%s' WHERE order_id = %d", status, id);
                Statement statement = conn.createStatement();
                statement.executeUpdate(query);
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateGoodsAmount(int id, int amount) {
        try {
            connect();
            if (conn != null) {

                String query = String.format("UPDATE goodses SET quantity = %d WHERE id = %d",amount, id);
                System.out.println(query);
                Statement statement = conn.createStatement();
                statement.executeUpdate(query);
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertPurchaseOrder(PurchaseOrder purchaseOrder) {
        try {
            connect();
            String query = String.format("INSERT INTO `orders` (`order_id`, `supplier_id`, `status`) VALUES (NULL, %d,'%s')", purchaseOrder.getSupplier().getId(), purchaseOrder.getStatus());
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);

            query = String.format("SELECT MAX(`order_id`) FROM `orders`");
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            int order_id = resultSet.getInt(1);

            for (PurchaseOrderGoods g : purchaseOrder.getRequisitionGoodsArrayList()) {
                query = String.format("INSERT INTO `ordergoods` (`order_id`, `good_id`, `amount`) VALUES (%d, %d, %d)",order_id,g.getId(),g.getAmount());
                statement.executeUpdate(query);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getAllTypes(String brand) {
        ArrayList<String> types = new ArrayList<>();

        try {
            connect();
            String query = String.format("SELECT DISTINCT type FROM goodses WHERE brand LIKE '%s'", brand);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String type = resultSet.getString(1);
                types.add(type);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return types;
    }

    public ArrayList<String> getAllBrands(String type) {
        ArrayList<String> brands = new ArrayList<>();

        try {
            connect();
            String query = String.format("SELECT DISTINCT brand FROM goodses WHERE type LIKE '%s'", type);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String brand = resultSet.getString(1);
                brands.add(brand);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return brands;
    }

    public ArrayList<String> getAllSupNames() {
        ArrayList<String> sups = new ArrayList<>();

        try {
            connect();
            String query = "SELECT DISTINCT name FROM suppliers";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                sups.add(name);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return sups;
    }

    public void updateStatusRequisition(Requisition r) {
        try {
            connect();
            if (conn != null) {

                String query = String.format("UPDATE requisitions SET status = 'available'  WHERE req_id = %d", r.getId());
                System.out.println(query);
                Statement statement = conn.createStatement();
                statement.executeUpdate(query);
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addNewSupplier(Supplier supplier) {
        try {
            connect();
            String query = String.format("INSERT INTO suppliers VALUES (NULL, '%s', '%s', '%s')", supplier.getName(), supplier.getAddr(), supplier.getPhone());
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);

            query = String.format("SELECT MAX(id) FROM suppliers");
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            int sup_id = resultSet.getInt(1);

            supplier.setId(sup_id);
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

