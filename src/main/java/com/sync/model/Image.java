package com.sync.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="image")
@Getter @Setter
public class Image {

		@Id
	 	private String id;
		@Column
	    private String title;
		@Column
	    private String description;
		@Column
	    private int datetime;
		@Column
	    private String type;
		@Column
	    private String deletehash;
		@Column
	    private String name;
		@Column
	    private String link;
		@Column
		private String username;
		


}
