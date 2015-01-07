package hw5.servlet;

import hw5.model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NextQuarter")
public class NextQuarter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NextQuarter() {
		super();
	}

	@SuppressWarnings({ "unchecked" })
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<String> qtr = (List<String>) request.getSession().getAttribute(
				"nextqtr");
		List<getset> datalist = (List<getset>) request.getSession()
				.getAttribute("datalist");
		List<String> donesub = (List<String>) request.getSession()
				.getAttribute("donesub");

		List<String> showsubcode = new ArrayList<String>();
		List<String> showsubname = new ArrayList<String>();
		List<String> showsubpre = new ArrayList<String>();

		for (int z = 0; z < datalist.size(); z++) {

			if (!donesub.contains(datalist.get(z).getCourseCode())) {

				if (datalist.get(z).getPrereq().equals(" ")) {
					String code = datalist.get(z).getCourseCode();
					showsubcode.add(code);
					String name = datalist.get(z).getCourseName();
					showsubname.add(name);
					String prereq = datalist.get(z).getPrereq();
					showsubpre.add(prereq);

				} else {
					List<String> strprereq = new ArrayList<String>();

					for (String substring : datalist.get(z).getPrereq()
							.split(" ")) {
						strprereq.add(substring);
					}
					if (donesub.containsAll(strprereq)) {
						String code = datalist.get(z).getCourseCode();
						showsubcode.add(code);
						String name = datalist.get(z).getCourseName();
						showsubname.add(name);
						String prereq = datalist.get(z).getPrereq();
						showsubpre.add(prereq);

					}
				}
			}
		}

		// /////////////////////////////////////////////////////////////////////////////

		if (showsubcode.size() == 0) {
			response.sendRedirect("finish");
			return;
		}

		String showqtr = qtr.get(qtr.size() - 1);
		request.getSession().setAttribute("nextqtr", qtr);
		request.getSession().setAttribute("datalist", datalist);
		request.getSession().setAttribute("donesub", donesub);
		request.setAttribute("showsubcode", showsubcode);
		request.setAttribute("showsubname", showsubname);
		request.setAttribute("showsubpre", showsubpre);
		request.setAttribute("showqtr", showqtr);
		request.getRequestDispatcher("/WEB-INF/NextQuarter.jsp").forward(
				request, response);

	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<getset> datalist = (List<getset>) request.getSession()
				.getAttribute("datalist");
		List<String> nextqtr = (List<String>) request.getSession()
				.getAttribute("nextqtr");
		List<finishmvc> cmplt = (List<finishmvc>) request.getSession()
				.getAttribute("cmplt");
		List<String> qtrshow = (List<String>) request.getSession()
				.getAttribute("qtrshow");
		String n = request.getParameter("button");

		if (n.equals("Next")) {

			int week = (Integer) request.getSession().getAttribute("week");
			int year = (Integer) request.getSession().getAttribute("year");

			List<String> donesub = (List<String>) request.getSession()
					.getAttribute("donesub");

			/*
			 * for (int i = 0; i < datalist.size(); i++) {
			 * changeobj.add(datalist.get(i));
			 * 
			 * }
			 */
			String[] newsub = request.getParameterValues("prereq");

			if (newsub != null) {

				List<String> selectsub = new ArrayList<String>();
				String qtr = nextqtr.get(nextqtr.size() - 1);
				for (int f = 0; f < newsub.length; f++) {
					selectsub.add(newsub[f]);
					cmplt.add(new finishmvc(qtr, newsub[f]));
				}
				qtrshow.add(qtr);

				for (int b = 0; b < selectsub.size(); b++) {
					donesub.add(selectsub.get(b));
				}

			}
			// ///////////////////////
			week = week + 12;
			if (week > 52) {
				week = 6;
				year++;
			}
			Quarter q = new Quarter();
			nextqtr = q.getQuarter(week, year);

			request.getSession().setAttribute("qtrshow", qtrshow);
			request.getSession().setAttribute("donesub", donesub);
			request.getSession().setAttribute("nextqtr", nextqtr);
			request.getSession().setAttribute("cmplt", cmplt);
			request.getSession().setAttribute("year", year);
			request.getSession().setAttribute("week", week);
			request.getSession().setAttribute("datalist", datalist);

			response.sendRedirect("NextQuarter");
		} else if ("Finish".equals(n)) {
			
			int week = (Integer) request.getSession().getAttribute("week");
			int year = (Integer) request.getSession().getAttribute("year");

			List<String> donesub = (List<String>) request.getSession()
					.getAttribute("donesub");

			/*
			 * for (int i = 0; i < datalist.size(); i++) {
			 * changeobj.add(datalist.get(i));
			 * 
			 * }
			 */
			String[] newsub = request.getParameterValues("prereq");

			if (newsub != null) {

				List<String> selectsub = new ArrayList<String>();
				String qtr = nextqtr.get(nextqtr.size() - 1);
				for (int f = 0; f < newsub.length; f++) {
					selectsub.add(newsub[f]);
					cmplt.add(new finishmvc(qtr, newsub[f]));
				}
				qtrshow.add(qtr);

				for (int b = 0; b < selectsub.size(); b++) {
					donesub.add(selectsub.get(b));
				}

			}
			// ///////////////////////
			week = week + 12;
			if (week > 52) {
				week = 6;
				year++;
			}
			Quarter q = new Quarter();
			nextqtr = q.getQuarter(week, year);

			request.getSession().setAttribute("qtrshow", qtrshow);
			request.getSession().setAttribute("donesub", donesub);
			request.getSession().setAttribute("nextqtr", nextqtr);
			request.getSession().setAttribute("cmplt", cmplt);
			request.getSession().setAttribute("year", year);
			request.getSession().setAttribute("week", week);

			
			request.getSession().setAttribute("datalist", datalist);
			response.sendRedirect("finish");
		}
	}

}
