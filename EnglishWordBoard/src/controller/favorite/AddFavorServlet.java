package controller.favorite;

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
import entity.favor.FavorEntity;
import service.comment.CommentService;
import service.favor.FavorService;

/**
 * Servlet implementation class AddFavorServlet
 */
@WebServlet("/addFavor")
public class AddFavorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFavorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String callback = request.getParameter("callback");
		
		FavorEntity entity = new FavorEntity();
		int wordNum = Integer.parseInt((String)request.getParameter("wordNum"));
		entity.setWordNum(wordNum);
		HttpSession session = request.getSession(true);
		System.out.println((String)session.getAttribute("id"));
		entity.setId((String)session.getAttribute("id"));
		
		response.setContentType("text/plain; charset=utf8");
		
		
		FavorService service = new FavorService();
		boolean result = service.add(entity);

		JSONObject object = new JSONObject();
		object.put("rs", result);
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
