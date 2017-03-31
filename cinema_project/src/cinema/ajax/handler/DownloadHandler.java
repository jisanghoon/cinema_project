package cinema.ajax.handler;

import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.controller.CommandHandler;

public class DownloadHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String filename = req.getParameter("filename");
		String uploadPath = req.getRealPath("upload");
		String sFilePath = uploadPath + "\\" + filename;// 실제경로

		byte b[] = new byte[4096];// 4k

		FileInputStream in = new FileInputStream(sFilePath);
		String sMimeType = req.getServletContext().getMimeType(sFilePath);

		System.out.println("sMimeType : " + sMimeType);

		if (sMimeType == null) {
			sMimeType = "application/octet-stream";
		}
		res.setContentType(sMimeType);

		String sEncoding = URLEncoder.encode(filename, "utf-8");
		res.setHeader("Content-Disposition", "attachment; filename=" + sEncoding);

		ServletOutputStream out = res.getOutputStream();
		int numRead;

		while ((numRead = in.read(b, 0, b.length)) != -1) {
			out.write(b, 0, numRead);

		}
		out.flush();
		out.close();
		in.close();

		return null;
	}

}
