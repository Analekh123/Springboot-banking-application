package net.banking_app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.banking_app.dto.AccountDto;
import net.banking_app.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	
	
	//ADD ACCOUNT REST API
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto)
	{
		return new ResponseEntity<AccountDto>(accountService.createAccount(accountDto),HttpStatus.CREATED);
	}
	
	
	//GET ACCOUNT REST API
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long id)
	{
		AccountDto accountDto = accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
	}
	
	
	//DEPOSIT REST API
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
			@RequestBody Map<String, Double> request)
	{
		
		double amount = request.get("amount");
		
		AccountDto accountDto =  accountService.deposit(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	
	//WITHDRAW REST API
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,
			@RequestBody Map<String, Double> request)
	{
		double amount = request.get("amount");
		AccountDto accountDto = accountService.withdrwa(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	
	//GET ALL ACCOUNTS REST API
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts()
	{
		List<AccountDto> accounts = accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
	}
	
	
	//DELETE ACCOUNT REST API
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable("id") Long id)
	{
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account Deleted Successfully");
	}
	
}
