package DAO;

import Enity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends MainDAO<User, String> {

    String SELECT_ALL_SQL = "SELECT * FROM Users where roleId = 0 ";
    String INSERT_SQL = "INSERT INTO Users(Email,Fullname, Password,Position, RoleID) VALUES(?,?,?,?,?)";
    String INSERT_EMPLOYEE = "INSERT users values(0 ,?, ?, ?, ?,?,?,?,?)";
    String SELECT_ALL_BY_KEYWORD = "SELECT * FROM Users where roleId = 0 and Fullname like ?";
    String UPDATE_EMPLOYEE = "UPDATE Users SET Fullname=?, Avatar=?, Address=?, NumberPhone=?, Password=? , Position=?, Salary=? WHERE UserID=?";
    String UPDATE_SQL = "UPDATE Users SET Fullname=?, Avatar=?, Address=?, NumberPhone=?, Password=? WHERE UserID=?";
    String DELETE_SQL = "delete users where userid = ?";
    String SELECT_BY_EMAIL_SQL = "SELECT * FROM USERS WHERE Email=?";
    String UPDATE_ROLE_ADMIN = "UPDATE users set RoleID = 1 where UserID = ?";
    String SELECT_TOTAL_EMPLOYEE = "select count(*) as Total from Users where roleID = 0";
    String UPDATE_PASSWORD = "update users set Password =? where email = ? ";

    public int selectTotal() {
        int total = 0;
        try {
            ResultSet rs = JDBC.JdbcHelper.executeQuery(SELECT_TOTAL_EMPLOYEE);
            if (rs.next()) {
                total = rs.getInt("Total");
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return total;
    }

    public List<User> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    public List<User> SelectAllByKeyWord(String key) {
        return this.selectBySQL(SELECT_ALL_BY_KEYWORD, key);
    }

    public User selectByEmail(String email) {
        List<User> list = this.selectBySQL(SELECT_BY_EMAIL_SQL, email);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public void insertEmployee(User entity) {
        JDBC.JdbcHelper.executeUpdate(INSERT_EMPLOYEE,
                entity.getFullname(),
                entity.getAvatar(),
                entity.getAddress(),
                entity.getNumberPhone(),
                entity.getPassword(),
                entity.getPosition(),
                entity.getSalary(),
                entity.getEmail());
    }

    public void updateEmployee(User entity) {
        JDBC.JdbcHelper.executeUpdate(UPDATE_EMPLOYEE,
                entity.getFullname(),
                entity.getAvatar(),
                entity.getAddress(),
                entity.getNumberPhone(),
                entity.getPassword(),
                entity.getPosition(),
                entity.getSalary(),
                entity.getUserId());
    }

    public void changePassword(String email, String password) {
        JDBC.JdbcHelper.executeUpdate(UPDATE_PASSWORD,
                 password, email);
    }

    @Override
    public void insert(User entity) {
        JDBC.JdbcHelper.executeUpdate(INSERT_SQL,
                entity.getEmail(),
                entity.getFullname(),
                entity.getPassword(),
                entity.getPosition(),
                entity.getRoleId());
    }

    @Override
    public void update(User entity) {
        JDBC.JdbcHelper.executeUpdate(UPDATE_SQL,
                entity.getFullname(),
                entity.getAvatar(),
                entity.getAddress(),
                entity.getNumberPhone(),
                entity.getPassword(),
                entity.getUserId());
    }

    public void updateRoleAdmin(int UserID) {
        JDBC.JdbcHelper.executeUpdate(UPDATE_ROLE_ADMIN, UserID);
    }

    @Override
    public void delete(String id) {
        JDBC.JdbcHelper.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public User selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<User> selectBySQL(String sql, Object... args) {
        List<User> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                User entity = new User(rs.getInt("UserID"),
                        rs.getBoolean("RoleID"),
                        rs.getString("Fullname"),
                        rs.getString("Avatar"),
                        rs.getString("Address"),
                        rs.getString("NumberPhone"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Position"),
                        rs.getString("Salary"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
