package my.test.restweb;

import org.springframework.data.repository.CrudRepository;
import my.test.restweb.domain.User;

public interface UserRepo extends CrudRepository<User, Long>{

}
