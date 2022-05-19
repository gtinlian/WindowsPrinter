package fun.ipconfig.print;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成二维码
 */
public class QrCode {
    private static final String DEFAULT_TYPE = "png";
    private static final Integer DEFAULT_SIZE = 200;

    public static byte[] genOrCodeToStream(String content) throws IOException, WriterException {
        return genOrCodeToStream(content, DEFAULT_SIZE, DEFAULT_TYPE);
    }

    public static byte[] genOrCodeToStream(String content, int size) throws IOException, WriterException {
        return genOrCodeToStream(content, size, DEFAULT_TYPE);
    }

    public static byte[] genOrCodeToStream(String content, int size, String picType) throws IOException, WriterException {

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF8");
        //生成矩阵
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, size, size, hints);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, picType, bout);
        return bout.toByteArray();

    }

}
