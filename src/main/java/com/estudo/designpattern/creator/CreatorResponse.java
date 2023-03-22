package com.estudo.designpattern.creator;

import java.io.Serializable;

public class CreatorResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String role;

	@Deprecated
	/**
	 * @deprecated Framework eyes only
	 */
	public CreatorResponse() {
	}

	public CreatorResponse(Long id, String name, String role) {
		this.id = id;
		this.name = name;
		this.role = role;
	}

	public CreatorResponse(Creator entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.role = entity.getRole();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
