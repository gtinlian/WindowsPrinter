package fun.ipconfig;

import com.google.zxing.WriterException;
import fun.ipconfig.print.HtmlToImage;
import fun.ipconfig.print.PngPrint;
import fun.ipconfig.print.QrCode;

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SimpleTemplate {
    public static void main(String[] args) throws PrintException, IOException, WriterException {
        /**html 读取的是本地qrcode.png 所以需要生成二维码，具体参见index.html */
        OutputStream in = new FileOutputStream("qrcode.png");
        in.write(QrCode.genOrCodeToStream("创建二维码"));
        in.flush();
        in.close();

        /**将index.html 转成png格式的图片，保存到byte数组中*/
        byte[] image = HtmlToImage.getPngForFileUrl("index.html");

        /**打印对象，提供打印及选择功能*/
        PngPrint pn = new PngPrint();
        pn.printDriver().forEach((k,v)->{
            System.out.println("打印机编号： " + k + "  打印机名称: " + v );
        });
        System.out.println("请输入你选择的打印机编号:");
        int driverId = System.in.read() - 48;
        pn.setDriver(driverId);

        int li = 1;
        pn.print(image,1,65 + (li*5));
    }
}
