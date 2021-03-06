package controller.word;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import entity.user.UserEntity;
import entity.word.WordEntity;
import service.user.UserService;
import service.word.WordService;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/add")
public class AddServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String callback = request.getParameter("callback");
		String enWord = request.getParameter("enWord");
		String koWord = request.getParameter("koWord");
		
		WordEntity entity = new WordEntity();
		response.setContentType("text/plain; charset=utf8");
		entity.setEgWord(enWord);
		entity.setKoWord(koWord);
		HttpSession session = request.getSession(true);
		String id = (String)session.getAttribute("id");
		entity.setId(id);
		Date dt = new Date();
		System.out.println(dt.toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss"); 
		entity.setWdate(sdf.format(dt).toString());
		WordService service = new WordService();
		boolean result = service.add(entity);
		System.out.println(result);
		JSONObject object = new JSONObject();
		object.put("rs", result);
		object.put("id", id);
		object.put("enWord", enWord);
		object.put("koWord",koWord );
		object.put("wordNum", entity.getWordNum());
		
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
