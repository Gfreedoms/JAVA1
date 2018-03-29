package DB;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class RegisterServletDB
 */
@WebServlet("/RegisterServletDB")
public class RegisterServletDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServletDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String fullname= request.getParameter("fullname");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String gender = request.getParameter("gender");
        try{
        
        //loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");

	//creating connection with the database 
          Connection  con=DriverManager.getConnection
                     ("jdbc:mysql://localhost:3306/nad","root","");
          String Connection ="insert into student values(?,?,?,?,?)";

        PreparedStatement ps=con.prepareStatement(Connection);

        ps.setString(1, fullname);
        ps.setString(2, name);
        ps.setString(3, email);
        ps.setString(4, pass);
        ps.setString(5, gender);
        int i=ps.executeUpdate();
        
          if(i>0)
          {
            out.println("You are successfully registered");
          
               }

          response.sendRedirect("RegisterServletEcho");
  		
        }
        catch(Exception se)
        {
           se.printStackTrace();
           out.println(se);
                     
        }
	
      }
  }