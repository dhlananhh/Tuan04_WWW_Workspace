package controllers;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DanhMuc;
import models.TinTuc;
import utils.EntityManagerFactoryUtil;

import java.io.IOException;
import java.util.List;

import dao.DanhMucDAO;
import dao.TinTucDAO;
import daoImpl.DanhMucDAOImpl;
import daoImpl.TinTucDAOImpl;


/**
 * Servlet implementation class DanhSachTinTucServlet
 */
@WebServlet(name = "DanhSachTinTucServlet", urlPatterns = { "/DanhSachTinTuc", "/DanhSachTinTuc*" })
public class DanhSachTinTucServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private EntityManagerFactoryUtil entityManagerFactory;
	private TinTucDAO tinTucDAO;
	private DanhMucDAO danhMucDAO;
	
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DanhSachTinTucServlet() {
        super();
    }


	/**
     * @see HttpServlet#init(ServletConfig)
	 * This method is called by the servlet container to perform initialization of the servlet.
     */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.entityManagerFactory = new EntityManagerFactoryUtil();
		this.tinTucDAO = new TinTucDAOImpl(this.entityManagerFactory.getEntityManager());
		this.danhMucDAO = new DanhMucDAOImpl(this.entityManagerFactory.getEntityManager());
	}


	/**
	 * @see HttpServlet#destroy()
	 * This method is called by the servlet container to perform cleanup before the servlet instance is destroyed.
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
		List<TinTuc> listNews = this.tinTucDAO.layDanhSachTinTuc();
		System.out.println(listNews);
		
		request.setAttribute("DanhSachTinTuc", listNews);
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/DanhSachTinTuc.jsp");
		dispatcher.forward(request, response);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

}
