package controller.word;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import entity.word.WordEntity;
import service.word.WordService;

/**
 * Servlet implementation class DeatilServlet
 */
@WebServlet("/detail")
public class DeatilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeatilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String callback = request.getParameter("callback");
		int wordNum = Integer.parseInt((String)request.getParameter("search"));
		System.out.println("상세 글번호: "+wordNum);
		WordEntity entity = new WordEntity();
		entity.setWordNum(wordNum);
		response.setContentType("text/plain; charset=utf8");
		WordService service = new WordService();
		JSONObject result = service.detail(entity);
		String json = result.toJSONString();
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
