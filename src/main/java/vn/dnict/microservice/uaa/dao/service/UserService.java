package vn.dnict.microservice.uaa.dao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import vn.dnict.microservice.core.api.CoreAttachmentController;
import vn.dnict.microservice.uaa.dao.model.Role;
import vn.dnict.microservice.uaa.dao.model.User;
import vn.dnict.microservice.uaa.dao.service.auth.TokenService;
import vn.dnict.microservice.uaa.data.NewUser;
import vn.dnict.microservice.uaa.exception.UserNotFoundException;
import vn.dnict.microservice.uaa.exception.UsernameTakenException;

@Slf4j
@Service
public class UserService {
	public static final String USER_ROLE = "user";
	private UserRepo repository;
	private PasswordEncoder passwordEncoder;
	private TokenService tokenService;

	@Autowired
	public UserService(UserRepo repository, PasswordEncoder passwordEncoder, TokenService tokenService) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.tokenService = tokenService;
	}

	public User findByEmail(String email) {
		return repository.findByEmail(email).orElse(null);
	}

	public boolean existsByEmail(String email) {
		return repository.existsByEmail(email);
	}

	public User findById(Long id) {
		return repository.findById(id).orElseThrow(UserNotFoundException::new);
	}

	@Transactional
	public String changeRoles(String email, List<Role> roles) {
		Optional<User> userOp = repository.findByEmail(email);
		if (userOp.isPresent()) {
			User user = userOp.get();
			user.getRoles();
			user.setRoles(roles);
			user = repository.saveAndFlush(user);
			return tokenService.encode(user);
		}
		throw new UserNotFoundException("Tài khoản không tồn tại");

	}

	@Transactional
	public String changePassword(String email, String newPass) {
		Optional<User> userOp = repository.findByEmail(email);
		if (userOp.isPresent()) {
			User user = userOp.get();
			user.setPassword(passwordEncoder.encode(newPass));
			user = repository.saveAndFlush(user);
			return tokenService.encode(user);
		}
		throw new UserNotFoundException("Tài khoản không tồn tại");

	}

	@Transactional
	public String update(String email, String username, List<String> roles) {
		User u = new User();
		Optional<User> opUser = repository.findByEmail(email);
		if (opUser.isPresent()) {
			u = opUser.get();
		}
		List<Role> newRoles = new ArrayList<>();
		for (String item : roles) {
			newRoles.add(new Role(email, item));
		}
		// u.setRoles(newRoles);
		u.setEmail(email);
		u.setUsername(username);
		u.setEnabled(true);
		u.getRoles().clear();
		u.getRoles().addAll(newRoles);
		// User user = repository.saveAndFlush(new User(appCode,email, username,
		// passwordEncoder.encode(password), true, Collections.singletonList(role)));
		User user = repository.saveAndFlush(u);
		return tokenService.encode(user);
	}

	@Transactional
	public String create(NewUser newUser) {
		if (repository.findByEmail(newUser.getEmail()).isPresent()) {
			throw new UsernameTakenException("Username is already taken");
		}
		List<Role> newRoles = new ArrayList<>();
		for (String item : newUser.getRoles()) {
			newRoles.add(new Role(newUser.getEmail(), item));
		}
		log.info("thong bao" + newRoles.toString());
		User user = new User();
		user.setEmail(newUser.getEmail());
		user.setUsername(newUser.getUsername());
		user.setPassword(passwordEncoder.encode(newUser.getPassword()));
		user.setEnabled(newUser.getEnabled());
		// user.setRoles(newRoles);
		user.setAppCode(newUser.getAppCode());
		user.getRoles().clear();
		user.getRoles().addAll(newRoles);
		user = repository.saveAndFlush(user);
		return tokenService.encode(user);
	}

//    @PostConstruct
//    public void register() {
//        registration.newUserRequest().subscribe(message -> {
//            NewUser payload = (NewUser) message.getPayload();
//            save(payload.getEmail(), payload.getUsername(), payload.getPassword());
//        });
//    }

	public Page<User> findAll(String email, String userName, String role, Boolean trangThai, Boolean isDungChung,
			String appCode, Pageable pageable) {
		return repository.findAll(
				UserSpecifications.quickSearch(email, userName, role, trangThai, isDungChung, appCode), pageable);
	}
}
