

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

@WebServlet("/PNG")
public class PNG extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer jb = new StringBuffer();
		String line = null;
  
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
			} 
		catch (Exception e) {
		}

		BufferedImage bi = null;
  
		try {
			Gson gson = new Gson();
			QRCode code = (QRCode) gson.fromJson(jb.toString(), QRCode.class);
			bi = code.getBufferedImage();
		}
		catch (Exception e) {
		}
		
        response.setContentType("image/png");

        OutputStream os = response.getOutputStream();
        ImageIO.write(bi, "png", os);
	}

}
