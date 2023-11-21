package com.trungdt.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
// đếm số lượng thông ke
public class StatisticalOrder {
	@Id
	private long count;
}
