package DAO;

import Enity.RevenueStatistics;
import java.sql.ResultSet;

public class StatisticDAO {

    String SELECT_STATISTICS_BY_DATE = "select sum(TotalAmount) as TotalAmount , COUNT(orders.OrderID) as TotalOrder , sum(quantity) as TotalProduct from orders inner join OrderDetail on orders.OrderID = OrderDetail.OrderID\n"
            + "WHERE\n"
            + "    MONTH(OrderDate) = ? AND YEAR(OrderDate) = ?";

    String SELECT_STATISTICS_ALL = "select sum(TotalAmount) as TotalAmount , COUNT(orders.OrderID) as TotalOrder , sum(quantity) as TotalProduct from orders inner join OrderDetail on orders.OrderID = OrderDetail.OrderID";

    String SELECT_STATISTICS_TODAY = "select sum(TotalAmount) as TotalAmount , COUNT(orders.OrderID) as TotalOrder , sum(quantity) as TotalProduct "
            + "from orders inner join OrderDetail on orders.OrderID = OrderDetail.OrderID\n"
            + "WHERE\n"
            + "MONTH(OrderDate) = MONTH(GETDATE()) AND YEAR(OrderDate) = YEAR(GETDATE());";

    public RevenueStatistics selectByDate(Object... args) {
        RevenueStatistics result = null;
        try {
            ResultSet rs = JDBC.JdbcHelper.executeQuery(SELECT_STATISTICS_BY_DATE, args);
            while (rs.next()) {
                result = new RevenueStatistics(rs.getDouble("TotalAmount"),
                        rs.getInt("TotalOrder"),
                        rs.getInt("TotalProduct")
                );
            }
            return result;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public RevenueStatistics selectToday() {
        RevenueStatistics result = null;
        try {
            ResultSet rs = JDBC.JdbcHelper.executeQuery(SELECT_STATISTICS_TODAY);
            while (rs.next()) {
                result = new RevenueStatistics(rs.getDouble("TotalAmount"),
                        rs.getInt("TotalOrder"),
                        rs.getInt("TotalProduct")
                );
            }
            return result;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public RevenueStatistics selectAll() {
        RevenueStatistics result = null;
        try {
            ResultSet rs = JDBC.JdbcHelper.executeQuery(SELECT_STATISTICS_ALL);
            while (rs.next()) {
                result = new RevenueStatistics(rs.getDouble("TotalAmount"),
                        rs.getInt("TotalOrder"),
                        rs.getInt("TotalProduct")
                );
            }
            return result;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
