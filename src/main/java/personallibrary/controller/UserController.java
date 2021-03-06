package personallibrary.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import personallibrary.model.User;
import personallibrary.service.UserService;
import personallibrary.view.View;

@RestController
@RequestMapping(value= "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value= "/search/{name}", method = RequestMethod.GET)
	@JsonView(View.UsuarioSimples.class)
	public ResponseEntity<Collection<User>> search(@PathVariable("name") final String name) {
		return new ResponseEntity<Collection<User>>(userService.searchUser(name), HttpStatus.OK);
	}
	
	@RequestMapping(value= "/searchId/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> search(@PathVariable("id") final Long id) {
		return new ResponseEntity<User>(userService.searchUserId(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Collection<User>> getAll() {
		return new ResponseEntity<Collection<User>>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> createUser(@RequestBody final User user) {
		userService.create(user);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteUser(@RequestBody final User user) {
		userService.delete(user);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<HttpStatus> updateUser(@RequestBody final User user) {
		userService.update(user);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	
	
	
}