package com.thippeshhirenallur.catalogmanagementplatform.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SKU")
@Data
@NoArgsConstructor
@SuppressWarnings("serial")
public class SKU implements Serializable {
	
	@Id
	@Column(name = "SKU_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer skuId;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "RETAIL_PRICE")
	private double retailPrice;
	
	@Column(name = "SALE_PRICE")
	private double salePrice;
	
	@Column(name = "INVENTORY_TYPE")
	private String inventoryType;
	
	@Column(name = "QUANTITY_AVAILABLE")
	private double quantityAvailable;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID")
	private Product product;
	
}
