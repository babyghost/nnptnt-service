package vn.dnict.microservice.uaa.dao.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Roles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1489907774454817399L;

	@Id
	private String roleName;

	private String description;

	public String getRoleName() {
		return roleName;
	}

	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Roles(String roleName, String description) {
		super();
		this.roleName = roleName;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Roles [roleName=" + roleName + ", description=" + description + "]";
	}

}
