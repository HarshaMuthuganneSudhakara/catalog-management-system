package com.thippeshhirenallur.catalogmanagementplatform.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SUBCATEGORY")
@Data
@NoArgsConstructor
@SuppressWarnings("serial")
public class SubCategory implements Serializable {

	@Id
	@Column(name = "SUBCATEGORY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer subCategoryId;

	@Column(name = "SUBCATEGORY_NAME")
	private String subCategoryName;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID")
	private Category category;
	
	@OneToMany(mappedBy = "subcategory", cascade = {
	        CascadeType.ALL
	    })
	private List<Product> products;
	
}
