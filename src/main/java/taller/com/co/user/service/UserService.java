package taller.com.co.user.service;

import taller.com.co.user.entity.Users;
import java.util.List;

public interface UserService {

    public List<Users> listAllUser();

    public Users createUser(Users user);

    public void deleteUser(Long id);

    public Users getUserById(Long id);

}
