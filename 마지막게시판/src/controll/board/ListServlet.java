package controll.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.board.BoardEntity;
import service.board.BoardService;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet("/list")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = (new Integer((String)request.getParameter("page"))).intValue();	
		ArrayList<BoardEntity> list = new ArrayList<BoardEntity>();
		BoardService service = new BoardService();
		BoardEntity start = new BoardEntity();
		
		start.setBoardNum((page-1)*10);
		list = service.searchPage(start);
		String pageNum = service.getPageNum() / 10 + 1 + "";
		
		RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
		request.setAttribute("list", list);
		request.setAttribute("pageNum", pageNum);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
