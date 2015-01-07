package hw5.servlet;

import hw5.model.getset;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

@WebServlet(urlPatterns = "/AddCourse")
public class AddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// int idSeed;

	public AddCourse() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("Login");
			return;
		}

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

		request.getRequestDispatcher("/WEB-INF/AddCourse.jsp").forward(request,
				response);

		/*
		 * response.setContentType( "text/html" ); PrintWriter out =
		 * response.getWriter(); out.println(
		 * "<html><head><title>Add Course</title></head><body>" );
		 * out.print("<h4>You are logged in as : "+
		 * request.getSession().getAttribute( "user" ) +
		 * "&nbsp &nbsp &nbsp</h4>"); out.println("<h2>Add Course</h2>");
		 * 
		 * out.println( "<a   href='Logout' >Logout</a><br /><br />" );
		 * 
		 * out.println( "<form action='AddCourse' method='post'>" );
		 * out.println( "<table border=1 ><tr><td>"); out.println(
		 * "Code :</td><td> <input type='text' name='courseCode' /></td> </tr >"
		 * ); out.println(
		 * "<tr><td>Title : </td><td><input type='text' name='courseName'/></td></tr>"
		 * ); out.println( "<tr><td>Prerequisite(s) : </td>"); out.println(
		 * "<td><ul>");
		 * 
		 * for(int i=0 ; i<datalist.size() ; i++){
		 * 
		 * getset getobj = datalist.get(i); String str = getobj.getCourseCode();
		 * out.println( "<li>"+ str+
		 * "<input type='checkbox' name='prereq' value='"+str+"'></li>");
		 * 
		 * 
		 * } out.println( "</ul></td>"); out.println(
		 * "</tr><tr><td><input type='submit' name='add' value='Add Course' /> </td></tr>"
		 * ); out.println("</table>"); out.println( "</form>" );
		 * 
		 * out.println( "</body></html>" );
		 */}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("Login");
			return;
		}

		String courseCode = request.getParameter("courseCode");
		String courseName = request.getParameter("courseName");
		String[] prereq1 = request.getParameterValues("prereq");

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

		String newstring = " ";

		if (prereq1 != null) {

			StringBuffer pre1 = new StringBuffer();

			for (int i = 0; i < prereq1.length; i++) {
				pre1.append(prereq1[i] + " ");
			}
			newstring = pre1.toString();
		} else {

			newstring = " ";

		}

		/*
		 * List<getset> entries = (List<getset>)
		 * getServletContext().getAttribute("datalist"); entries.add(new getset(
		 * courseCode, courseName, newstring));
		 */

		Connection c = null;
		try {
			String url = "jdbc:mysql://localhost/cs320stu57";
			String username = "cs320stu57";
			String password = "9kjC9!.l";

			String sql = "insert into datalist (courseCode, courseName, prereq) values (?, ?, ?)";

			c = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, courseCode);
			pstmt.setString(2, courseName);
			pstmt.setString(3, newstring);
			pstmt.executeUpdate();
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
