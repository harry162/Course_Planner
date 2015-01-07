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
import javax.swing.JOptionPane;

@WebServlet("/EditCourse")
public class EditCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditCourse() {
		super();
	}

	
	
	private getset getEntry(Integer id) throws ServletException {
		getset entry = null;
		Connection c = null;
		try {
			String url = "jdbc:mysql://localhost/cs320stu57";
			String username = "cs320stu57";
			String password = "9kjC9!.l";

			String sql = "select * from datalist where id = ?";
			
			c = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				entry = new getset(rs.getString("courseCode"),
						rs.getString("courseName"), rs.getString("prereq"));
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

		return entry;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	/*	if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("Login");
			return;
		}
*/
		 Integer id = Integer.valueOf( request.getParameter( "id" ) );
	     getset data = getEntry( id );
		 List<String> strprereq = new ArrayList<String>();
	
		
		
		String prereq1 = data.getPrereq();
		for (String substring : prereq1.split(" ")) {
			strprereq.add(substring);
		}
		
	///////////////////////////////////////////////////
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
/////////////////////////////////////////////////////////////
		request.getSession().setAttribute("id", id);
		request.setAttribute("id", id);
		request.setAttribute("strprereq", strprereq);
		request.setAttribute("entry", data);
		request.getRequestDispatcher("/WEB-INF/EditCourse.jsp").forward(
				request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("Login");
			return;
		}
		
		String courseCode = request.getParameter("courseCode");
		String courseName = request.getParameter("courseName");
		if (courseCode.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Course Code not inserted",
					"No Course Code Error", JOptionPane.ERROR_MESSAGE);
			response.sendRedirect("AddCourse");
			return;
		} else if (courseName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Course Name not inserted",
					"No Course Name Error", JOptionPane.ERROR_MESSAGE);
			response.sendRedirect("AddCourse");
			return;
		}

		String[] prereq1 = request.getParameterValues("prereq");
		String ns = null;

		if (prereq1 != null) {

			StringBuffer pre1 = new StringBuffer();

			for (int i = 0; i < prereq1.length; i++) {
				pre1.append(prereq1[i] + " ");
			}
			ns = pre1.toString();
		} else {

			ns = " ";

		}
/*		c.get(index).setCourseCode(request.getParameter("courseCode"));
		c.get(index).setCourseName(request.getParameter("courseName"));
		c.get(index).setPrereq(ns);
*/
		
		Integer id = (Integer) request.getSession().getAttribute("id");

		Connection c = null;
		
		
		try {
			String url = "jdbc:mysql://localhost/cs320stu57";
			String username = "cs320stu57";
			String password = "9kjC9!.l";

		
			String sql2 = "update datalist set courseCode = ?, courseName = ?, prereq = ? where id = ?";

			c = DriverManager.getConnection(url, username,
					password);
			PreparedStatement pstmt2 = c.prepareStatement(sql2);
			pstmt2.setString(1, courseCode);
			pstmt2.setString(2, courseName);
			pstmt2.setString(3, ns);
			pstmt2.setInt(4, id);
			pstmt2.executeUpdate();

			

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

			
		
		response.sendRedirect("DisplayCourse");
	}

}
