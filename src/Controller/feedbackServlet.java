package Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.commentBean;
import Bean.feedbackBean;
import Bean.resultBean;
import DAO.commentDAO;
import DAO.feedbackDAO;
import DAO.resultDAO;

/**
 * Servlet implementation class feedbackServlet
 */
@WebServlet("/feedbackServlet")
public class feedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public feedbackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		request.setAttribute("id", id);
//	  	RequestDispatcher view = request.getRequestDispatcher("/homePage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String id = request.getParameter("proj_id");
		response.getWriter().append("Served at: ").append(id);
		String content = request.getParameter("feedback");
		feedbackBean feedback = new feedbackBean();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String time = dateFormat.format(date);
		
		feedback.setProjID(id);
		feedback.setContent(content);	
		feedback.setTime(time);
		feedback = feedbackDAO.insertData(feedback);
		
				
		resultBean result = new resultBean();
		result.setID(id);
		result = resultDAO.selectData(result);
		
		commentBean comment = new commentBean();
		comment.setID(id);
		comment = commentDAO.selectData(comment);
		comment = commentDAO.getComplexityComment(comment);
		comment = commentDAO.getCohesionComment(comment);
		comment = commentDAO.getCouplingComment(comment);
		comment = commentDAO.getSecurityComment(comment);

		        
	    HttpSession session = request.getSession(true);	    
	    
	    request.setAttribute("result", result);
	    request.setAttribute("comment", comment);
	  	RequestDispatcher view = request.getRequestDispatcher("/showResult.jsp");
		view.forward(request, response); 
		
		
	}

}
