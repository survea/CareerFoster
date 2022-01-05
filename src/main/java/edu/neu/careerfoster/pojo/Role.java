package edu.neu.careerfoster.pojo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roleId", unique = true, nullable = false)
	private int roleId;

	@Column(name = "ROLE_NAME")
	private String role_name;
	
	@OneToMany(mappedBy = "role",fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<AppUsers> user = new ArrayList<AppUsers>();

	public int getRoleId() {
		return roleId;
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public List<AppUsers> getUser() {
		return user;
	}

	public void setUser(List<AppUsers> user) {
		this.user = user;
	}
}
