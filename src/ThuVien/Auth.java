package ThuVien;

import Enity.User;
import java.security.MessageDigest;

public class Auth {

    public static User user = null;

    public static boolean isNullOrEmpty(char[] password) {
        return password == null || password.length == 0;
    }

    public static void clear() {
        Auth.user = null;
    }

    public static boolean isLogin() {
        return Auth.user != null;
    }

    public static boolean isManager() {
        return Auth.isLogin() && user.getRoleId();
    }

    public static String md5(String msg) {
        try {
            //    lấy một đối tượng MessageDigest được cấu hình để sử dụng thuật toán băm MD5.
            MessageDigest md = MessageDigest.getInstance("MD5");
            // chuyển thành mảng bytes và cũng cấp cho MessageDigest 
            md.update(msg.getBytes());
            // md.digest() trong messagedigest để bắt đầu tính toán trả về mảng byte 
            byte byteData[] = md.digest();
            //chuyển đổi mỗi byte trong mảng byteData thành một xâu hex 
            //và nối chúng vào StringBuffer sb. 
            //Điều này sử dụng một cách cụ thể để đảm bảo rằng các giá trị hex có thể được biểu diễn bằng đúng hai ký tự.
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                //& 0xff thực hiện một phép AND bit với 0xff (255 trong hệ thập phân).
                //Điều này giúp đảm bảo rằng chỉ giữ lại 8 bit thấp nhất của giá trị byte, loại bỏ mọi bit cao.
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            // nếu không có lỗi gì xảy ra trả về xâu hex 
            return sb.toString();
        } catch (Exception ex) {
            System.out.println(ex);
            return "";
        }
    }
}
