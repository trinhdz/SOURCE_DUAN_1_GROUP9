package DAO;

import Enity.Category;
import Enity.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDao extends MainDAO<Product, String> {

    String SELECT_BY_CATEGORY = "select * from Product where CategoryID = ? and title like ? ";
    String INSERT_SQL = "  insert product values(?,?,?,?,?,?,?)";
    String DELETE_SQL = "DELETE FROM Product WHERE ProductID=?";
    String UPDATE_SQL = "UPDATE product SET CategoryID=? , Title=?, Desciption=? , Price=? , Img=? , Supplier=? , quantity=? WHERE ProductID=?";
    String SELECT_TOTAL = "select count(*) as Total from Product";
    String SELECT_BY_ID = "select * from product where ProductID = ?";
    String SELECT_ALL = "select * from product ";
    String SELECT_ALL_BY_KEY = "select * from product where Title like ?";
    String UPDATE_QUANTITY = "update product set quantity = quantity - ?  where ProductID = ? ";

    @Override
    public void insert(Product entity) {
        JDBC.JdbcHelper.executeUpdate(INSERT_SQL,
                entity.getCategoryID(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getImg(),
                entity.getSupplier(),
                entity.getQuantity());
    }
    
    public void minusQuantity(int minusQuantity , int productID){
        JDBC.JdbcHelper.executeUpdate(UPDATE_QUANTITY, minusQuantity ,productID );
    }

    @Override
    public void update(Product entity) {
        JDBC.JdbcHelper.executeUpdate(UPDATE_SQL,
                entity.getCategoryID(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getImg(),
                entity.getSupplier(),
                entity.getQuantity(),
                entity.getProductID()
        );
    }

    @Override
    public void delete(String id) {
        JDBC.JdbcHelper.executeUpdate(DELETE_SQL, id);
    }

    public int selectTotal() {
        int total = 0;
        try {
            ResultSet rs = JDBC.JdbcHelper.executeQuery(SELECT_TOTAL);
            if (rs.next()) {
                total = rs.getInt("Total");
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return total;
    }

    public List<Product> selectByCategoryAndKey(int categoryID, String key) {
        return this.selectBySQL(SELECT_BY_CATEGORY, categoryID, key);
    }

    @Override
    public Product selectById(String id) {
        List<Product> list = this.selectBySQL(SELECT_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<Product> selectAllByKey(String key) {
        return this.selectBySQL(SELECT_ALL_BY_KEY, key);
    }

    @Override
    public List<Product> selectAll() {
        return this.selectBySQL(SELECT_ALL);
    }

    @Override
    protected List<Product> selectBySQL(String sql, Object... args) {
        List<Product> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                Product entity = new Product(rs.getInt("ProductID"),
                        rs.getInt("CategoryID"),
                        rs.getString("Supplier"),
                        rs.getString("Title"),
                        rs.getString("Desciption"),
                        rs.getFloat("Price"),
                        rs.getString("Img"),
                        rs.getInt("Quantity"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
