/* QRCodeEncoderTest.java */
//QRCode 編碼程式

import com.swetake.util.Qrcode;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public class QRCodeEncoderTest {

          /** Creates a new instance of QRCodeEncoderTest */
          public QRCodeEncoderTest() {
          }


          public static void main(String[] args) throws IOException {

               try{
                    String testString = "";//要加密的資料
                    String keyword1 = "";//DES加密金鑰
                    String data;
                    String A="";
                    int Num;//switch number

                    BufferedReader buf1 = new BufferedReader(new InputStreamReader(System.in));//加密的字串
                    BufferedReader buf2 = new BufferedReader(new InputStreamReader(System.in));//加密與否選項
                    BufferedReader buf3 = new BufferedReader(new InputStreamReader(System.in));//DES加密金鑰

                    FileOutputStream fOut1 = new FileOutputStream("D:/TestQRCode/key1.txt");//記錄DES加密金鑰
                    FileOutputStream fOut2 = new FileOutputStream("D:/TestQRCode/key2.txt");//記錄加密與否選項

                    // QRCode版本設定
                    com.swetake.util.Qrcode testQrcode= new com.swetake.util.Qrcode();
                    testQrcode.setQrcodeErrorCorrect('M');
                    testQrcode.setQrcodeEncodeMode('B');
                    testQrcode.setQrcodeVersion(7);

                    System.out.println("==========QRCode encoder beta1==========");
                    System.out.println("|                                      |");
                    System.out.println("|             2007.06.14               |");
                    System.out.println("|                                      |");
                    System.out.println("========================================");
                    System.out.println("please input original data string.....");

                    //輸入待加密的字串
                    testString = buf1.readLine();


                    do{

                         //加密與否選項
                         System.out.print("1.加密 2.不加密  請輸入數字 :");
                         A = buf2.readLine();
                         if("1".equals(A)==false && "2".equals(A)==false)
                              System.out.println("Invalid entry");
                    }while("1".equals(A)==false && "2".equals(A)==false);

                    //加密與否選項寫入("D:/TestQRCode/key2.txt")
                    new PrintStream(fOut2).print (A);
                    fOut2.close();

                    //加密與否選項(字串型態)轉換成整數型態
                    Num=Integer.parseInt(A);

                    //編碼開始
                    switch(Num){
                         //使用DES加密的資料
                         case 1:
                              do{
                                   //輸入DES加密金鑰
                                   System.out.println("加密-----請輸入DES金鑰...8 bits");
                                   keyword1 = "";
                                   keyword1 = buf3.readLine();
                                   if(keyword1.length()!=8)
                                        System.out.println("你輸入的密碼格式錯誤...請重新輸入");
                                   else
                                        System.out.println("金鑰格式正確-----加密中。。。");
                              }while(keyword1.length()!=8);

                              //DES加密金鑰寫入("D:/TestQRCode/key1.txt")
                              new PrintStream(fOut1).print (keyword1);
                              fOut1.close();

                              //進行DES資料加密，加密後的資料傳回testString
                              StringUtils xxx=new StringUtils();
                              data = xxx.encrypt(testString,keyword1);
                              testString=data;
                         break;

                         //沒有經過DES加密的資料
                         case 2:
                         break;

                    }//switch end

                    //輸出編碼完成後的資料
                    System.out.println("Encode DONE!");

                    byte[] d = testString.getBytes("UTF-8");

                    // 設定圖檔寬度 140*140
                    BufferedImage bi= new BufferedImage(140, 140, BufferedImage.TYPE_INT_RGB);
                    // createGraphics
                    Graphics2D g = bi.createGraphics();

                    // set background
                    g.setBackground(Color.WHITE);
                    g.clearRect(0, 0, 140, 140);

                    // 設定字型顏色 => BLACK
                    g.setColor(Color.BLACK);

                    // 轉出 Bytes
                    if (d.length>0 && d.length <120)
                    {
                         boolean[][] s = testQrcode.calQrcode(d);
                         for (int i=0;i<s.length;i++){
                              for (int j=0;j<s.length;j++){
                                   if (s[j][i]){
                                        g.fillRect(j*3+2,i*3+2,3,3);
                                   }
                              }
                         }
                    }

                    g.dispose();
                    bi.flush();

                    // 設定產生QRCode檔案路徑
                    String FilePath="D:\\TestQRCode\\TestQRCode.jpg";
                    File f = new File(FilePath);

                    // 產生TestQRCode JPG File
                    ImageIO.write(bi, "jpg", f);

               }//try end

               catch (Exception e) {
                    System.out.println("error!");
               e.printStackTrace();
               System.err.println ("Unable to write to file");
               System.exit(-1);
               } //catch  end

          } //main  end


}//QRCodeEncoderTest end


