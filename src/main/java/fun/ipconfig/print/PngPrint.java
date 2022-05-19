package fun.ipconfig.print;

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class PngPrint {
    /**
     * 纸张的宽度
     */
    private Integer pageWidth = 58;
    /**
     * 纸张的高度
     */
    private Integer pageHighe = 80;
    /**
     * 当前的打印机为系统默认打印机
     */
    private PrintService driver = PrintServiceLookup.lookupDefaultPrintService();

    /**获取打印机*/
    public Map<String,String> printDriver(){
        Map<String,String> map = new HashMap<>();
        PrintService p[] = PrinterJob.lookupPrintServices();
        for(int i = 0;i < p.length;i++){
            map.put(String.valueOf(i+1),p[i].getName());
        }
        return map;
    }

    /**配置打印机 */
    public void setDriver(int driverId) {
        this.driver = PrinterJob.lookupPrintServices()[driverId-1];
    }

    public void print(byte[] arrayStream, int count,int higth) throws PrintException {
        ByteArrayInputStream ib = new ByteArrayInputStream(arrayStream);
        print(ib,count,higth);
    }
    public void print(InputStream ib, int count,int higth) throws PrintException {
        DocFlavor dof = null;
        dof = DocFlavor.INPUT_STREAM.PNG;
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        DocAttributeSet das = new HashDocAttributeSet();
        das.add(new MediaPrintableArea(0, 0, 52, higth, MediaPrintableArea.MM));
        if (dof == null) {
            System.out.println(dof);
        }
        Doc doc = new SimpleDoc(ib, dof, das);
        DocPrintJob job = driver.createPrintJob();
        job.print(doc, pras);
    }

    public static void main(String[] args) throws IOException {
        PngPrint pn = new PngPrint();
        pn.printDriver();
        int value = System.in.read();
        System.out.println(value-48);
    }
}
