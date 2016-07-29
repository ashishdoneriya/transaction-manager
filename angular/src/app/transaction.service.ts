import { Injectable } from '@angular/core';
import { Transaction } from './transaction';

@Injectable()
export class TransactionService {

	getCategories(): Array<string> {
		let categories: string = localStorage.getItem['categories'];
		if (categories == null) {
			return [
				'Base',
				'Food',
				'Investment'
			];
		}
		return JSON.parse(categories);
	}

	addCategories(cat: string) {
		let categories: Array<string> = this.getCategories();
		categories.push(cat);
		localStorage.setItem('categories', JSON.stringify(categories));
	}

	getAllItems(): Array<Transaction> {
		let transactions: string = localStorage.getItem['transactions'];
		if (transactions == null) {
			return [];
		}
		return JSON.parse(transactions);
	}

	generateID(): number {
		let sIDs: string = localStorage.getItem['IDs'];
		if (sIDs == null) {
			localStorage.setItem('IDs', '1');
			return 2;
		} else {
			let ids: number = Number(sIDs);
			ids++;
			localStorage.setItem('IDs', String(ids));
			return ids;
		}
	}

	addTransaction(item: string, category: string) {
		let transactions = this.getAllItems();
		transactions.push(new Transaction(this.generateID(), item, category, new Date()));
	}

	getTransaction(id: number) {
		this.getAllItems().forEach(transaction => {
			if (transaction.id == id) {
				return transaction;
			}
		});
	}

	updateTransaction(id: number, item: string, category: string, date: Date) {
		let transactions = this.getAllItems();
		transactions.forEach(transaction => {
			if (transaction.id == id) {
				transaction.item = item;
				transaction.category = category;
				transaction.data = date;
			}
		});
		localStorage.setItem('transactions', JSON.stringify(transactions));
	}


}