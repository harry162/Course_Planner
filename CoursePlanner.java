package hw5.servlet;

import hw5.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CoursePlanner")
public class CoursePlanner extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CoursePlanner() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		
		
		
		List<getset> datalist = (List<getset>) request.getSession()
				.getAttribute("datalist");

		request.setAttribute("datalist", datalist);
		request.getRequestDispatcher("/WEB-INF/CoursePlanner.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		Calendar c = Calendar.getInstance();
		int week = c.get(Calendar.WEEK_OF_YEAR);
		int year = c.get(Calendar.YEAR);
		Quarter q = new Quarter();

		List<String> qtrshow = new ArrayList<String>();
		List<String> nextqtr = new ArrayList<String>();
		nextqtr = q.getQuarter(week, year);

		request.getSession().setAttribute("year", year);
		request.getSession().setAttribute("week", week);

		/*
		 * select arrayydatalistfor datalist22 if ree"""&&& selev 2 12 202
		 */

		List<String> donesub = new ArrayList<String>();
		List<finishmvc> cmplt = new ArrayList<finishmvc>();
		List<String> selectsub = new ArrayList<String>();

		String[] newsub = request.getParameterValues("prereq");

		if (newsub != null) {
			for (int f = 0; f < newsub.length; f++) {
				selectsub.add(newsub[f]);
				cmplt.add(new finishmvc("Subjects already taken",newsub[f]));
			}
			qtrshow.add("Subjects already taken");

			for (int b = 0; b < selectsub.size(); b++) {
				donesub.add(selectsub.get(b));

			}

		}
		request.getSession().setAttribute("qtrshow", qtrshow);
		request.getSession().setAttribute("cmplt", cmplt);
		request.getSession().setAttribute("donesub", donesub);
		request.getSession().setAttribute("nextqtr", nextqtr);

		response.sendRedirect("NextQuarter");
	}

}
