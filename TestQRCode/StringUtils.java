/* StringUtils.java */
//DES�[�ѱK
import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class StringUtils {

          //�[�K

          public static byte[] encrypt(byte[] src, byte[] key)throws Exception {

               //����ܤ@��64�줸���H���ܼƧ@��DES���K�_
               SecureRandom sr = new SecureRandom();
               //�q��l�K�_����DESKeySpec
               DESKeySpec dks = new DESKeySpec(key);
               //�إߤ@�K�_���;��M��Υ���DESKeySpec�নSecretKey
               SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
               SecretKey securekey = keyFactory.generateSecret(dks);
               //Cipher�����[�K�ާ@
               Cipher cipher = Cipher.getInstance("DES");
               //�αK�_��l��Cipher
               cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
               //�����ƨå[�K
               return cipher.doFinal(src);
          }//encrypt end


          //��ƥ[�K

          public final static String encrypt(String password,String DES_KEY1){

               try {
                    return byte2hex(encrypt(password.getBytes(),DES_KEY1.getBytes()));
               }
               catch(Exception e) {
               }
               return null;
          }//encrypt end


          //�ѱK

          public static byte[] decrypt(byte[] src, byte[] key)throws Exception {

               //��ܤ@��64�줸���H���ܼƧ@��DES���K�_
               SecureRandom sr = new SecureRandom();
               //�q��l�K�_����DESKeySpec
               DESKeySpec dks = new DESKeySpec(key);
               //�إߤ@�K�_���;��M��Υ���DESKeySpec�নSecretKey
               SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
               SecretKey securekey = keyFactory.generateSecret(dks);
               //Cipher�����ѱK�ާ@
               Cipher cipher = Cipher.getInstance("DES");
               //�αK�_��l��Cipher
               cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
               //�����ƨøѱK
               return cipher.doFinal(src);
          }//decrypt end

          //�K�X�ѱK

          public final static String decrypt(String data,String DES_KEY2){

               try {
                    return new String(decrypt(hex2byte(data.getBytes()),DES_KEY2.getBytes()));
               }catch(Exception e) {
               }
               return null;
          }//decrypt end


          //�G�i����r��

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
                    throw new IllegalArgumentException("���פ��O����!");
               byte[] b2 = new byte[b.length/2];
               for (int n = 0; n < b.length; n+=2) {
                    String item = new String(b,n,2);
                    b2[n/2] = (byte)Integer.parseInt(item,16);
               }
               return b2;
          }//hex2byte end

}//StringUtils end