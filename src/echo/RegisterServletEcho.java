package echo;

import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServletEcho
 */
@WebServlet("/RegisterServletEcho")
public class RegisterServletEcho extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServletEcho() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// TODO Auto-generated method stub
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>User Info</title></head>");
        out.println("<body>");
        out.println("<center><h1>All Users</h1>");
        Connection conn = null;
        Statement stmt = null;
        out.println("<table border=1>");
        out.println("<tr>");
        out.println("<th>FULL NAME</th>");
  		out.println("<th>NAME</th>");
  		out.println("<th>EMAIL</th>");
  		out.println("<th>GENDER</th>");
  		 out.println("</tr>");
        try {
          Class.forName("com.mysql.jdbc.Driver");
          conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nad","root","");
          stmt = conn.createStatement();
          String orderBy = request.getParameter("sort");
          if ((orderBy == null) || orderBy.equals("")) {
            orderBy = "name";
          }
          String orderByDir = request.getParameter("sortdir");
          if ((orderByDir == null) || orderByDir.equals("")) {
            orderByDir = "asc";
          }
          String query = "SELECT * from student";
             
          ResultSet rs = stmt.executeQuery(query);
          while (rs.next()) {
        	  
       
           
      		out.println("<tr>");
      		out.println("<td>"+ rs.getString("name") +"</td>");
      		out.println("<td>"+ rs.getString("fullname") +"</td>");
      		out.println("<td>"+ rs.getString("email") +"</td>");
      		out.println("<td>"+ rs.getString("gender") +"</td>");
      		out.println("</tr>");
      		
                     
            }
        }
                   
        catch (SQLException e) {
            out.println("An error occured while retrieving " + "all student: " 
                + e.toString());
          } catch (ClassNotFoundException e) {
            throw (new ServletException(e.toString()));
          } finally {
            try {
              if (stmt != null) {
                stmt.close();
              }
              if (conn != null) {
                conn.close();
              }
            } catch (SQLException ex) {
            }
          }
        out.println("</table>");
          out.println("</center>");
          out.println("</body>");
          out.println("</html>");
          out.close();
        }
		
	}       
		

	

	


