package hw5.servlet;

import hw5.model.getset;
import hw5.model.finishmvc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FDisplay")
public class FDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FDisplay() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<finishmvc> finallist = new ArrayList<finishmvc>();

		String user = request.getParameter("user");
		String date = request.getParameter("date");

		Connection c = null;
		try {
			String url = "jdbc:mysql://localhost/cs320stu57";
			String username = "cs320stu57";
			String password = "9kjC9!.l";

			String sql = "select * from lastTable where username = ? and saved_on = ?";

			c = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.setString(2, date);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				finishmvc entry = new finishmvc(rs.getString("username"),
						rs.getString("saved_on"), rs.getString("quarter"),
						rs.getString("courseCode"), rs.getString("courseName"),
						rs.getString("prereq"));
				finallist.add(entry);
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

		List<String> qlist = new ArrayList<String>();
		
		for(int i= 0; i<finallist.size();i++){
			if(!qlist.contains(finallist.get(i).getQuarter())){
				qlist.add(finallist.get(i).getQuarter());
			}
		}
		
		for(int i= 0; i<qlist.size();i++){
		System.out.println(qlist.get(i));
		}
		
		request.setAttribute("user", user);
		request.setAttribute("date", date);
		request.setAttribute("qlist", qlist);
		request.setAttribute("finallist", finallist);
		request.getSession().setAttribute("finallist", finallist);
		request.getRequestDispatcher("/WEB-INF/FDisplay.jsp").forward(
				request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
