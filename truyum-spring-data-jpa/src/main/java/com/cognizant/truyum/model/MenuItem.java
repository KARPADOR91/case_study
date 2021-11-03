package com.cognizant.truyum.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "menu_item")
public class MenuItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@NotBlank(message= "Title is required")
	@Size(min = 2, max = 65, message = "Title should have 2 to 65 characters")
	@Column(name = "name")
	private String name;
	@Min(value = (long) 0.01, message = "Price is required")
	@Column(name = "price", columnDefinition = "decimal", precision = 8, scale = 2)
	private double price;
	@Column(name = "in_stock")
	private boolean active;
	@NotNull(message = "Launch Date required")
	@Column(name = "launch_date")
	private Date dateOfLaunch;
	@EqualsAndHashCode.Exclude
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@Column(name = "freeDelivery")
	private boolean freeDelivery;
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToMany(mappedBy = "cartItems")
	private Set<User> cartUsers;

}
