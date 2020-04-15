package ua.lviv.ura.service;

import ua.lviv.ura.domain.User;
import ua.lviv.ura.shared.AbstractCRUD;

public interface UserService extends AbstractCRUD <User> {
	User getUserByEmail(String email);
}
