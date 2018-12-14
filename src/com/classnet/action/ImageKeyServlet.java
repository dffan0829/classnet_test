package com.classnet.action;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classnet.util.Image;
import com.classnet.util.ImageBuilder;

@SuppressWarnings("serial")
public class ImageKeyServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		OutputStream out = resp.getOutputStream();
		String random = random();
		req.getSession().setAttribute("image_key", random);
		Image img = new ImageBuilder(random);
		try {
			img.out(out);
		} catch (Exception e) {
			throw new ServletException();
		}
		out.close();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	String[] str = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
			"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
			"Z" };

	private String random() {
		StringBuffer sb = new StringBuffer();
		sb.append(str[(int) (Math.random() * 35)]);
		sb.append(str[(int) (Math.random() * 35)]);
		sb.append(str[(int) (Math.random() * 35)]);
		sb.append(str[(int) (Math.random() * 35)]);
		return sb.toString();
	}
}
