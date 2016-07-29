import { Component } from '@angular/core';
import { TransactionService } from './transaction.service';
@Component({
  moduleId: module.id,
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css'],
  providers: [TransactionService]
})

export class AppComponent {
	title = 'app works!';

	categories: Array<string> = [];
	constructor(private service: TransactionService) {

	}

	displayCategories() {
		this.service.getCategories().subscribe((categories) => this.categories = categories);
	}

}
