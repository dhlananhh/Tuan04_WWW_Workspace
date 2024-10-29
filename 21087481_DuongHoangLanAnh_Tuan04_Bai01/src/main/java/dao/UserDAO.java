package dao;

import models.User;
import java.util.List;

public interface UserDAO {
	public User insertUser (User user);
	public User updateUser (User user);
	public boolean deleteUser (int id);
	public List<User> getAllUsers();
	public User getUserById(int id);
}
