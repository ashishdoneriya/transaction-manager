package manager.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transactionID")
	private int transactionID;

	@NotNull
	@Column(name = "item")
	private String item;

	@NotNull
	@Column(name = "price")
	private double price;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "category")
	private Category category;

	@NotNull
	@Column(name = "date")
	private Date date;

	public Transaction() {
	}

	public Transaction(String item, Category category, double price) {
		this.item = item;
		this.category = category;
		this.price = price;
		this.date = new Date();
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public String getItem() {
		return item;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
