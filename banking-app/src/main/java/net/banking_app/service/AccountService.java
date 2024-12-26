package net.banking_app.service;

import java.util.List;

import net.banking_app.dto.AccountDto;

public interface AccountService {

	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id,double amount);
	
	AccountDto withdrwa(Long id,double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccount(Long id);
}
