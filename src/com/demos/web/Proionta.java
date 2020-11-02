package com.demos.web;

import java.sql.*;
import com.demos.model.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Proionta extends HttpServlet
{
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
    {
        //resp.setContentType("text/html");
        //PrintWriter out = resp.getWriter();
        //out.println("Got product<br>");

	String barcode = req.getParameter("Barcode");
	String name = req.getParameter("name");
        String description = req.getParameter("Description");
	String color = req.getParameter("color");

	String conStr = "jdbc:mysql://localhost:3306/proionta";
	String user = "root";
	String pass = "D1179S1995";	
	try{
	    Connection conn = DriverManager.getConnection(conStr,user,pass);	
	    String sql = "INSERT INTO proionta (Name, Colour, Description) Values (?, ?, ?);";
	    PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	    ps.setString(1,name);
	    ps.setString(2,color);
	    ps.setString(3,description);

	    ps.executeUpdate();
            ResultSet rst = ps.getGeneratedKeys();
            rst.next();
            barcode = String.valueOf(rst.getInt(1));
	}catch(Exception e)
        {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println(e.toString());
	    return;
        }
        Product product = new Product(barcode, name, description, color);
	req.setAttribute("product",product.toHtml());

	//out.println("<br> barcode " + c);
	RequestDispatcher view = req.getRequestDispatcher("product.jsp");
	view.forward(req,resp);
    }
}

