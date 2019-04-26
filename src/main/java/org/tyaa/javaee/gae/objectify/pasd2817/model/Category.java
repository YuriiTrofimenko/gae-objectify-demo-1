package org.tyaa.javaee.gae.objectify.pasd2817.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Category {

	@Id
	public Long id;
	public String name;
	public String image;
}
