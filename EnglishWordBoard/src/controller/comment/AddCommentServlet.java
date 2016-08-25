package controller.comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import entity.comment.CommentEntity;
import service.comment.CommentService;

/**
 * Servlet implementation class AddCommentServlet
 */
@WebServlet("/addComment")
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String callback = request.getParameter("callback");
		String content = request.getParameter("content");
		response.setContentType("text/plain; charset=utf8");
		int wordNum = Integer.parseInt((String)request.getParameter("wordNum"));
		CommentEntity entity = new CommentEntity();
		HttpSession session = request.getSession(true);
		System.out.println(content);
		entity.setComment(content);
		entity.setWordNum(wordNum);
		entity.setId((String)session.getAttribute("id"));
		Date dt = new Date();
		System.out.println(dt.toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss"); 
		entity.setCdate(sdf.format(dt).toString());
		
		CommentService service = new CommentService();
		service.add(entity);
		
		
		JSONObject object = new JSONObject();
		object.put("id", (String)session.getAttribute("id"));
		String json = object.toJSONString();
		
		PrintWriter writer = response.getWriter();
		writer.println(callback+"("+json+")");
		writer.flush();
		writer.close();
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
