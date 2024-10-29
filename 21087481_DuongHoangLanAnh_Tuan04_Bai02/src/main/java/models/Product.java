package models;


import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table (name = "products")
public class Product {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name = "name")
	private String name;
	
	@Column (name = "price")
	private double price;
	
	@Column (name = "image")
	private String image;
	
	
	public Product() {
		
	}


	public Product (String name, double price, String image) {
		this.setName(name);
		this.setPrice(price);
		this.setImage(image);
	}
	
	
	public Product (int id, String name, double price, String image) {
		this.setId(id);
		this.setName(name);
		this.setPrice(price);
		this.setImage(image);
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return id == other.id;
	}


	@Override
	public String toString() {
		return String.format("Product [id = %d, name = %s, price = %.2f, image = %s]", 
				id, name, price, image);
	}
	
}
