package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.userBean;
import DAO.userDAO;

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
		try
		{	    

		     userBean user = new userBean();
		     user.setAccount(request.getParameter("username"));
		     user.setPassword(request.getParameter("password"));

		     user = userDAO.login(user);
			   		    
		     if (user.isValid())
		     {
			        
		          HttpSession session = request.getSession(true);	    
		          session.setAttribute("currentSessionUser",user); 
		          request.setAttribute("userInfo", user);
//		          response.sendRedirect("showAllResult.jsp"); //logged-in page
		  		RequestDispatcher view = request.getRequestDispatcher("/showAllResult.jsp");
			    view.forward(request, response); 
		     }
			        
		     else 
		          response.sendRedirect("homePage.jsp"); //error page 
		} 
				
				
		catch (Throwable theException) 	    
		{
		     System.out.println(theException); 
		}
		
	    	   
	}	
}
