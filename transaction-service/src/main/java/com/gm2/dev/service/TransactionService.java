package com.gm2.dev.service;

import com.gm2.dev.dto.AccountStatementDto;
import com.gm2.dev.dto.TransactionDto;
import com.gm2.dev.dto.TransactionRequestDto;
import com.gm2.dev.dto.TransferResponseDto;
import com.gm2.dev.entity.Transaction;
import com.gm2.dev.repository.TransactionRepository;
import com.gm2.dev.repository.TransferRepository;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService extends BaseService {

    private final TransactionRepository transactionRepository;
    private final TransferService transferService;

    public TransactionService(
            TransactionRepository transactionRepository,
            TransferService transferService){
        this.transactionRepository = transactionRepository;
        this.transferService = transferService;
    }

    public Object save(TransactionRequestDto transactionDto){
        Transaction newTransaction = mapper.map(transactionDto, Transaction.class);
        newTransaction.setDate(LocalDateTime.now());
        return transactionRepository.save(newTransaction);
    }

    public AccountStatementDto getAccountStatement(String accountNumber) {

        AccountStatementDto transactionResponseDto = new AccountStatementDto();

        transactionResponseDto.setAccountNumber(accountNumber);
        transactionResponseDto.setTransactions(getAllTransactions(accountNumber));
        transactionResponseDto.calculateBalance();

        return transactionResponseDto;
    }

    private List<TransactionDto> getAllTransactions(String accountNumber) {
        List<TransactionDto> transactions = new ArrayList<>();
        transactions.addAll(getTransactions(accountNumber));
        transactions.addAll(getTransfers(accountNumber));
        return transactions;
    }

    private List<TransactionDto> getTransactions(String accountNumber){
        var transactions = transactionRepository.getTransactionsByAccountNumber(accountNumber);

        Type listType = new TypeToken<List<TransactionDto>>() {}.getType();
        return mapper.map(transactions, listType);
    }

    private List<TransactionDto> getTransfers(String accountNumber){
        var transfers = transferService.getAllByAccountNumber(accountNumber);
        Type listType = new TypeToken<List<TransferResponseDto>>(){}.getType();
        return mapper.map(transfers, listType);
    }

}
