package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.commentBean;
import Bean.resultBean;
import DAO.commentDAO;
import DAO.resultDAO;

/**
 * Servlet implementation class updateRuleServlet
 */
@WebServlet("/updateRuleServlet")
public class updateRuleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateRuleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		int type =Integer.valueOf(request.getParameter("type"));
		System.out.println("Save "+type);
		
//		commentBean comment = new commentBean();
//		comment.setID(id);
//		comment = commentDAO.UpdateComplexity(bean);
		request.setCharacterEncoding("UTF-8");
		if (type==1){
			request.setCharacterEncoding("UTF-8");
			String[] sim_rate_from = request.getParameterValues("sim_rate_from");
			String[] sim_rate_to = request.getParameterValues("sim_rate_to");
			String[] reu_rate_from = request.getParameterValues("reu_rate_from");
			String[] reu_rate_to = request.getParameterValues("reu_rate_to");
			String[] c_comment = request.getParameterValues("comment");
			for (int i=0;i<sim_rate_from.length;i++){
				System.out.println(sim_rate_from[i]);
				commentBean comment = new commentBean();
				comment.setComplexityID(i+1);
				comment.setSimp_start(Double.valueOf(sim_rate_from[i]));
				comment.setSimp_end(Double.valueOf(sim_rate_to[i]));
				comment.setReu_start(Double.valueOf(reu_rate_from[i]));
				comment.setReu_end(Double.valueOf(reu_rate_to[i]));
				comment.setComplexityComment(c_comment[i]);
				comment = commentDAO.UpdateComplexity(comment);
			}			
		}
		
		else if (type==2){
			String[] coup_start = request.getParameterValues("coup_start");
			String[] coup_end = request.getParameterValues("coup_end");
			String[] c_comment = request.getParameterValues("rule");
			for (int i=0;i<coup_start.length;i++){
				System.out.println(coup_start[i]);
				commentBean comment = new commentBean();
				comment.setComplexityID(i+1);
				comment.setSimp_start(Double.valueOf(coup_start[i]));
				comment.setSimp_end(Double.valueOf(coup_end[i]));
				comment.setComplexityComment(c_comment[i]);
				comment = commentDAO.UpdateComplexity(comment);
			}	
		}
		else if (type==3){
			String[] sim_rate_from = request.getParameterValues("sim_rate_from");
			String[] sim_rate_to = request.getParameterValues("sim_rate_to");
			String[] reu_rate_from = request.getParameterValues("reu_rate_from");
			String[] reu_rate_to = request.getParameterValues("reu_rate_to");
			String[] c_comment = request.getParameterValues("comment");
			for (int i=0;i<sim_rate_from.length;i++){
				System.out.println(sim_rate_from[i]);
				commentBean comment = new commentBean();
				comment.setComplexityID(i+1);
				comment.setSimp_start(Double.valueOf(sim_rate_from[i]));
				comment.setSimp_end(Double.valueOf(sim_rate_to[i]));
				comment.setReu_start(Double.valueOf(reu_rate_from[i]));
				comment.setReu_end(Double.valueOf(reu_rate_to[i]));
				comment.setComplexityComment(c_comment[i]);
				comment = commentDAO.UpdateComplexity(comment);
			}	
		}
		else{
			String[] sim_rate_from = request.getParameterValues("sim_rate_from");
			String[] sim_rate_to = request.getParameterValues("sim_rate_to");
			String[] reu_rate_from = request.getParameterValues("reu_rate_from");
			String[] reu_rate_to = request.getParameterValues("reu_rate_to");
			String[] c_comment = request.getParameterValues("comment");
			for (int i=0;i<sim_rate_from.length;i++){
				System.out.println(sim_rate_from[i]);
				commentBean comment = new commentBean();
				comment.setComplexityID(i+1);
				comment.setSimp_start(Double.valueOf(sim_rate_from[i]));
				comment.setSimp_end(Double.valueOf(sim_rate_to[i]));
				comment.setReu_start(Double.valueOf(reu_rate_from[i]));
				comment.setReu_end(Double.valueOf(reu_rate_to[i]));
				comment.setComplexityComment(c_comment[i]);
				comment = commentDAO.UpdateComplexity(comment);
			}	
		}
		
		 response.sendRedirect("showRule.jsp");
	}

}
