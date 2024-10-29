package models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "carts")
public class Cart {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn (name = "productId")
	private Product product;
	
	@Column (name = "quantity")
	private int quantity;
	
	
	public Cart() {
		
	}
	
	
	public Cart (Product product, int quantity) {
		this.setProduct(product);
		this.setQuantity(quantity);
	}
	
	
	public Cart(int id, Product product, int quantity) {
		this.id = id;
		this.product = product;
		this.quantity = quantity;
	}


	public Product getProduct() {
		return product;
	}
	

	public void setProduct(Product product) {
		this.product = product;
	}
	

	public int getQuantity() {
		return quantity;
	}
	

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return String.format("Cart [product=%s, quantity=%s]", product, quantity);
	}
	
}
