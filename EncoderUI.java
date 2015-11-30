import com.swetake.util.Qrcode;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

//public class QRCodeEncoderTest extends Thread{
	
//	

    /** Creates a new instance of QRCodeEncoderTest */
/*   
    
    public static String dataset(String data){
    	testString=data;
    	return testString;
    }
 */  

    	
    
    /**
     * @param args the command line arguments
     */
    
    /*
    public static void main(String[] args) {
        

        
    } // end main
    */
    
    
////////////////////////////////

public class EncoderUI extends Applet implements ActionListener{

		
	
	
	
	Image ig;
	//String input1new="";
	String input2new="";
	TextField input1=new TextField(200);
	TextField input2=new TextField(200);
	
	public void init(){
		
		
		Label lb1=new Label("請輸入欲編碼字串:");
		Label lb2=new Label("請輸入密碼(請牢記):");
		input2.setEchoChar('*');
		
		ig=getImage(getDocumentBase(),"TestQRCode.jpg");
		
		
		
		setLayout(null);
		add(lb1);
		add(lb2);
		add(input1);
  	add(input2);
		
		
		input1.addActionListener(this);
		input2.addActionListener(this);
		

		lb1.setBounds(0,0,200,20);
		lb2.setBounds(0,100,200,20);
		input1.setBounds(0,50,200,20);
		input2.setBounds(0,150,200,20);
		
	}
	
	
	
	public void paint(Graphics g){
		g.drawImage(ig,300,100,this);
	}
	
	
	
	public void actionPerformed(ActionEvent e){

		String input1new=input1.getText();

				//System.out.println(input1new);
				        try{
            
            // TODO code application logic here
            
            // Constructor Qrcode Object
            String testString = input1new; 
            Qrcode testQrcode  = new Qrcode();
            //com.swetake.util.Qrcode testQrcode  = new com.swetake.util.Qrcode();
            testQrcode.setQrcodeErrorCorrect('M');
            testQrcode.setQrcodeEncodeMode('B');
            testQrcode.setQrcodeVersion(7);
            
            // 設定QR Code 編碼內容
            //String testString = "";
            //dataset("123");
            //testString = testString+"JavaWorld .\n";
           // testString = testString+"中文Java討論網站\n";
            //testString = testString+"http://www.javaworld.com\n";
            
            // getBytes
            //byte[] d = testString.getBytes("Big5");
            // 改為
            byte[] d = testString.getBytes("UTF-8");


            
            // 設定圖檔寬度 140*140
            BufferedImage bi = new BufferedImage(140, 140, BufferedImage.TYPE_INT_RGB);
            
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
            String FilePath="D:\\EncoderUIApp\\TestQRCode.jpg";
            File f = new File(FilePath);
            
            // 產生TestQRCode JPG File
            ImageIO.write(bi, "jpg", f);
            
        } // end try
         catch (Exception ex) {
            ex.printStackTrace();
        } // end catch 

		//QRCodeEncoderTest hahaha=new QRCodeEncoderTest();
		//hahaha.dataset(input1new);
		//hahaha.start();
		
		
		//input2new=input2.getText();


}
}
