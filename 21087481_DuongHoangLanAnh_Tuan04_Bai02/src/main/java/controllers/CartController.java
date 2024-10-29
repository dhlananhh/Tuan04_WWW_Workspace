package controllers;


import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;

import models.Cart;
import utils.EntityManagerFactoryUtil;
import dao.ProductDAO;
import daoImpl.ProductDAOImpl;


/**
 * Servlet implementation class CartController
 */
@WebServlet (name = "CartController", urlPatterns = { "/cart", "/cart*" })
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private EntityManagerFactoryUtil entityManagerFactoryUtil;
	private ProductDAO productDAO;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartController() {
        super();
    }

    
    /**
     * @see HttpServlet#init(ServletConfig)
     * This method is called by the servlet container to perform initialization of the servlet.
     */
	@Override
	public void init(ServletConfig config) throws ServletException {
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
	 * This method is used to do the action: buy, remove, display cart
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the action
		// If the action is NOT null, do the action
		// If the action is null, return an empty string
		String action = request.getParameter("action") != null ? request.getParameter("action") : "";
		
		// Check the action
		switch (action) {
			case "buy":
				doGetBuy(request, response);
                break;
            case "remove":
				doGetRemoveCart(request, response);
				break;
			default:
				doGetDisplayCart(request, response);
				break;
		}
	}

	
	/**
	 * Display cart
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGetDisplayCart (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.getRequestDispatcher("views/cart/index.jsp").forward(request, response);
	}
	
	
	/**
	 * Add item to cart
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGetBuy (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Create a new session
		HttpSession session = request.getSession();
		
		// Get the list of cart from the session
		List<Cart> cart = null;
		
		// Check if the cart is empty
		if (session.getAttribute("cart") == null) {
            // The cart is empty => Create a new cart
			cart = new ArrayList<Cart>();
        } else {
            // The cart is not empty => Get the cart from the session
            cart = (List<Cart>) session.getAttribute("cart");
        }
		
		// Get the product's index in the cart
		int index = isProductExisting(Integer.parseInt(request.getParameter("id")), cart);
		
		// Check if the product is already in the cart
		if (index == -1) {
			// The product is not in the cart => Add the product to the cart
			cart.add(new Cart(productDAO.getProductById(Integer.parseInt(request.getParameter("id"))), 1));
		} else {
			// The product is already in the cart => Increase the quantity of the product
			int quantity = cart.get(index).getQuantity() + 1;
			cart.get(index).setQuantity(quantity);
		}
		
		// Set the cart to the session
		session.setAttribute("cart", cart);
		
		response.sendRedirect("cart");
	}
	
	
	/**
	 * Remove item from cart
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGetRemoveCart (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Create a new session
		HttpSession session = request.getSession();
		
		// Get the list of cart from the session
		List<Cart> cart = (List<Cart>) session.getAttribute("cart");
		
		// If the cart is empty, remove the cart from the session
		// If the cart is not empty, set the cart to the session
		if (cart != null) {
			// Get the product's index in the cart
	        int index = isProductExisting(Integer.parseInt(request.getParameter("id")), cart);
	        
	        // If the product is in the cart, remove the product from the cart
	        if (index != -1) {
	        	// Remove the product from the cart
	            cart.remove(index);
	            
	            // Set the cart to the session
	            session.setAttribute("cart", cart);
	        }
	    }
		
		// Redirect to the cart page
		response.sendRedirect("cart");
	}
	
	
	/**
	 * Check if product is already in the cart
	 * @param id - product id
	 * @param cartList - list of cart
	 * @return -1 - if product is not in the cart
	 */
	private int isProductExisting (int id, List<Cart> cartList) {
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getProduct().getId() == id) {
				return i;
			}
		}
		
		return -1;
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
