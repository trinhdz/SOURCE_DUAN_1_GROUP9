package com.raven.component;

import DAO.UserDao;
import Enity.User;
import Form.AuthForm;
import Form.MainForm;
import ThuVien.Auth;
import ThuVien.DialogHelper;
import TienIch.Validator;
import com.raven.swing.Button;
import com.raven.swing.MyPasswordField;
import com.raven.swing.MyTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.MessageDigest;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    PanelCover cover = new PanelCover();
    UserDao dao = new UserDao();

    public PanelLoginAndRegister() {
        initComponents();
        initRegister();
        initLogin();
        login.setVisible(false);
        register.setVisible(false);
    }



    private void initRegister() {
        // set Layout cho form 
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        // header 
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(252, 132, 3));
        register.add(label);
        // field txtFullname 
        MyTextField txtFullname = new MyTextField();
        txtFullname.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/user.png")));
        txtFullname.setHint("Fullname");
        register.add(txtFullname, "w 60%");
        // field txtEmail 
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/mail.png")));
        txtEmail.setHint("Email");
        register.add(txtEmail, "w 60%");
        // field password 
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/pass.png")));
        txtPass.setHint("Password");
        register.add(txtPass, "w 60%");
        // field confirm password 
        MyPasswordField txtConfirm = new MyPasswordField();
        txtConfirm.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/pass.png")));
        txtConfirm.setHint("Confirm Password");
        register.add(txtConfirm, "w 60%");
        // mess err
        JLabel errmess = new JLabel("");
        errmess.setForeground(Color.red);
        register.add(errmess);
        // button SignUp
        Button cmd = new Button();
        cmd.setBackground(new Color(252, 132, 3));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN UP");
        register.add(cmd, "w 40%, h 40");

        // onclick register  
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                errmess.setText("");

                // check empty 
                if (txtEmail.getText().trim().isEmpty()
                        || new String(txtPass.getPassword()).isEmpty()
                        || new String(txtConfirm.getPassword()).isEmpty()) {
                    errmess.setText("Vui lòng điền đầy đủ thông tin .");
                    return;
                }

                // check matches passworđ 
                if (!new String(txtPass.getPassword()).equals(new String(txtConfirm.getPassword()))) {
                    errmess.setText("Mật khẩu không trùng khớp .");
                    return;
                }

                // check type email 
                if (!Validator.isValidEmail(txtEmail.getText().trim())) {
                    errmess.setText("Email không hợp lệ  .");
                    return;
                }
                
                // check email exits 
                User check = dao.selectByEmail(txtEmail.getText().trim());
                if(check != null){
                    errmess.setText("Email đã tồn tại sài email khác.");
                    return;
                }
                // validate ok create user
                try {
                    User user = new User(txtEmail.getText().trim(), txtFullname.getText().trim(),
                            Auth.md5(new String(txtConfirm.getPassword())),
                            "Nhân viên phòng 2003",
                            false);
                    dao.insert(user);
                    DialogHelper.alert(null, "Đăng kí thành công !!!");
                } catch (Exception e) {
                    System.out.println(e);
                    errmess.setText("Đăng kí không thành công .");
                }
            }
        });

    }

    private void initLogin() {
        // set layout 
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        //add header 
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(252, 132, 3));
        login.add(label);
        // add field email 
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/mail.png")));
        txtEmail.setHint("Email");
        login.add(txtEmail, "w 60%");
        // add field password 
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/pass.png")));
        txtPass.setHint("Password");
        txtEmail.setText("kkk@gmail.com");
        txtPass.setText("123456");
        login.add(txtPass, "w 60%");
        // mess err
        JLabel errmess = new JLabel("");
        errmess.setForeground(Color.red);
        login.add(errmess);

        /// listening enter 
        txtPass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Gọi phương thức xử lý đăng nhập khi nhấn Enter
                    errmess.setText("");
                    // check empty 
                    if (txtEmail.getText().trim().isEmpty() || new String(txtPass.getPassword()).isEmpty()) {
                        errmess.setText("Vui lòng điền đầy đủ thông tin .");
                        return;
                    }
                    // check type email 
                    if (!Validator.isValidEmail(txtEmail.getText().trim())) {
                        errmess.setText("Email không hợp lệ  .");
                        return;
                    }

                    try {
                        User userFound = dao.selectByEmail(txtEmail.getText().trim());
                        if (userFound == null) {
                            errmess.setText("Không tìm thấy tài khoản này !!!");
                            return;
                        }

                        if (!userFound.getPassword().equals(Auth.md5(new String(txtPass.getPassword())))) {
                            errmess.setText("Mật khẩu không đúng !!!");
                            return;
                        }
                        Auth.user = userFound;
                        DialogHelper.alert(null, "Đăng nhập thành công !!!");
                        // Đây là một phương thức tiện ích trong SwingUtilities trả về tổ tiên cấp cao nhất
                        //(vùng chứa cấp cao nhất, thường là JFrame hoặc JDialog) của thành phần được chỉ định.
                        SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
                        new MainForm().setVisible(true);
                        /// handle dispose
                    } catch (Exception err) {
                        System.out.println(err);
                        errmess.setText("Có lỗi truy vấn dữ liệu.");
                    }

                }
            }
        });

        Button cmd = new Button();
        cmd.setBackground(new Color(252, 132, 3));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN IN");
        login.add(cmd, "w 40%, h 40");

        // onclick login  
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                errmess.setText("");
                // check empty 
                if (txtEmail.getText().trim().isEmpty() || new String(txtPass.getPassword()).isEmpty()) {
                    errmess.setText("Vui lòng điền đầy đủ thông tin .");
                    return;
                }
                // check type email 
                if (!Validator.isValidEmail(txtEmail.getText().trim())) {
                    errmess.setText("Email không hợp lệ  .");
                    return;
                }

                try {
                    User userFound = dao.selectByEmail(txtEmail.getText().trim());
                    if (userFound == null) {
                        errmess.setText("Không tìm thấy tài khoản này !!!");
                        return;
                    }
                    if (!userFound.getPassword().equals(Auth.md5(new String(txtPass.getPassword())))) {
                        errmess.setText("Mật khẩu không đúng !!!");
                        return;
                    }
                    Auth.user = userFound;
                    DialogHelper.alert(null, "Đăng nhập thành công !!!");
                    // Đây là một phương thức tiện ích trong SwingUtilities trả về tổ tiên cấp cao nhất
                    //(vùng chứa cấp cao nhất, thường là JFrame hoặc JDialog) của thành phần được chỉ định.
                    SwingUtilities.getWindowAncestor((Component) ae.getSource()).dispose();
                    new MainForm().setVisible(true);
                    /// handle dispose
                } catch (Exception e) {
                    System.out.println(e);
                    errmess.setText("Có lỗi truy vấn dữ liệu.");
                }

            }
        });
        JButton cmdForget = new JButton("Forgot your password ?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        // onClick mode reset ;
        cmdForget.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new Form.ForgetPWForm().setVisible(true);
            }
        });
    }

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
