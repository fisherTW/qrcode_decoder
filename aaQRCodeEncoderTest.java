import com.swetake.util.Qrcode;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
 

class QRCodeEncoderTest {
	
	//String testString = ""; 

    /** Creates a new instance of QRCodeEncoderTest */
    /*public QRCodeEncoderTest() {
    }*/
    

   /* public static String dataset(String data){
    	testString=data;
    	return testString;
    }*/
    

    	
    
    /**
     * @param args the command line arguments
     */
    
    //public final static String dataset(String testString)throws IOException{
    
      public void dataset(String testString)throws IOException{
        try{
        	  
           //testString=data; 
            // TODO code application logic here
            
            // Constructor Qrcode Object
            com.swetake.util.Qrcode testQrcode = new com.swetake.util.Qrcode();
            testQrcode.setQrcodeErrorCorrect('M');
            testQrcode.setQrcodeEncodeMode('B');
            testQrcode.setQrcodeVersion(7);
            
            // 設定QR Code 編碼內容
            //String testString = "";
           // dataset("123");
            //testString = testString+"JavaWorld .\n";
           // testString = testString+"中文Java討論網站\n";
            //testString = testString+"http://www.javaworld.com\n";
            

            byte[] d = testString.getBytes("UTF-8");


            
            // 設定圖檔寬度 140*140
            BufferedImage bi 
            = new BufferedImage(140, 140, BufferedImage.TYPE_INT_RGB);
            
            // createGraphics
            Graphics2D g = bi.createGraphics();
            
            // set background
            g.setBackground(Color.WHITE);
            g.clearRect(0, 0, 140, 140);
            
            // 設定字型顏色 => BLACK
            g.setColor(Color.BLACK);
            
            // 轉出 Bytes
            if (d.length>0 && d.length <120){
                boolean[][] s = testQrcode.calQrcode(d);
                for (int i=0;i<s.length;i++){
                    for (int j=0;j<s.length;j++){
                        if (s[j][i]) {
                            g.fillRect(j*3+2,i*3+2,3,3);
                        }
                    }
                }
            }
            
            g.dispose();
            bi.flush();
            
            // 設定 產生檔案路徑
            String FilePath="D:\\TestQRCode\\TestQRCode.jpg";
            File f = new File(FilePath);
            
            // 產生TestQRCode JPG File
            ImageIO.write(bi, "jpg", f);
            
        } // end try
        catch (Exception e) {
               System.out.println("error!");
               e.printStackTrace();
               System.err.println ("Unable to write to file");
               System.exit(-1);
        } // end catch

   } // end dataset 
}

