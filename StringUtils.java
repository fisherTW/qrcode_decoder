/* StringUtils.java */
//DES加解密
import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class StringUtils {

          //加密

          public static byte[] encrypt(byte[] src, byte[] key)throws Exception {

               //先選擇一個64位元的隨機變數作為DES的密鑰
               SecureRandom sr = new SecureRandom();
               //從原始密鑰產生DESKeySpec
               DESKeySpec dks = new DESKeySpec(key);
               //建立一密鑰產生器然後用它把DESKeySpec轉成SecretKey
               SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
               SecretKey securekey = keyFactory.generateSecret(dks);
               //Cipher完成加密操作
               Cipher cipher = Cipher.getInstance("DES");
               //用密鑰初始化Cipher
               cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
               //獲取資料並加密
               return cipher.doFinal(src);
          }//encrypt end


          //資料加密

          public final static String encrypt(String password,String DES_KEY1){

               try {
                    return byte2hex(encrypt(password.getBytes(),DES_KEY1.getBytes()));
               }
               catch(Exception e) {
               }
               return null;
          }//encrypt end


          //解密

          public static byte[] decrypt(byte[] src, byte[] key)throws Exception {

               //選擇一個64位元的隨機變數作為DES的密鑰
               SecureRandom sr = new SecureRandom();
               //從原始密鑰產生DESKeySpec
               DESKeySpec dks = new DESKeySpec(key);
               //建立一密鑰產生器然後用它把DESKeySpec轉成SecretKey
               SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
               SecretKey securekey = keyFactory.generateSecret(dks);
               //Cipher完成解密操作
               Cipher cipher = Cipher.getInstance("DES");
               //用密鑰初始化Cipher
               cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
               //獲取資料並解密
               return cipher.doFinal(src);
          }//decrypt end

          //密碼解密

          public final static String decrypt(String data,String DES_KEY2){

               try {
                    return new String(decrypt(hex2byte(data.getBytes()),DES_KEY2.getBytes()));
               }catch(Exception e) {
               }
               return null;
          }//decrypt end


          //二進制轉字串

          public static String byte2hex(byte[] b) {
               String hs = "";
               String stmp = "";
               for (int n = 0; n < b.length; n++) {
                    stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
                    if (stmp.length() == 1)
                         hs = hs + "0" + stmp;
                    else
                         hs = hs + stmp;
               }
               return hs.toUpperCase();
          }//byte2hex end

          public static byte[] hex2byte(byte[] b) {
               if((b.length%2)!=0)
                    throw new IllegalArgumentException("長度不是偶數!");
               byte[] b2 = new byte[b.length/2];
               for (int n = 0; n < b.length; n+=2) {
                    String item = new String(b,n,2);
                    b2[n/2] = (byte)Integer.parseInt(item,16);
               }
               return b2;
          }//hex2byte end

}//StringUtils end