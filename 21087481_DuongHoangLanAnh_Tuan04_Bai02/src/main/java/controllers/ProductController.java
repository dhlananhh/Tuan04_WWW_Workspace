package controllers;


import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


import dao.ProductDAO;
import daoImpl.ProductDAOImpl;
import models.Product;
import utils.EntityManagerFactoryUtil;


/**
 * Servlet implementation class ProductController
 */
@WebServlet (name = "ProductController", urlPatterns = { "/product", "/product*" })
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private EntityManagerFactoryUtil entityManagerFactoryUtil;
	private ProductDAO productDAO;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
        super();
    }

    
    /**
     * @see HttpServlet#init(ServletConfig config)
     * This method is used to initialize the servlet
     */
	@Override
	public void init (ServletConfig config) throws ServletException {
		super.init(config);
		this.entityManagerFactoryUtil = new EntityManagerFactoryUtil();
		this.productDAO = new ProductDAOImpl(this.entityManagerFactoryUtil.getEntityManager());
	}

	
	/**
	 * @see HttpServlet#destroy() 
	 * This method is called by the servlet container to perform the destruction of the servlet.
	 */
	@Override
	public void destroy() {
		this.entityManagerFactoryUtil.close();
		super.destroy();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * This method is used to add new products and get all products
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Add new products
//		Product p1 = new Product("Smartphone Model A", 550, "ProductImage_1.png");
//		productDAO.addProduct(p1);
//		
//		Product p2 = new Product("Smartphone Model B", 500, "ProductImage_2.png");
//		productDAO.addProduct(p2);
//		
//		Product p3 = new Product("Keyboard Model K", 625, "ProductImage_3.png");
//		productDAO.addProduct(p3);
//		
//		Product p4 = new Product("Smartphone Model P", 650, "ProductImage_4.png");
//		productDAO.addProduct(p4);
//		
//		Product p5 = new Product("Tablet Model T", 850, "ProductImage_5.png");
//		productDAO.addProduct(p5);
		
		// Get all products
		request.setAttribute("products", productDAO.getAllProducts());
		request.getRequestDispatcher("views/product/index.jsp").forward(request, response);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
