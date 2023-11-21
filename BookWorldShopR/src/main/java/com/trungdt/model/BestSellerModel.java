package com.trungdt.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.trungdt.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class BestSellerModel {
	@Id
	private Product product;
	private long sum;
}
