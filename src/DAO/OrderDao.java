
package DAO;

import Enity.Order;
import Enity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderDao extends MainDAO<Order, String> {

    String SELECT_TOTAL = "select count(*) as Total from orders";
    String SELECT_ALL_SQL = "SELECT * FROM orders";
    String INSERT_SQL = "insert orders values (?,?,?,?,?,?)";
    String DELETE_SQL = "delete from orders where OrderID =?";
    String UPDATE_SQL = "update orders set OrderDate = ? ,  NameCustomer = ? ,SDTCustomer = ?, TotalAmount = ? , MaNv = ? where OrderID = ?";
    String SELECT_ALL_KEY = "select * from orders where MONTH(OrderDate) = ? and YEAR(OrderDate) = ? and NameCustomer like ? ";

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
    public void insert(Order entity) {
        JDBC.JdbcHelper.executeUpdate(INSERT_SQL,
                entity.getOrderID(),
                entity.getOrderdate(),
                entity.getNameCustomer(),
                entity.getPhoneCustomer(),
                entity.getTotalAmount(),
                entity.getEmployeeID());
    }

    @Override
    public void update(Order entity) {
        JDBC.JdbcHelper.executeUpdate(UPDATE_SQL,
                entity.getOrderdate(),
                entity.getNameCustomer(),
                entity.getPhoneCustomer(),
                entity.getTotalAmount(),
                entity.getEmployeeID(),
                entity.getOrderID()
        );
    }

    @Override
    public void delete(String id) {
        JDBC.JdbcHelper.executeUpdate(DELETE_SQL, Integer.parseInt(id));
    }

    @Override
    public Order selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Order> selectAllByDate(int month, int year, String nameCustomer) {
        return this.selectBySQL(SELECT_ALL_KEY, month, year, nameCustomer);
    }

    @Override
    public List<Order> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    protected List<Order> selectBySQL(String sql, Object... args) {
        List<Order> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                Order entity = new Order(rs.getInt("OrderId"),
                        rs.getString("OrderDate"),
                        rs.getFloat("TotalAmount"),
                        rs.getString("NameCustomer"),
                        rs.getString("SDTCustomer"),
                        rs.getInt("MaNv")
                );
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
