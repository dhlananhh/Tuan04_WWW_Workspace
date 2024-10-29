package dao;


import java.util.List;
import models.Product;


public interface ProductDAO {
	public Product addProduct (Product product);
	public Product updateProduct (Product product);
	public boolean deleteProduct (int productId);
	public Product getProductById (int productId);
	public List<Product> getAllProducts();
}
