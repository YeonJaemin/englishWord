package controll.board;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.board.BoardEntity;
import service.board.BoardService;

/**
 * Servlet implementation class WriteServlet
 */
@WebServlet("/write")
public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteServlet() {
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
		request.setCharacterEncoding("UTF8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		HttpSession session =  request.getSession(true);
		BoardEntity entity = new BoardEntity();
		entity.setUid((String)(session.getAttribute("id")));
		entity.setTitle(title);
		entity.setContent(content);
		entity.setDate((new Date()).toLocaleString());
		BoardService service = new BoardService();
		boolean result =  service.write(entity);
		RequestDispatcher rd = request.getRequestDispatcher("writeOK.jsp");
		if(result){
			request.setAttribute("msg","정상적이게 작성되었습니다." );
			request.setAttribute("flag", result);
		}
		else{
			request.setAttribute("msg","작성에 실패하였습니다." );
			request.setAttribute("flag", result);
		}
		rd.forward(request, response);
	}

}
