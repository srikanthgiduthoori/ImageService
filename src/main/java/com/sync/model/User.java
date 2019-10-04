package com.sync.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user")
@Getter @Setter
public class User {
	
	@Id
	private String username;

	@Column
	private String password;
	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column
	private String gender;
	@Column
	private String email;



}
