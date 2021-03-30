package com.thippeshhirenallur.catalogmanagementplatform.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
@Data
@NoArgsConstructor
@SuppressWarnings("serial")
public class Category implements Serializable {

	@Id
	@Column(name = "CATEGORY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;

	@Column(name = "CATEGORY_NAME")
	private String categoryName;

	@OneToMany(mappedBy = "category", cascade = {
	        CascadeType.ALL
	    })
	private List<SubCategory> subCategories;
}
