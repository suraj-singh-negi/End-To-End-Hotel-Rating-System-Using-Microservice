package com.user.service.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "micro_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	@Id
	@Column(name="ID")
	String userId;
	
	@Column(name="NAME", length=20)
	String name;
	
	@Column(name="EMAIL")
	String email;
	
	@Column(name="ABOUT")
	String about;
	
	@Transient
	private List<Rating> ratings = new ArrayList<>();

}
