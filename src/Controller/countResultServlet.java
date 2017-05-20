package Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

import Bean.commentBean;
import Bean.resultBean;
import DAO.commentDAO;
import DAO.resultDAO;

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
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	    
	    HttpSession session = request.getSession(true);	
        session.setAttribute("login", "software_engineer");

		resultBean result = new resultBean();
		ResultSet rs = null;
		rs = resultDAO.selectLastID();
		
		String packageName = null, time = null, AHFData=null, HCData=null, CohNever=null, CohSeldom=null,CoupAll=null, 
				CoupHigh=null, CompMethodHigh=null, CompClassHigh=null, ReuseLow=null,
				complexityComment, cohesionComment, couplingComment, securityAHFComment, securityHCComment, id = null;
		int classNum = 0;
		double simplicity = 0, reusability = 0, cohesion = 0, coupling = 0, AHF = 0, HC = 0, security = 0;
		try {
//			boolean more = rs.next();
			if (rs.next()){
			packageName = rs.getString("PackageName");
			classNum = rs.getInt("ClassNum");
			simplicity = rs.getDouble("Simplicity");
			reusability = rs.getDouble("Reusability");
			cohesion = rs.getDouble("Cohesion");
			coupling = rs.getDouble("Coupling");
			AHF = rs.getDouble("AHF");
			HC = rs.getDouble("HC");
			security = rs.getDouble("Security");
			time = rs.getString("Time");
			id = rs.getString("ID");
			AHFData = rs.getString("AHFComment");
			HCData = rs.getString("HCComment");
			CohNever = rs.getString("Coh_never");
			CohSeldom = rs.getString("Coh_seldom");
			CoupAll = rs.getString("Coup_all");
			CoupHigh = rs.getString("Coup_high");
			CompMethodHigh = rs.getString("Comp_metHigh");
			CompClassHigh = rs.getString("Comp_classHigh");
			ReuseLow = rs.getString("Reuse_low");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		commentBean comment = new commentBean();
		comment = commentDAO.selectlastData(comment);
		comment = commentDAO.getComplexityComment(comment);
		comment = commentDAO.getCohesionComment(comment);
		comment = commentDAO.getCouplingComment(comment);
		comment = commentDAO.getSecurityComment(comment);
		
		
		complexityComment = comment.getComplexityComment();
		cohesionComment = comment.getCohesionComment();
		couplingComment = comment.getCouplingComment();
		securityAHFComment = comment.getAHFComment();
		securityHCComment = comment.getHCComment();
  	    
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
	    request.setAttribute("AHFData", AHFData);
	    request.setAttribute("HCData", HCData);
	    request.setAttribute("CohNever", CohNever);
	    request.setAttribute("CohSeldom", CohSeldom);
	    request.setAttribute("CoupAll", CoupAll);
	    request.setAttribute("CoupHigh", CoupHigh);
	    request.setAttribute("CompMethodHigh", CompMethodHigh);
	    request.setAttribute("CompClassHigh", CompClassHigh);
	    request.setAttribute("ReuseLow", ReuseLow);
	    request.setAttribute("id", id);
	    request.setAttribute("complexityComment", complexityComment);
	    request.setAttribute("cohesionComment", cohesionComment);
	    request.setAttribute("couplingComment", couplingComment);
	    request.setAttribute("securityAHFComment", securityAHFComment);
	    request.setAttribute("securityHCComment", securityHCComment);
	  	RequestDispatcher view = request.getRequestDispatcher("/showResult.jsp");
		view.forward(request, response); 				

	}
}
