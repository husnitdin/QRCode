package uz.geeks.qrcode.service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@Service
public class QrReadService {

    public String readQRCode(MultipartFile file) throws Exception {
        // Convert MultipartFile to BufferedImage
        BufferedImage bufferedImage = ImageIO.read(file.getInputStream());

        // Decode the QR code
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;

        try {
            result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            throw new Exception("QR Code not found in the image.");
        }
    }

}