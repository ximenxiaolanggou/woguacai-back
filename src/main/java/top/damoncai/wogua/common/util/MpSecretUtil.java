package top.damoncai.wogua.common.util;


import com.baomidou.mybatisplus.core.toolkit.AES;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/5/11 10:22
 */
public class MpSecretUtil {

    public static void main(String[] args) {
        String username = "root";
        String pwd = "zhishun.cai";
        String url = "jdbc:mysql://47.100.22.236:3306/wogua?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8";

        // 生成 16 位随机 AES 密钥
        String randomKey = AES.generateRandomKey();
        System.out.println("key => " + randomKey); // 709d7cf3e33f365f
        // 随机密钥加密
        String usernameS = AES.encrypt(username, randomKey);
        String pwdS = AES.encrypt(pwd, randomKey);
        String urlS = AES.encrypt(url, randomKey);
        System.out.println("usernameS => " + usernameS);
        System.out.println("pwdS => " + pwdS);
        System.out.println("urlS => " + urlS);
    }
}
