package com.anghack.backfullcourse.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.anghack.backfullcourse.payload.UserDto;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QrcodeGenerator {
    // @Value("${project.qrcodeimg}")
    // private static String qrcodePath;

    private static String qrcodePath = "./imgQRCode/";

    public static void generateQRCode(UserDto userDto) throws WriterException, IOException {

        String qrCodeName = userDto.getName() + userDto.getId() + "-QRCODE.png";
        QRCodeWriter qRCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qRCodeWriter.encode("Id : " + userDto.getId() + "\nName : " + userDto.getName()
                + "\nEmail : " + userDto.getEmail() + "\nMot de passe : " + userDto.getPassword(),
                BarcodeFormat.QR_CODE, 350, 350);

        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

        File dir = new File(qrcodePath);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File qrFile = new File(dir, qrCodeName);
        ImageIO.write(image, "png", qrFile);

    }
}
