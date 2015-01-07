package hw5.servlet;

import hw5.model.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/finish")
public class finish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public finish() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<finishmvc> cmplt = (List<finishmvc>) request.getSession()
				.getAttribute("cmplt");

		List<getset> datalist = (List<getset>) request.getSession()
				.getAttribute("datalist");

		List<String> qtrshow = (List<String>) request.getSession()
				.getAttribute("qtrshow");
		
		
		List<finishmvc> last = new ArrayList<finishmvc>();

		List<String> nextqtr = (List<String>) request.getSession().getAttribute(
				"nextqtr");
		
		if (request.getSession().getAttribute("user") != null) {
			String user = (String) request.getSession().getAttribute("user");
			request.setAttribute("user", user);

		}
		
		/*
		 * for (int i = 0; i < cmplt.size(); i++) {
		 * System.out.println(cmplt.get(i).getQuarter());
		 * System.out.println(cmplt.get(i).getCode()); }
		 */

		for (int s = 0; s < cmplt.size(); s++) {
			for (int t = 0; t < datalist.size(); t++) {
				if (cmplt.get(s).getCode()
						.equals(datalist.get(t).getCourseCode())) {
					last.add(new finishmvc(cmplt.get(s).getQuarter(), datalist
							.get(t).getCourseCode(), datalist.get(t)
							.getCourseName(), datalist.get(t).getPrereq()));
				}
			}
		}
		request.getSession().setAttribute("last", last);
		request.setAttribute("qtrshow", qtrshow);
		request.setAttribute("last", last);
		request.setAttribute("nextqtr", nextqtr);

		request.getRequestDispatcher("/WEB-INF/Finish.jsp").forward(request,
				response);

	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<finishmvc> lastlist = new ArrayList<finishmvc>();

		String user = (String) request.getSession().getAttribute("user");
		
		String timeStamp = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(Calendar.getInstance().getTime());
		
		List<finishmvc> last = (List<finishmvc>) request.getSession()
				.getAttribute("last");
	
		Connection c = null;
		try {
			String url = "jdbc:mysql://localhost/cs320stu57";
			String username = "cs320stu57";
			String password = "9kjC9!.l";

			String sql = "insert into lastTable (username, saved_on, quarter, courseCode, courseName, prereq) values ( ?, ?, ?, ?, ?, ?)";

			c = DriverManager.getConnection(url, username, password);
		for(int a = 0; a<last.size();a++){
			
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.setString(2, timeStamp);
			pstmt.setString(3, last.get(a).getQuarter());
			pstmt.setString(4, last.get(a).getCode());
			pstmt.setString(5, last.get(a).getName());
			pstmt.setString(6, last.get(a).getPrereq());
			pstmt.executeUpdate();
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

		
response.sendRedirect("DisplayCourse");	
	}

}
