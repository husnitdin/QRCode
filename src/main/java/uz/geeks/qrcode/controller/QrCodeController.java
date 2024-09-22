package uz.geeks.qrcode.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.geeks.qrcode.service.QrCreateService;
import uz.geeks.qrcode.service.QrReadService;

@Controller
@RequestMapping("/api/qrcode")
public class QrCodeController {

    private final QrCreateService qrCodeService;
    private final QrReadService qrCodeReaderService;

    public QrCodeController(QrCreateService qrCodeService, QrReadService qrCodeReaderService) {
        this.qrCodeService = qrCodeService;
        this.qrCodeReaderService = qrCodeReaderService;
    }

    @GetMapping(value = "/generate", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] generateQRCode(@RequestParam String data) throws Exception {
        return qrCodeService.generateQRCode(data, 300, 300);
    }

    @GetMapping("/generate-qr")
    public String generateQRCodeUI(@RequestParam String data, Model model) throws Exception {

        model.addAttribute("qrCodeBase64", qrCodeService.generateQRCodeUI(data));
        return "qrCode";
    }

    @PostMapping("/read")
    public String readQRCode(@RequestParam("file") MultipartFile file) throws Exception {
        return qrCodeReaderService.readQRCode(file);
    }
}
