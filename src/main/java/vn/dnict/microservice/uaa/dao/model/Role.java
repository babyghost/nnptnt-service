package vn.dnict.microservice.uaa.dao.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "uaa_role")
public class Role implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3660610842896362678L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String role;

	public Role() {
	}

	public Role(String email, String role) {
		this(null, email, role);
	}

	public Role(Long id, String email, String role) {
		this.id = id;
		this.email = email;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
