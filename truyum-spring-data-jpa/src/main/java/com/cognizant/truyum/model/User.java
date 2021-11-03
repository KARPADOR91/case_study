package com.cognizant.truyum.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private boolean admin;
	@ToString.Exclude
	@ManyToMany
	@JoinTable(name = "cart", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "menu_item_id"))
	private Set<MenuItem> cartItems;

}
