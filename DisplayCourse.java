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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/DisplayCourse", loadOnStartup = 1)
public class DisplayCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	List<getset> datalist = new ArrayList<getset>();
	List<getset> changeobj = new ArrayList<getset>();
	List<String> courseCode = new ArrayList<String>();
	List<String> courseName = new ArrayList<String>();
	List<String> prereq = new ArrayList<String>();
	List<getset> register = new ArrayList<getset>();
	public int count = 1;

	public DisplayCourse() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<getset> datalist = new ArrayList<getset>();
		Connection c = null;
		try {
			String url = "jdbc:mysql://localhost/cs320stu57";
			String username = "cs320stu57";
			String password = "9kjC9!.l";

			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from datalist");

			while (rs.next()) {
				getset entry = new getset(rs.getString("courseCode"),
						rs.getString("courseName"), rs.getString("prereq"));
				datalist.add(entry);
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

		request.setAttribute("datalist", datalist);
		request.getSession().setAttribute("datalist", datalist);
		request.getRequestDispatcher("/WEB-INF/DisplayCourse.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
