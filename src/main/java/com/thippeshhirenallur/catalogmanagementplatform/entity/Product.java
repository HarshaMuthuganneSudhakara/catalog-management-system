package com.thippeshhirenallur.catalogmanagementplatform.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PRODUCT")
@Data
@NoArgsConstructor
@SuppressWarnings("serial")
public class Product implements Serializable {

	@Id
	@Column(name = "PRODUCT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "URL")
	private String url;

	@Column(name = "CURRENCY")
	private String currency;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID")
	private Category category;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SUBCATEGORY_ID")
	private SubCategory subcategory;
	
	@OneToMany(mappedBy = "product", cascade = {
	        CascadeType.ALL
	    })
	private List<Media> medias;
	
	@OneToMany(mappedBy = "product", cascade = {
	        CascadeType.ALL
	    })
	private List <SKU> skus;

}
