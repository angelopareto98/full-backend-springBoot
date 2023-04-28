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

@Component
public class QrcodeGenerator {
    @Value("${project.qrcodeImg}")
    private static String QRCODE_PATH;

    public static void generateQRCode(UserDto userDto) throws WriterException, IOException {

        String qrCodeName = userDto.getName() + userDto.getId() + "-QRCODE.png";
        QRCodeWriter qRCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qRCodeWriter.encode("Id : " + userDto.getId() + "\nName : " + userDto.getName()
                + "\nEmail : " + userDto.getEmail() + "\nMot de passe : " + userDto.getPassword(),
                BarcodeFormat.QR_CODE, 350, 350);

        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

        File qrFile = new File(qrCodeName);

        if (!qrFile.exists()) {
            qrFile.mkdir();
        }

        ImageIO.write(image, "png", qrFile);

    }

}
