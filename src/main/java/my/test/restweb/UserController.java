package my.test.restweb;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import my.test.restweb.domain.User;

@RestController
public class UserController {

  @Autowired
  UserService us;

  @GetMapping("/")
  public ModelAndView home() {
    return new ModelAndView("index");
  }
  
  @GetMapping("/adduser")
  public ModelAndView addUser(Map<String, Object> model) {
    User u = us.newUser();
    model.put("user", u);
    return new ModelAndView("show_user", model);
  }

  @GetMapping("/allusers")
  public Iterable<User> getAllUsers() {
    return us.getAllUsers();
  }

  @GetMapping("/allusersview")
  public ModelAndView allusersview(Map<String, Object> model) {
    model.put("users", us.getAllUsers());
    return new ModelAndView("show_allusers", model);
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<User> getUser(@PathVariable Long id) throws URISyntaxException {
    User user = us.getUser(id);
    if (user == null) {
      URI uri = new URI("/api/usernotfound?id="+id);
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.setLocation(uri);
      return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    } else {
      return new ResponseEntity<>(user, HttpStatus.OK);
    }
  }

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @GetMapping("/user")
  public ResponseEntity<User> queryUser(@RequestParam Long id) throws URISyntaxException {
    User user = us.getUser(id);
    logger.info("queryUser: id={} user={}", id, user);

    if (user == null) {
      URI uri = new URI("/api/usernotfound?id="+id);
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.setLocation(uri);
      return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    } else {
      return new ResponseEntity<>(user, HttpStatus.OK);
    }

  }
  
  @GetMapping("/usernotfound")
  public ModelAndView userNotFound(@RequestParam Long id) {
    ModelAndView model = new ModelAndView("user_not_found");
    model.addObject("userid_not_found", id);
    return model;
  }

  
}
