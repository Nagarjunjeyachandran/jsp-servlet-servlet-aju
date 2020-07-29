package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javaguides.usermanagement.dao.UserDao;
import net.javaguides.usermanagement.model.User;

/**
 * Servlet implementation class Userservlet
 */
@WebServlet("/")
public class Userservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private UserDao userDao;
    
    public Userservlet(){
    	this.userDao=new UserDao();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String action=request.getServletPath();
		
		switch(action){
		  case "/new":try {
							showNewForm(request,response);
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
			          break;
		  case "/insert":try {
								addNewUser(request,response);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		                 break;
			case "/update":try {
								updateUser(request,response);
							} catch (SQLException e3) {
								// TODO Auto-generated catch block
								e3.printStackTrace();
							}
			  		break;
		  case "/delete":try {
							deleteUser(request,response);
						} catch (SQLException e4) {
							// TODO Auto-generated catch block
							e4.printStackTrace();
						}
		                 break;
		  case "/edit":try {
							showEditForm(request,response);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		               break;
		  default:try {
				listUser(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}                         
			      break;
				   
		}
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,SQLException {
		// TODO Auto-generated method stub
		RequestDispatcher rd=request.getRequestDispatcher("/userform.jsp");
		rd.forward(request, response);
	}
	
	private void addNewUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException,SQLException{
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String country=request.getParameter("country");
		User newuser=new User(name,email,country);
		userDao.createUser(newuser);
		response.sendRedirect("list");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException,SQLException {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(request.getParameter("id"));
		User existingUser=userDao.selectUser(id);
		RequestDispatcher rd=request.getRequestDispatcher("/userform.jsp");
		request.setAttribute("user",existingUser);
		rd.forward(request, response);
		
	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException,SQLException{
		// TODO Auto-generated method stub
		int id= Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String country=request.getParameter("country");
		User user=new User(id,name,email,country);
		userDao.updateUser(user);
		response.sendRedirect("list");
		
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException,SQLException{
		// TODO Auto-generated method stub
		int id=Integer.parseInt(request.getParameter("id"));
		userDao.deleteUser(id);
		response.sendRedirect("list");	
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,SQLException{
		// TODO Auto-generated method stub
		List<User> listusers=userDao.selectAllUsers();
		request.setAttribute("listUser", listusers);
		RequestDispatcher rd=request.getRequestDispatcher("/userlist.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
