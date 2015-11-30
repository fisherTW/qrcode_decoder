/* QRCodeDecoderTest */
//QRCode �ѽX�{��

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
               String B= "";//�[�K�ﶵ
               String Temp= "";
               String keyword2 = "";//���_
               int remain,Num;
               boolean equals_string;//���
               BufferedImage image = null;// �]�w BufferedImage

               File imageFile = new File("D:\\TestQRCode\\TestQRCode.jpg");//Ū��QR Code���ɸ��|
               FileInputStream Load1 = new FileInputStream("D:/TestQRCode/key1.txt");//Ū��DES�[�K���_���|
               FileInputStream Load2 = new FileInputStream("D:/TestQRCode/key2.txt");//Ū���[�K�P�_�ﶵ���|

               BufferedReader buf1 = new BufferedReader(new InputStreamReader(System.in));//�[�K�P�_�ﶵ
               BufferedReader buf2 = new BufferedReader(new InputStreamReader(System.in));//��J���_


               //Ū��QR Code����
               try {
                    image = ImageIO.read(imageFile);
               }//try end
               catch (IOException e) {
                    System.out.println("Error: "+ e.getMessage());
               }//cath end

               try {
                    String decodedData = new String(decoder.decode(new J2SEImage(image)),"UTF-8");

                    //Ū���ƥ��s�ɪ��[�K�ﶵ(D:/TestQRCode/key2.txt)
                    while((remain = Load2.read()) != -1)
                    {
                         //��Ū�X�Ӫ��F��s��String A
                         Temp += String.valueOf((char)remain);
                    }//while end
                    A=Temp;
                    Load2.close();

                    do{
                         equals_string=false;

                         //��J�P�_�[�K�ﶵ�A�çP�_�O�_�P���eEncoder�ɿ�J���ﶵ�ۦP
                         System.out.print("1.�[�K�L��QRCode 2.���[�K��QRCode  �п�J�Ʀr :");
                         B = buf1.readLine();

                         if("1".equals(B)==false && "2".equals(B)==false){
                              System.out.println("Invalid entry");
                              equals_string=true;
                         }//if end
                         else if(B.equals(A)==false){
                              if("1".equals(B)==true){
                              	    //�����J�O2
                                   System.out.println("�P���eEncoder�ɿ�J���ﶵ���ۦP...");
                                   System.out.println("�ѽX�X�Ӫ���Ʒ|���~...�п��2");
                                   //equals_string=B.equals(A)==false && "1".equals(B);
                                   equals_string=true;
                              }//if end
                              if("2".equals(B)==true){
                              	    //�����J���O1
                                   System.out.println("�P���eEncoder�ɿ�J���ﶵ���ۦP...");
                                   System.out.println("�ѽX�X�Ӫ���Ƭ����gDES�ѱK�L�����");
                                   equals_string=false;
                              }//if end
                         }//else-if end
                    }while(equals_string);

                    //�[�K�P�_�ﶵ(�r�ꫬ�A)�ഫ����ƫ��A
                    Num=Integer.parseInt(B);

                    //�ѽX�}�l
                    switch(Num){
                         //�ϥ�DES�ѱK�����
                         case 1:
                              //Ū���ƥ��s�ɪ�DES�[�K���_(D:/TestQRCode/key1.txt)
                              A="";
                              Temp="";
                              while((remain = Load1.read()) != -1)
                              {
                                   //��Ū�X�Ӫ��F��s��String A[]
                                   Temp += String.valueOf((char)remain);
                              }//while end
                              A=Temp;
                              Load1.close();

                              //�����_�MDES�[�K���_�O�_�ۦP
                              do
                              {
                                   System.out.println("�ѱK---�п�JDES���_...8 bits");
                                   keyword2 ="";
                                   //��J���_
                                   keyword2 = buf2.readLine();

                                   if(keyword2.equals(A)==false)
                                        System.out.println("���_���~-----�Э��s��J���_�C�C�C");
                                   else
                                        System.out.println("���_���T-----�ѱK���C�C�C");
                              }while(keyword2.equals(A)==false);

                              //�NQR Code�ѽX�X�Ӫ���� �i��DES�ѱK
                              StringUtils xxx=new StringUtils();
                              String ggg = xxx.decrypt(decodedData,keyword2);
                              decodedData=ggg;
                         break;

                         //�S���g�LDES�[�K�����
                         case 2:
                         break;
                    }//switch end

                    ////��X�ѽX�����᪺���
                    System.out.println("�ѱK�᪺���="+decodedData);
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


