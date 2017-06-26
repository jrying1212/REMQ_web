package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * Servlet implementation class drawServlet
 */
@WebServlet("/drawServlet")
public class drawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public drawServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		response.setContentType("text/html");  
        DefaultCategoryDataset chartDate = new DefaultCategoryDataset();          
        String group1 = packageName;
        
        chartDate.addValue(simplicity, group1, "Simplicity(Complexity)");
        chartDate.addValue(reusability, group1, "Reusability(Complexity)");
        chartDate.addValue(cohesion, group1, "Cohesion");
        chartDate.addValue(coupling, group1, "Coupling");
        chartDate.addValue(AHF, group1, "Encapsulation(Security)");
        chartDate.addValue(HC, group1, "Hard-coded(Security)");
        DefaultCategoryDataset data = chartDate; 
        SpiderWebPlot spiderwebplot = new SpiderWebPlot(data);
        JFreeChart jfreechart = new JFreeChart("Result", TextTitle.DEFAULT_FONT,spiderwebplot, false);
        LegendTitle legendtitle = new LegendTitle(spiderwebplot);
        legendtitle.setPosition(RectangleEdge.BOTTOM);
        jfreechart.addSubtitle(legendtitle);
        ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f, jfreechart, 700, 420, null);
  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
