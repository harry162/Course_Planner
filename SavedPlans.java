package hw5.servlet;

import hw5.model.finishmvc;
import hw5.model.getset;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SavedPlans")
public class SavedPlans extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SavedPlans() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<finishmvc> lastlist = new ArrayList<finishmvc>();
		String user = (String) request.getSession().getAttribute("user");
		List<String> allsaved = new ArrayList<String>();
		Connection c = null;
		try {
			String url = "jdbc:mysql://localhost/cs320stu57";
			String username = "cs320stu57";
			String password = "9kjC9!.l";

			String sql = "select * from lastTable where username = ?";

			c = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, user);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				finishmvc entry = new finishmvc(rs.getString("username"),
						rs.getString("saved_on"), rs.getString("quarter"),
						rs.getString("courseCode"), rs.getString("courseName"),
						rs.getString("prereq"));
				lastlist.add(entry);
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
		
		try {
			String url = "jdbc:mysql://localhost/cs320stu57";
			String username = "cs320stu57";
			String password = "9kjC9!.l";

			String sql = "select distinct saved_on from lastTable where username = ?";

			c = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, user);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String entry = rs.getString("saved_on");
				allsaved.add(entry);
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
		Collections.reverse(allsaved);
		request.setAttribute("user", user);
		request.setAttribute("allsaved", allsaved);
		request.getRequestDispatcher("/WEB-INF/SavedPlans.jsp").forward(
				request, response);
			}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
