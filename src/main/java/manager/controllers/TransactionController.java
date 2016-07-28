package manager.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import manager.dao.CategoryDao;
import manager.dao.TransactionDao;
import manager.models.Transaction;
import manager.services.CategoryService;

@Controller
@RequestMapping("/api")
public class TransactionController {
	
	@Autowired TransactionDao transactionDao;
	
	@Autowired CategoryDao categoryDao;
	
	@Autowired CategoryService categoryService;
	
	private Gson gson = new Gson();
	
	@ResponseBody
	@RequestMapping(value = "/add-transaction", method = RequestMethod.POST)
	public int addTransaction(HttpServletRequest request) {
		String item = request.getParameter("item");
		String sCategoryID = request.getParameter("categoryID");
		String sPrice = request.getParameter("price");
		return transactionDao.save(new Transaction(item, categoryDao.get(Integer.parseInt(sCategoryID)), Double.parseDouble(sPrice)));
	}
	
	@ResponseBody
	@RequestMapping(value = "/add-category", method = RequestMethod.POST)
	public int addCategory(HttpServletRequest request) {
		return categoryService.createCategory(request.getParameter("name"));
	}
	
	@ResponseBody
	@RequestMapping(value = "/transactions", method = RequestMethod.GET)
	public String transactions(HttpServletRequest request) {
		return gson.toJson(transactionDao.list());
	}
	
	@ResponseBody
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String categories(HttpServletRequest request) {
		return gson.toJson(categoryDao.list());
	}

}
