/* QRCodeDecoderTest */
//QRCode 解碼程式

import jp.sourceforge.qrcode.codec.QRCodeDecoder;
import jp.sourceforge.qrcode.codec.data.QRCodeImage;
import jp.sourceforge.qrcode.codec.exception.DecodingFailedException;
import jp.sourceforge.qrcode.codec.exception.InvalidVersionInfoException;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.*;


public class QRCodeDecoderTest {

          public QRCodeDecoderTest() {
          }

          public static void main(String[] args) throws IOException {

               // TODO code application logic here
               QRCodeDecoder decoder = new QRCodeDecoder();

               String A= "";
               String B= "";//加密選項
               String Temp= "";
               String keyword2 = "";//金鑰
               int remain,Num;
               boolean equals_string;//比較
               BufferedImage image = null;// 設定 BufferedImage

               File imageFile = new File("D:\\TestQRCode\\TestQRCode.jpg");//讀取QR Code圖檔路徑
               FileInputStream Load1 = new FileInputStream("D:/TestQRCode/key1.txt");//讀取DES加密金鑰路徑
               FileInputStream Load2 = new FileInputStream("D:/TestQRCode/key2.txt");//讀取加密與否選項路徑

               BufferedReader buf1 = new BufferedReader(new InputStreamReader(System.in));//加密與否選項
               BufferedReader buf2 = new BufferedReader(new InputStreamReader(System.in));//輸入金鑰


               //讀取QR Code圖檔
               try {
                    image = ImageIO.read(imageFile);
               }//try end
               catch (IOException e) {
                    System.out.println("Error: "+ e.getMessage());
               }//cath end

               try {
                    String decodedData = new String(decoder.decode(new J2SEImage(image)),"UTF-8");

                    //讀取事先存檔的加密選項(D:/TestQRCode/key2.txt)
                    while((remain = Load2.read()) != -1)
                    {
                         //把讀出來的東西存到String A
                         Temp += String.valueOf((char)remain);
                    }//while end
                    A=Temp;
                    Load2.close();

                    do{
                         equals_string=false;

                         //輸入判斷加密選項，並判斷是否與之前Encoder時輸入的選項相同
                         System.out.print("1.加密過的QRCode 2.未加密的QRCode  請輸入數字 :");
                         B = buf1.readLine();

                         if("1".equals(B)==false && "2".equals(B)==false){
                              System.out.println("Invalid entry");
                              equals_string=true;
                         }//if end
                         else if(B.equals(A)==false){
                              if("1".equals(B)==true){
                              	    //原先輸入是2
                                   System.out.println("與之前Encoder時輸入的選項不相同...");
                                   System.out.println("解碼出來的資料會錯誤...請選擇2");
                                   //equals_string=B.equals(A)==false && "1".equals(B);
                                   equals_string=true;
                              }//if end
                              if("2".equals(B)==true){
                              	    //原先輸入的是1
                                   System.out.println("與之前Encoder時輸入的選項不相同...");
                                   System.out.println("解碼出來的資料為未經DES解密過的資料");
                                   equals_string=false;
                              }//if end
                         }//else-if end
                    }while(equals_string);

                    //加密與否選項(字串型態)轉換成整數型態
                    Num=Integer.parseInt(B);

                    //解碼開始
                    switch(Num){
                         //使用DES解密的資料
                         case 1:
                              //讀取事先存檔的DES加密金鑰(D:/TestQRCode/key1.txt)
                              A="";
                              Temp="";
                              while((remain = Load1.read()) != -1)
                              {
                                   //把讀出來的東西存到String A[]
                                   Temp += String.valueOf((char)remain);
                              }//while end
                              A=Temp;
                              Load1.close();

                              //比對金鑰和DES加密金鑰是否相同
                              do
                              {
                                   System.out.println("解密---請輸入DES金鑰...8 bits");
                                   keyword2 ="";
                                   //輸入金鑰
                                   keyword2 = buf2.readLine();

                                   if(keyword2.equals(A)==false)
                                        System.out.println("金鑰錯誤-----請重新輸入金鑰。。。");
                                   else
                                        System.out.println("金鑰正確-----解密中。。。");
                              }while(keyword2.equals(A)==false);

                              //將QR Code解碼出來的資料 進行DES解密
                              StringUtils xxx=new StringUtils();
                              String ggg = xxx.decrypt(decodedData,keyword2);
                              decodedData=ggg;
                         break;

                         //沒有經過DES加密的資料
                         case 2:
                         break;
                    }//switch end

                    ////輸出解碼完成後的資料
                    System.out.println("解密後的資料="+decodedData);
                    System.out.println("Decode DONE!");
               }//try end

               catch (DecodingFailedException dfe) {
                    System.out.println("Error: " + dfe.getMessage());
               }//catch end
               catch (Exception e) {
                    e.printStackTrace();
               }//catch end

          }//main end
}//QRCodeDecoderTest end


class J2SEImage implements QRCodeImage
{
          BufferedImage image;
          public J2SEImage(BufferedImage image) {
               this.image = image;
          }

          public int getWidth() {
               return image.getWidth();
          }

          public int getHeight() {
               return image.getHeight();
          }

          public int getPixel(int x, int y) {
               return image.getRGB(x, y);
          }

}//J2SEImage end


