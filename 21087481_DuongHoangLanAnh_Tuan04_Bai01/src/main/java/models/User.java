package models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;


@Entity
@Table (name = "users")
public class User {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	protected int id;
	
	@Column (name = "fullName")
	@NotNull (message = "Full name must not be null")
	@NotEmpty (message = "Full name must not be empty")
	@Size (min = 3, max = 100, message = "Full name must be between 3 and 100 characters")
	private String fullName;
	
	@Column (name = "email")
	@NotNull (message = "Email must not be null")
	@NotEmpty (message = "Email must not be empty")
	@Email (message = "Email must be valid")
	private String email;
	
	@Column (name = "job")
	@NotNull (message = "Job must not be null")
	@NotEmpty (message = "Job must not be empty")
	@Size (min = 3, max = 100, message = "Job must be between 3 and 100 characters")
	private String job;
	
	@Column (name = "birthDate")
	@NotNull (message = "Birth date must not be null")
	@NotEmpty (message = "Birth date must not be empty")
	@PastOrPresent (message = "Birth date must be valid")
	private String birthDate;
	
	@Column (name = "gender")
	@NotNull (message = "Gender must not be null")
	@NotEmpty (message = "Gender must not be empty")
	private String gender;
	
	@Column (name = "city")
	@NotNull (message = "City must not be null")
	@NotEmpty (message = "City must not be empty")
	private String city;
	
	@Column (name = "country")
	@NotNull (message = "Country must not be null")
	@NotEmpty (message = "Country must not be empty")
	private String country;
	
	
	public User() {
		
	}


	public User (int id, String fullName, String email, String job, String birthDate, 
			String gender, String city, String country) {
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.job = job;
		this.birthDate = birthDate;
		this.gender = gender;
		this.city = city;
		this.country = country;
	}


	public User(String fullName, String email, String job, String birthDate, 
			String gender, String city, String country) {
		this.fullName = fullName;
		this.email = email;
		this.job = job;
		this.birthDate = birthDate;
		this.gender = gender;
		this.city = city;
		this.country = country;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getJob() {
		return job;
	}


	public void setJob(String job) {
		this.job = job;
	}


	public String getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	@Override
	public String toString() {
		return String.format("User [id = %d, fullName = %s, email = %s, job = %s, birthDate = %s, gender = %s, city = %s, country = %s]",
                id, fullName, email, job, birthDate, gender, city, country);
	}
	
}
