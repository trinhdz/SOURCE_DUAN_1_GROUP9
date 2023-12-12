package Enity;


public class User {

    private int userId;
    private boolean roleId;
    private String fullname;
    private String avatar;
    private String address;
    private String numberPhone;
    private String email;
    private String password;
    private String position;
    private String salary;

    public User(int userId, boolean roleId, String fullname, String avatar, String address, String numberPhone, String email, String password, String position, String salary) {
        this.userId = userId;
        this.roleId = roleId;
        this.fullname = fullname;
        this.avatar = avatar;
        this.address = address;
        this.numberPhone = numberPhone;
        this.email = email;
        this.password = password;
        this.position = position;
        this.salary = salary;
    }

    public User(String email, String fullname, String password, String position, boolean roleId) {
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.position = position;
        this.roleId = roleId;
    }

    public User(boolean roleId, String fullname, String avatar, String address, String numberPhone, String email, String password, String position, String salary) {
        this.roleId = roleId;
        this.fullname = fullname;
        this.avatar = avatar;
        this.address = address;
        this.numberPhone = numberPhone;
        this.email = email;
        this.password = password;
        this.position = position;
        this.salary = salary;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean getRoleId() {
        return roleId;
    }

    public void setRoleId(boolean roleId) {
        this.roleId = roleId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

}
