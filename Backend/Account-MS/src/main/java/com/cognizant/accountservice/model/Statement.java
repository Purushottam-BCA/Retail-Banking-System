package com.cognizant.accountservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "STATEMENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statement {
	@Id
	@SequenceGenerator(name="stat_id", initialValue = 150001)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="stat_id")
	private long transactionId;
	private long sourceId;
	private long targetId;
	private double amount;
	private double sourceBalance;
	private double targetBalance;
	private Date date;
	private String reference;
	public Statement(long sourceId, long targetId, double amount, double sourceBalance, double targetBalance,Date date,
			String reference) {
		super();
		this.sourceId = sourceId;
		this.targetId = targetId;
		this.amount = amount;
		this.sourceBalance = sourceBalance;
		this.targetBalance = targetBalance;
		this.date=date;
		this.reference = reference;
	}
	
	
	

}
