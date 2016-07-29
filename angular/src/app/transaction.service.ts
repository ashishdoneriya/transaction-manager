import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable }     from 'rxjs/Observable';
import 'rxjs/Rx';

import { Transaction } from './transaction';

@Injectable()
export class TransactionService {

	constructor(private http: Http) { }

	getCategories(): Observable<string[]> {
		return this.http.get('api/categories').map(res => res.json()).catch(this.handleError);
	}

	getTransaction(id: number) {
		return this.http.get('api/transactions').map(res => res.json()).catch(this.handleError);
	}

	addCategories(cat: string) {
		return this.http.post('api/add-category', JSON.stringify({ 'name': cat }));
	}

	getAllItems(): Array<Transaction> {
		return null;
	}

	addTransaction(item: string, category: string) {
	}



	updateTransaction(id: number, item: string, category: string, date: Date) {

	}

	private handleError(error: any) {
		// In a real world app, we might use a remote logging infrastructure
		// We'd also dig deeper into the error to get a better message
		let errMsg = (error.message) ? error.message :
			error.status ? `${error.status} - ${error.statusText}` : 'Server error';
		console.error(errMsg); // log to console instead
		return Observable.throw(errMsg);
	}


}