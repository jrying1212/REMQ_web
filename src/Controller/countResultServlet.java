package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.cohesion;
import Model.complexity;
import Model.coupling;
import Model.security;
import Model.showInfo;

/**
 * Servlet implementation class countResultServlet
 */
@WebServlet("/countResultServlet")
public class countResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public countResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		showInfo info = new showInfo();
		complexity comp = new complexity();
		security se = new security();
		coupling cp = new coupling();
		cohesion ch = new cohesion();
		request.setAttribute("basic", info);
		request.setAttribute("complexity", comp);
		request.setAttribute("security", se);
		request.setAttribute("coupling", cp);
		request.setAttribute("cohesion", ch);		
		RequestDispatcher view = request.getRequestDispatcher("/resultPage.jsp");
	    view.forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
		showInfo info = new showInfo();
		complexity comp = new complexity();
		security se = new security();
		coupling cp = new coupling();
		cohesion ch = new cohesion();
		request.setAttribute("basic", info);
		request.setAttribute("complexity", comp);
		request.setAttribute("security", se);
		request.setAttribute("coupling", cp);
		request.setAttribute("cohesion", ch);		
		RequestDispatcher view = request.getRequestDispatcher("/resultPage.jsp");
	    view.forward(request, response); 
	}

}
