package ua.lviv.ura.dao;

import ua.lviv.ura.domain.User;
import ua.lviv.ura.shared.AbstractCRUD;

public interface UserDao extends AbstractCRUD<User> {
	User getUserByEmail(String email);
}
