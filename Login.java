package hw5.servlet;

import hw5.model.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

@WebServlet(urlPatterns = "/Login", loadOnStartup = 1)
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request,
				response);

		/*
		 * response.setContentType("text/html"); PrintWriter out =
		 * response.getWriter(); out.println("<html>");
		 * out.println("<head><title>Login</title></head>");
		 * out.println("<body>"); out.println("<h3>Login</h3>");
		 * out.println("<form action='Login' method='post'>");
		 * out.println("Username: <input type='text' name='username' /> <br />"
		 * ); out.println(
		 * "Password: <input type='password' name='password' /> <br /><br />");
		 * out
		 * .println("<input type='submit' name='login' value='Login' /> <br />"
		 * ); out.println("</form>");
		 * out.println("<br><br>New User? --> <a href = 'Registration'>Register</a>"
		 * );
		 * out.println("<br><br><a href ='DisplayCourse'>List of Courses</a>");
		 * 
		 * out.println("</body></html>");
		 */
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
						rs.getString("password"), rs.getString("firstname"),
						rs.getString("firstname"));
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

		for (int a = 0; a < register.size(); a++) {
			getset getobj = register.get(a);
			String user = getobj.getUname();
			String pwd = getobj.getPwd();

			if (request.getParameter("username").equals(user)
					&& request.getParameter("password").equals(pwd)) {

				request.getSession().setAttribute("user", user);
				response.sendRedirect("DisplayCourse");
				return;	
			}


		}

		response.sendRedirect("Login");
		return;

	}

}