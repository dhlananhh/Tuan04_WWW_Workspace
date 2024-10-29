package controllers;



import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.io.IOException;

import dao.UserDAO;
import daoImpl.UserDAOImpl;
import models.User;
import utils.EntityManagerFactoryUtil;


/**
 * Servlet implementation class UserController
 */
@WebServlet(name = "UserController", urlPatterns = { "/", "/users", "/users*"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private EntityManagerFactoryUtil entityManagerFactory;
	private UserDAO userDAO;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
    }

    
    /**
     * @see HttpServlet#init(ServletConfig config)
     * This method is called by the servlet container to perform initialization of the servlet.
     */
	@Override
	public void init (ServletConfig config) throws ServletException {
		super.init(config);
		this.entityManagerFactory = new EntityManagerFactoryUtil();
		this.userDAO = new UserDAOImpl(this.entityManagerFactory.getEntityManager());
	}

	
	/**
	 * @see HttpServlet#destroy()
	 * This method is called by the servlet container to perform the destruction of the servlet.
	 */
	@Override
	public void destroy() {
		this.entityManagerFactory.close();
		super.destroy();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * This method is used to do the action: add a new user, edit an existing user, delete a user, list all users.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action") != null ? request.getParameter("action") : "";
		System.out.println("Action: " + action);
		
		switch (action) {
			case "new":
				showRegisterForm(request, response);
				break;
			case "delete":
				deleteUser(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			default:
				listUsers(request, response);
				break;
		}
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action") != null ? request.getParameter("action") : "";
		
		switch (action) {
			case "insert":
				insertUser(request, response);
				break;
			case "update":
				updateUser(request, response);
				break;
			default:
				listUsers(request, response);
				break;
		}
	}

	
	/*
	 * List all users
	 */
	private void listUsers (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		List<User> users = userDAO.getAllUsers();
		System.out.println(users);
		
		request.setAttribute("listUsers", users);
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/user/list.jsp");
		dispatcher.forward(request, response);
	}
	
	
	/**
	 * Show the register form
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showRegisterForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/user/add.jsp");
		dispatcher.forward(request, response);
	}
	
	
	/**
	 * Show the edit form
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = this.userDAO.getUserById(id);

		request.setAttribute("user", existingUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/user/edit.jsp");
		dispatcher.forward(request, response);
	}
	
	
	/**
	 * Insert a new user
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void insertUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String job = request.getParameter("job");
		String birthDate = request.getParameter("birthDate");
		String gender = request.getParameter("gender");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		User newUser = new User(fullName, email, job, birthDate, gender, city, country);
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<User>> violations = validator.validate(newUser);
		
		if (violations.isEmpty()) {
			this.userDAO.insertUser(newUser);
			response.sendRedirect("users");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("views/user/add.jsp");
			
			StringBuilder stringBuilder = new StringBuilder();
			violations.forEach(violation -> {
				stringBuilder.append(violation.getPropertyPath() + ": " + violation.getMessage());
				stringBuilder.append("<br>");
			});
			
			request.setAttribute("user", newUser);
			request.setAttribute("errors", stringBuilder);
			dispatcher.forward(request, response);
		}
	}
	
	
	/**
	 * Update an existing user
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateUser (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String job = request.getParameter("job");
		String birthDate = request.getParameter("birthDate");
		String gender = request.getParameter("gender");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		User user = new User(id, fullName, email, job, birthDate, gender, city, country);
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        
		if (violations.isEmpty()) {
			this.userDAO.updateUser(user);
			response.sendRedirect("users");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("views/user/edit.jsp");

			StringBuilder stringBuilder = new StringBuilder();
			violations.forEach(violation -> {
				stringBuilder.append(violation.getPropertyPath() + ": " + violation.getMessage());
				stringBuilder.append("<br>");
			});

			request.setAttribute("user", user);
			request.setAttribute("errors", stringBuilder);
			dispatcher.forward(request, response);
		}
	}
	
	
	/**
	 * Delete a user
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		this.userDAO.deleteUser(id);
		response.sendRedirect("users");
	}
}
