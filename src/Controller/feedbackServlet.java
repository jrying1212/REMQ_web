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
		request.setCharacterEncoding("UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String id = request.getParameter("proj_id");
		String item = request.getParameter("item");
		System.out.println("item "+item);
		response.getWriter().append("Served at: ").append(id);
		request.setCharacterEncoding("UTF-8"); 
		String content = request.getParameter("feedback");
		feedbackBean feedback = new feedbackBean();
		System.out.println("feedback "+content);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String time = dateFormat.format(date);
		
		feedback.setProjID(id);
		feedback.setContent(content);	
		feedback.setTime(time);
		feedback.setSelect(item);
		feedback = feedbackDAO.insertData(feedback);
		
		String packageName = null, c_time = null, complexityComment, cohesionComment, couplingComment, securityAHFComment, securityHCComment;
		int classNum = 0;
		double simplicity = 0, reusability = 0, cohesion = 0, coupling = 0, AHF = 0, HC = 0, security = 0;
		
		resultBean result = new resultBean();
		result.setID(id);
		result = resultDAO.selectData(result);
		packageName = result.getPackageName();
		classNum = result.getClassNum();
		simplicity = result.getSimplicity();
		reusability = result.getResuability();
		coupling = result.getCoupling();
		cohesion = result.getCohesion();
		security = result.getSecurity();
		AHF = result.getAHF();
		HC = result.getHC();
		c_time = result.getTime();
		
		commentBean comment = new commentBean();
		comment.setID(id);
		comment = commentDAO.selectData(comment);
		comment = commentDAO.getComplexityComment(comment);
		comment = commentDAO.getCohesionComment(comment);
		comment = commentDAO.getCouplingComment(comment);
		comment = commentDAO.getSecurityComment(comment);

		complexityComment = comment.getComplexityComment();
		cohesionComment = comment.getCohesionComment();
		couplingComment = comment.getCouplingComment();
		securityAHFComment = comment.getAHFComment();
		securityHCComment = comment.getHCComment();
       
	    HttpSession session = request.getSession(true);	    
	    request.setCharacterEncoding("UTF-8");
	    request.setAttribute("packageName", packageName);
	    request.setAttribute("classNum", classNum);
	    request.setAttribute("simplicity", simplicity);
	    request.setAttribute("reusability", reusability);
	    request.setAttribute("cohesion", cohesion);
	    request.setAttribute("coupling", coupling);
	    request.setAttribute("AHF", AHF);
	    request.setAttribute("HC", HC);
	    request.setAttribute("security", security);
	    request.setAttribute("time", c_time);
	    request.setAttribute("id", id);
	    request.setAttribute("complexityComment", complexityComment);
	    request.setAttribute("cohesionComment", cohesionComment);
	    request.setAttribute("couplingComment", couplingComment);
	    request.setAttribute("securityAHFComment", securityAHFComment);
	    request.setAttribute("securityHCComment", securityHCComment);
	  	RequestDispatcher view = request.getRequestDispatcher("/showAllResult.jsp");
		view.forward(request, response); 
				
	}
}
