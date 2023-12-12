package DAO;

import Enity.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends MainDAO<Category, String> {

    String SELECT_ALL_SQL = "SELECT Category.categoryID, Category.name, COUNT(Product.ProductID) AS TotalProducts\n"
            + "FROM Category\n"
            + "LEFT JOIN Product ON Category.categoryID = Product.CategoryID\n"
            + "GROUP BY Category.categoryID, Category.name;";
    String SELECT_BY_KEYWORD = "SELECT Category.categoryID, Category.name, COUNT(Product.ProductID) AS TotalProducts\n"
            + "FROM Category\n"
            + "LEFT JOIN Product ON Category.categoryID = Product.CategoryID\n"
            + "where Category.name like ?\n"
            + "GROUP BY Category.categoryID, Category.name;";
    String SELECT_BY_NAME = "SELECT Category.categoryID, Category.name, COUNT(Product.ProductID) AS TotalProducts\n"
            + "FROM Category\n"
            + "LEFT JOIN Product ON Category.categoryID = Product.CategoryID\n"
            + "where Category.name = ?\n"
            + "GROUP BY Category.categoryID, Category.name;";
    String INSERT_SQL = "insert Category values (?)";
    String DELETE_SQL = "DELETE FROM Category WHERE categoryID=?";
    String UPDATE_SQL = "UPDATE Category SET name=? WHERE categoryID=?";
    String SELECT_TOTAL = "select count(*) as Total from Category";

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

    @Override
    public void insert(Category entity) {
        JDBC.JdbcHelper.executeUpdate(INSERT_SQL,
                entity.getName());
    }

    @Override
    public void update(Category entity) {
        JDBC.JdbcHelper.executeUpdate(UPDATE_SQL,
                entity.getName(),
                entity.getCategoryID());
    }

    @Override
    public void delete(String id) {
        JDBC.JdbcHelper.executeUpdate("DELETE FROM Product WHERE categoryID=?", id);
        JDBC.JdbcHelper.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public Category selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Category> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    public List<Category> selectByKeyWord(String key) {
        return this.selectBySQL(SELECT_BY_KEYWORD, key);
    }

    public Category selectByName(String name) {
        List<Category> list = this.selectBySQL(SELECT_BY_NAME, name);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<Category> selectBySQL(String sql, Object... args) {
        List<Category> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                Category entity = new Category(rs.getInt("categoryID"),
                        rs.getString("name"),
                        rs.getInt("TotalProducts"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
