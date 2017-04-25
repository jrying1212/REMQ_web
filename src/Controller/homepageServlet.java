package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.coupling;
import Model.security;
import Model.showInfo;

/**
 * Servlet implementation class homepageServlet
 */
@WebServlet("/homepageServlet")
public class homepageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public homepageServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		showInfo info = new showInfo();
		security se = new security();
		coupling cp =new coupling();
		request.setAttribute("basic", info);
		request.setAttribute("security", se);
		request.setAttribute("coupling", cp);
		RequestDispatcher view = request.getRequestDispatcher("/test.jsp");
	    view.forward(request, response); 
	    	   
	}	
}
