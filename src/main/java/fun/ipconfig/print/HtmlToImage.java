package fun.ipconfig.print;

import gui.ava.html.parser.HtmlParser;
import gui.ava.html.parser.HtmlParserImpl;
import gui.ava.html.renderer.ImageRenderer;
import gui.ava.html.renderer.ImageRendererImpl;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class HtmlToImage {
    public static byte[] getPngForPage(String page) {
        HtmlParser htmlParser = new HtmlParserImpl();
        ByteArrayOutputStream imageObject = new ByteArrayOutputStream();
        ImageRenderer imageRenderer = new ImageRendererImpl(htmlParser);
        htmlParser.loadHtml(page);
        imageRenderer.getBufferedImage();
        imageRenderer.setWidth(250);
        imageRenderer.isAutoHeight();
        imageRenderer.saveImage(imageObject, true);
        return imageObject.toByteArray();
    }

    public static byte[] getPngForFileUrl(String url) throws IOException {
        String htmlPage = new String(new FileInputStream(url).readAllBytes());
        return getPngForPage(htmlPage);
    }

}
