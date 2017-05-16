package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.commentBean;
import Bean.resultBean;
import DAO.commentDAO;
import DAO.resultDAO;
@WebServlet("/getResultServlet")
public class getResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("param1");
		
		String packageName = null, time = null, complexityComment, cohesionComment, couplingComment, securityAHFComment, securityHCComment;
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
		time = result.getTime();		
		
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
	    
	    request.setAttribute("packageName", packageName);
	    request.setAttribute("classNum", classNum);
	    request.setAttribute("simplicity", simplicity);
	    request.setAttribute("reusability", reusability);
	    request.setAttribute("cohesion", cohesion);
	    request.setAttribute("coupling", coupling);
	    request.setAttribute("AHF", AHF);
	    request.setAttribute("HC", HC);
	    request.setAttribute("security", security);
	    request.setAttribute("time", time);
	    request.setAttribute("id", id);
	    request.setAttribute("complexityComment", complexityComment);
	    request.setAttribute("cohesionComment", cohesionComment);
	    request.setAttribute("couplingComment", couplingComment);
	    request.setAttribute("securityAHFComment", securityAHFComment);
	    request.setAttribute("securityHCComment", securityHCComment);
	  	RequestDispatcher view = request.getRequestDispatcher("/showResult.jsp");
		view.forward(request, response); 		       					
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
