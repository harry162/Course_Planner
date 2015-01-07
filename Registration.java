package hw5.servlet;

import hw5.model.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	List<String> error = new ArrayList<String>();

	public Registration() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("error", error);
		request.getRequestDispatcher( "/WEB-INF/Registration.jsp" ).forward(request, response );
		
		/*response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Registration</title></head><body>");
		out.println("<h2>Registration Form</h2>");
		out.println("<form action='Registration' method='post'><table border=1>");

		for (int i = 0; i < error.size(); i++)
			out.println("<font color='red'>" + error.get(i) + "</font><br />");

		if (error.contains("* Username not Inserted !")
				|| error.contains("* Username already exists, Try another !")
				|| error.contains("* Username is shorter than 4 charecters !"))
			out.println("<tr><td bgcolor='red'>Username :</td><td bgcolor='red'><input name='username' type='text' /></td></tr>");
		else
			out.println("<tr><td>Username :</td><td><input name='username' type='text' /></td></tr>");

		if (error.contains("* Password not Inserted !")
				|| error.contains("* Password is shorter than 4 charecters !")
				|| error.contains("* Password and Re-Type Password not Matching !"))
			out.println("<tr><td bgcolor='red'>Password :</td><td bgcolor='red'><input name='password' type='password' /></td></tr>");
		else
			out.println("<tr><td>Password :</td><td><input name='password' type='password' /></td></tr>");

		if (error.contains("* Re-Type Password not Inserted !")
				|| error.contains("* Password and Re-Type Password not Matching !"))
			out.println("<tr><td bgcolor='red'>Re-Type Password :</td><td bgcolor='red'><input name='repassword' type='password' /></td></tr>");
		else
			out.println("<tr><td>Re-Type Password :</td><td><input name='repassword' type='password' /></td></tr>");

		out.println("<tr><td>First Name(Optional) :</td><td><input name='firstname' type='text' /></td></tr>");
		out.println("<tr><td>Last Name(Optional) :</td><td><input name='lastname' type='text' /></td></tr>");
		out.println("</table>");

		out.println("<br><input type='submit' name='Register' value='Register' /> <br />");
		out.println("</form></body></html>");
*/
		error.clear();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<getset> register = new ArrayList<getset>();

		Connection c = null;
		try {
			String url = "jdbc:mysql://localhost/cs320stu57";
			String username = "cs320stu57";
			String password = "9kjC9!.l";

			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from userinfo");

			while (rs.next()) {
				getset entry = new getset(rs.getString("username"),
						rs.getString("password"), rs.getString("firstname"), rs.getString("firstname"));
				register.add(entry);
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}

		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");

		if (fname.equals(null))
			fname = " ";
		if (lname.equals(null))
			lname = " ";

		for (int a = 0; a < register.size(); a++) {
			getset getobj = register.get(a);
			String uname = getobj.getUname();
			if (request.getParameter("username").equals(uname)) {
				String str = "* Username already exists, Try another !";
				error.add(str);
			}
		}
		if (user.isEmpty()) {
			String str = "* Username not Inserted !";
			error.add(str);
		}
		else if (user.length() < 4) {
			String str = "* Username is shorter than 4 charecters !";
			error.add(str);
		}
		if (pass.isEmpty()) {
			String str = "* Password not Inserted !";
			error.add(str);
		}
		else if (pass.length() < 4) {
			String str = "* Password is shorter than 4 charecters !";
			error.add(str);
		}
		if (repassword.isEmpty()) {
			String str = "* Re-Type Password not Inserted !";
			error.add(str);
		}

		if (!pass.equals(repassword)) {
			String str = "* Password and Re-Type Password are not Matching !";
			error.add(str);
		}

		
		

		if (error.size() == 0) {

			///////////////////////////////////////////////
			
			Connection c1 = null;
			try {
				String url = "jdbc:mysql://localhost/cs320stu57";
				String username = "cs320stu57";
				String password = "9kjC9!.l";

				String sql = "insert into userinfo ( username, password, firstname, lastname) values (?, ?, ?, ?)";

				c1 = DriverManager.getConnection(url, username, password);
				PreparedStatement pstmt = c1.prepareStatement(sql);
				pstmt.setString(1, user);
				pstmt.setString(2, pass);
				pstmt.setString(3, fname);
				pstmt.setString(4, lname);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				throw new ServletException(e);
			} finally {
				try {
					if (c1 != null)
						c1.close();
				} catch (SQLException e) {
					throw new ServletException(e);
				}
			}

			
			//////////////////////////////////////////////
			
			request.getSession().setAttribute("user", user);
			response.sendRedirect("DisplayCourse");
			return;		
			} else
			response.sendRedirect("Registration");

	}
}
