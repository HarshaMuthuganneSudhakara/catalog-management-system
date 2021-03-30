package com.thippeshhirenallur.catalogmanagementplatform.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MEDIA")
@Data
@NoArgsConstructor
@SuppressWarnings("serial")
public class Media implements Serializable {

	@Id
	@Column(name = "MEDIA_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mediaId;

	@Column(name = "IMAGE_URL")
	private String imageUrl;

	@Column(name = "ALT_TEXT")
	private String altText;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID")
	private Product product;
}
