package com.trungdt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// thống kê số lượng đơn hàng theo trạng thái
public class StatisticalTotalOrder {
	private long orderSuccess;
	private long orderCancel;
	private long orderWait;
	private long orderTransport;
}
