package controller.favorite;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import entity.favor.FavorEntity;
import service.favor.FavorService;

/**
 * Servlet implementation class DeleteFavorServlet
 */
@WebServlet("/deleteFavor")
public class DeleteFavorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFavorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String callback = request.getParameter("callback");
		int num = Integer.parseInt(request.getParameter("wordNum"));
		FavorEntity entity = new FavorEntity();
		HttpSession session = request.getSession(true);
		String id = (String)session.getAttribute("id");
		entity.setId(id);
		entity.setWordNum(num);
		FavorService service = new FavorService();	
		boolean result = service.delete(entity);
		JSONObject object = new JSONObject();
		object.put("rs", result);
		String json = object.toJSONString();
		response.setContentType("text/plain; charset=utf8");
		PrintWriter out = response.getWriter();
		out.println(callback+"("+json+")");
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
