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
 * Servlet implementation class TinTucFormServlet
 */
@WebServlet(name = "TinTucFormServlet", urlPatterns = { "/TinTucForm", "/TinTucForm*" })
public class TinTucFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private EntityManagerFactoryUtil entityManagerFactory;
	private TinTucDAO tinTucDAO;
	private DanhMucDAO danhMucDAO;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TinTucFormServlet() {
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
		String action = request.getParameter("action") != null ? request.getParameter("action") : "";
		
		switch (action) {
			case "insert":
				showAddForm(request, response);
				break;
			default:
				listNews(request, response);
				break;
		}
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action = request.getParameter("action") != null ? request.getParameter("action") : "";
		
		switch (action) {
			case "insert":
				insertNews(request, response);
				break;
			default:
				listNews(request, response);
				break;
		}
	}
	
	private void showAddForm (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/TinTucForm.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void insertNews(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tieuDe = request.getParameter("tieuDe");
		String noiDung = request.getParameter("noiDung");
		String lienKet = request.getParameter("lienKet");
		int maDanhMuc = Integer.parseInt(request.getParameter("maDanhMuc"));
		
		DanhMuc danhMuc = this.danhMucDAO.timDanhMucTheoMa(maDanhMuc);
		
		TinTuc tinTuc = new TinTuc();
		tinTuc.setTieuDe(tieuDe);
		tinTuc.setNoiDung(noiDung);
		tinTuc.setLienKet(lienKet);
		tinTuc.setDanhMuc(danhMuc);
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<TinTuc>> violations = validator.validate(tinTuc);
		
		if (violations.isEmpty()) {
			this.tinTucDAO.themTinTuc(tinTuc);
			response.sendRedirect("DanhSachTinTuc");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("views/TinTucForm.jsp");
			
			StringBuilder stringBuilder = new StringBuilder();
			violations.forEach(violation -> {
				stringBuilder.append(violation.getPropertyPath() + ": " + violation.getMessage());
				stringBuilder.append("<br>");
			});
			
			request.setAttribute("tinTuc", tinTuc);
			request.setAttribute("errors", stringBuilder);
			dispatcher.forward(request, response);
		}
	}

	
	private void listNews (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<TinTuc> listNews = this.tinTucDAO.layDanhSachTinTuc();
		List<DanhMuc> listCategories = this.danhMucDAO.layDanhSachDanhMuc();
		
		System.out.println(listNews);
		System.out.println(listCategories);
		
		request.setAttribute("DanhSachTinTuc", listNews);
		request.setAttribute("listDanhMuc", listCategories);
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/TinTucForm.jsp");
		dispatcher.forward(request, response);
	}
}
