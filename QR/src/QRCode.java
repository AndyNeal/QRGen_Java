import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

public class QRCode {
	private String data;
	private int height;
	private int width;
	private int margin;

	public QRCode(String data, int height, int width, int margin) {
		this.data = data;
		this.height = height;
		this.width = width;
		this.margin = margin;
	}

	public String getVars() {
		return "Text: " + data + "\nHeight: " + height + "\nWidth: " + width + "\nMargin: " + margin;
	}

	public BufferedImage getBufferedImage() {

		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>(1);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, margin);
        
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BufferedImage bi = null;
        try {
			BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height, hints);
			bi = MatrixToImageWriter.toBufferedImage(bitMatrix);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return bi;
	}

	public String getBase64EncodedPng() {
		String result = "";

		BufferedImage bi = getBufferedImage();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ImageIO.write(bi, "png", os);
			result = DatatypeConverter.printBase64Binary(os.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
