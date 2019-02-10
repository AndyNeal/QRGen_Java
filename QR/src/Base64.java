

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;


@WebServlet("/Base64")
public class Base64 extends HttpServlet {
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

		  String result = "";
		  
		  try {
			  Gson gson = new Gson();
			  QRCode code = (QRCode) gson.fromJson(jb.toString(), QRCode.class);
			  result = code.getBase64EncodedPng();
		  }
		  catch (Exception e) {
		  }
		  
		  response.setContentType("text/plain");
		  response.setCharacterEncoding("UTF-8");
		  response.getWriter().write("data:image/png;base64," + result);  
	}
}
