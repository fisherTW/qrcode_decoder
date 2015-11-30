/* QRCodeEncoderTest.java */
//QRCode �s�X�{��

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
                    String testString = "";//�n�[�K�����
                    String keyword1 = "";//DES�[�K���_
                    String data;
                    String A="";
                    int Num;//switch number

                    BufferedReader buf1 = new BufferedReader(new InputStreamReader(System.in));//�[�K���r��
                    BufferedReader buf2 = new BufferedReader(new InputStreamReader(System.in));//�[�K�P�_�ﶵ
                    BufferedReader buf3 = new BufferedReader(new InputStreamReader(System.in));//DES�[�K���_

                    FileOutputStream fOut1 = new FileOutputStream("D:/TestQRCode/key1.txt");//�O��DES�[�K���_
                    FileOutputStream fOut2 = new FileOutputStream("D:/TestQRCode/key2.txt");//�O���[�K�P�_�ﶵ

                    // QRCode�����]�w
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

                    //��J�ݥ[�K���r��
                    testString = buf1.readLine();


                    do{

                         //�[�K�P�_�ﶵ
                         System.out.print("1.�[�K 2.���[�K  �п�J�Ʀr :");
                         A = buf2.readLine();
                         if("1".equals(A)==false && "2".equals(A)==false)
                              System.out.println("Invalid entry");
                    }while("1".equals(A)==false && "2".equals(A)==false);

                    //�[�K�P�_�ﶵ�g�J("D:/TestQRCode/key2.txt")
                    new PrintStream(fOut2).print (A);
                    fOut2.close();

                    //�[�K�P�_�ﶵ(�r�ꫬ�A)�ഫ����ƫ��A
                    Num=Integer.parseInt(A);

                    //�s�X�}�l
                    switch(Num){
                         //�ϥ�DES�[�K�����
                         case 1:
                              do{
                                   //��JDES�[�K���_
                                   System.out.println("�[�K-----�п�JDES���_...8 bits");
                                   keyword1 = "";
                                   keyword1 = buf3.readLine();
                                   if(keyword1.length()!=8)
                                        System.out.println("�A��J���K�X�榡���~...�Э��s��J");
                                   else
                                        System.out.println("���_�榡���T-----�[�K���C�C�C");
                              }while(keyword1.length()!=8);

                              //DES�[�K���_�g�J("D:/TestQRCode/key1.txt")
                              new PrintStream(fOut1).print (keyword1);
                              fOut1.close();

                              //�i��DES��ƥ[�K�A�[�K�᪺��ƶǦ^testString
                              StringUtils xxx=new StringUtils();
                              data = xxx.encrypt(testString,keyword1);
                              testString=data;
                         break;

                         //�S���g�LDES�[�K�����
                         case 2:
                         break;

                    }//switch end

                    //��X�s�X�����᪺���
                    System.out.println("Encode DONE!");

                    byte[] d = testString.getBytes("UTF-8");

                    // �]�w���ɼe�� 140*140
                    BufferedImage bi= new BufferedImage(140, 140, BufferedImage.TYPE_INT_RGB);
                    // createGraphics
                    Graphics2D g = bi.createGraphics();

                    // set background
                    g.setBackground(Color.WHITE);
                    g.clearRect(0, 0, 140, 140);

                    // �]�w�r���C�� => BLACK
                    g.setColor(Color.BLACK);

                    // ��X Bytes
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

                    // �]�w����QRCode�ɮ׸��|
                    String FilePath="D:\\TestQRCode\\TestQRCode.jpg";
                    File f = new File(FilePath);

                    // ����TestQRCode JPG File
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


