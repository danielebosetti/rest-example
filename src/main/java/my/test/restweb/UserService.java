package my.test.restweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import my.test.restweb.domain.User;

@Service
public class UserService {

  @Autowired
  UserRepo ur;
  
  public User newUser() {
    User u = new User();
    long time = System.currentTimeMillis()%512;
    u.setName("name-"+time);
    if (time<100) u.setActive(true);
    u = ur.save(u);
    return u;
  }

  public User getUser(Long id) {
    return ur.findById(id).orElse(null);
  }

  public Iterable<User> getAllUsers() {
    return ur.findAll();
  }

}
