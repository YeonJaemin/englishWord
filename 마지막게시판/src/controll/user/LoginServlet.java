package controll.user;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.user.UserEntity;
import service.user.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		//한글
		request.setCharacterEncoding("UTF8");
		//입력
		boolean result = false;
		String id = request.getParameter("inputId");
		String pw = request.getParameter("inputPassword");
		UserEntity entity = new UserEntity();
		entity.setId(id);
		entity.setPw(pw);
		UserService service = new UserService();
		result = service.login(entity);
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		request.setAttribute("flag", result);
		if(result){
			HttpSession session = request.getSession(true);
			session.setAttribute("id", id);
			request.setAttribute("msg", id+"님 환영합니다.");
		}
		else{
			request.setAttribute("msg", "없는 비밀번호입니다.");
		}
		rd.forward(request, response);
	}

}
