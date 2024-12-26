package net.banking_app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import net.banking_app.dto.AccountDto;
import net.banking_app.entity.Account;
import net.banking_app.mapper.AccountMapper;
import net.banking_app.repository.AccountRepository;
import net.banking_app.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	private AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}



	@Override
	public AccountDto createAccount(AccountDto accountDto) {

		Account account = AccountMapper.mapToAccount(accountDto);
		
		Account savedAccount =  accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	public AccountDto getAccountById(Long id) {

		Account account =  accountRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Account does not exits ------------------------------------------------------------")
				);
		
		return AccountMapper.mapToAccountDto(account);
	}



	@Override
	public AccountDto deposit(Long id, double amount) {

		Account account =  accountRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Account does not exits ------------------------------------------------------------")
				);
		
		
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	public AccountDto withdrwa(Long id, double amount) {


		Account account =  accountRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Account does not exits ------------------------------------------------------------")
				);
		
		
		if(account.getBalance() < amount)
		{
			throw new RuntimeException("Insufficient amount");
		}
		
		double total = account.getBalance() - amount;
		account.setBalance(total);
		Account savedAmount =  accountRepository.save(account);
		
		
		return AccountMapper.mapToAccountDto(savedAmount);
	}



	@Override
	public List<AccountDto> getAllAccounts() {

		List<Account> accounts = accountRepository.findAll();
		
		
		
		return accounts.stream().map((account) 
				->AccountMapper.mapToAccountDto(account))
				.collect(Collectors.toList());
	}



	@Override
	public void deleteAccount(Long id) {
		
		Account account =  accountRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Account does not exits ------------------------------------------------------------")
				);
		
		
		accountRepository.deleteById(id);
		
	}

}
