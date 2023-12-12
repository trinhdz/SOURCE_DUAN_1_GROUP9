
package DAO;

import Enity.OrderDetail;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDao {
    String INSERT_SQL = "insert OrderDetail values (?,?,?,?)";
    String DELETE_SQL = "delete from OrderDetail where OrderID = ? ";
    String SELECT_BY_ORDERID = "select OrderDetailID,  OrderID , OrderDetail.ProductID, Title , OrderDetail.quantity, OrderDetail.Price\n" +
    "from OrderDetail inner join Product on Product.ProductID = OrderDetail.ProductID where OrderID = ?";
    
    
    public List<OrderDetail> selectByOrderID(int OrderID){
        return this.selectBySQL(SELECT_BY_ORDERID, OrderID);
    }
    
    
    public void insert(OrderDetail entity) {
        JDBC.JdbcHelper.executeUpdate(INSERT_SQL,
                entity.getOrderID(),
                entity.getProductID(),
                entity.getQuantity(),
                entity.getPrice());
    }
    
    public void delete(String orderID) {
        JDBC.JdbcHelper.executeUpdate(DELETE_SQL,orderID
                );
    }

    protected List<OrderDetail> selectBySQL(String sql, Object... args) {
        List<OrderDetail> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                OrderDetail entity = new OrderDetail(rs.getInt("OrderDetailID"),
                        rs.getInt("OrderID"),
                        rs.getInt("ProductID"),
                        rs.getInt("Quantity"),
                        rs.getString("Title"),
                        rs.getFloat("Price")
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
