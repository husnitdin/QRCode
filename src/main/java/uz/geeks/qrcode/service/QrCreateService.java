package uz.geeks.qrcode.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class QrCreateService {

    private static final String FILE_PATH = "src/main/resources/qr_codes/QRCode.png";

    public byte[] generateQRCode(String data, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);

        // Save QR Code to a file if you don't want to save it, delete upcoming 2 lines
        Path path = Paths.get(FILE_PATH);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(MatrixToImageWriter.toBufferedImage(bitMatrix), "PNG", baos);
        return baos.toByteArray();
    }

    public String generateQRCodeUI(String data) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 300, 300);

        // Convert BitMatrix to ByteArray
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", baos);
        byte[] qrCodeBytes = baos.toByteArray();

        // Encode the byte array to Base64
        return Base64.getEncoder().encodeToString(qrCodeBytes);
    }

}