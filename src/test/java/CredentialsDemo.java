import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 生成加密后的密码
 * @Author xiaoh
 * @create 2019-09-04 10:23
 */
public class CredentialsDemo {
    public static void main(String[] args) {
        //
        String hash = "MD5";
        //密码
        Object credentials="123456";
        //盐值
        Object salt =null;
        //加密次数
        int num = 1024;
        Object simpleHash = new SimpleHash(hash, credentials, salt, num);
        System.out.println(simpleHash);
    }
}
