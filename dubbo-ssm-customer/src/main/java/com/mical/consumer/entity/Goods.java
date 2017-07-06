package com.mical.consumer.entity;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mical.consumer.util.CustomDoubleSerialize;
import com.mical.consumer.validator.Not999;

public class Goods {

	@Min(900)
	@Not999 // 这个为自定义的验证标签
	private long goodsId;

	private String title;

	private int number;

	// 复杂的转换可以自定义转换方式
	@JsonSerialize(using = CustomDoubleSerialize.class)
	private double price;

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
