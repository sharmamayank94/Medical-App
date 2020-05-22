package Utility;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.*;

public class Barcode {

    public static File generateBarCode(String inputString) throws IOException {
        ByteArrayOutputStream out=QRCode.from(inputString).to(ImageType.JPG).withSize(100,100).stream();
        File file = new File("C:\\Users\\DeLL\\Desktop\\QR code\\demo.jpg");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(out.toByteArray());
        fos.flush();
        return file;
    }
}
