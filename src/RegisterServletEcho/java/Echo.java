package RegisterServletEcho.java;

import java.io.IOException;
import java.sql.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Echo
 */
@WebServlet("/Echo")
public class Echo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Echo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String fullname= request.getParameter("fullname");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        try{
        
        //loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");

	//creating connection with the database 
          Connection  con=DriverManager.getConnection
                     ("jdbc:mysql:/ /localhost:8080/nad","root","");

        PreparedStatement ps=con.prepareStatement
                  ("insert into Student values(fullname,name,email,pass)");

        ps.setString(1, fullname);
        ps.setString(2, name);
        ps.setString(3, email);
        ps.setString(4, pass);
        int i=ps.executeUpdate();
        
          if(i>0)
          {
            out.println("You are successfully registered");
          
               }

          response.sendRedirect("/RegisterServletEcho");
        }
        catch(Exception se)
        {
           se.printStackTrace();
           out.println("The operation was not successful");
           
        }
	
      }
}
