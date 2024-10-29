package controllers;


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

import java.io.IOException;
import java.util.List;
import java.util.Set;

import utils.EntityManagerFactoryUtil;

import dao.DanhMucDAO;
import dao.TinTucDAO;

import daoImpl.DanhMucDAOImpl;
import daoImpl.TinTucDAOImpl;

import models.DanhMuc;
import models.TinTuc;


/**
 * Servlet implementation class QuanLyFormServlet
 */
@WebServlet(name = "QuanLyFormServlet", urlPatterns = { "/QuanLyForm", "/QuanLyForm*" })
public class QuanLyFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private EntityManagerFactoryUtil entityManagerFactory;
	private TinTucDAO tinTucDAO;
	private DanhMucDAO danhMucDAO;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuanLyFormServlet() {
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
		this.tinTucDAO = new TinTucDAOImpl(this.entityManagerFactory.getEntityManager());
		this.danhMucDAO = new DanhMucDAOImpl(this.entityManagerFactory.getEntityManager());
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
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
        if (action != null && action.equals("delete")) {
            deleteNews(request, response);
        } else {
            listNews(request, response);
        }
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	

	private void showDeleteForm (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/QuanLyForm.jsp");
		dispatcher.forward(request, response);
	}


	/**
	 * Delete news
	 */
	private void listNews (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<TinTuc> listNews = this.tinTucDAO.layDanhSachTinTuc();
		List<DanhMuc> listCategories = this.danhMucDAO.layDanhSachDanhMuc();
		
		System.out.println(listNews);
		System.out.println(listCategories);
		
		request.setAttribute("DanhSachTinTuc", listNews);
		request.setAttribute("listDanhMuc", listCategories);
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/QuanLyForm.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void deleteNews(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		this.tinTucDAO.xoaTinTuc(id);
		response.sendRedirect("DanhSachTinTuc");
	}
}
