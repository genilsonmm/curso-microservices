package com.gm2.dev.service;

import com.gm2.dev.dto.ResponseDto;
import com.gm2.dev.exceptions.InvalidUserAccountException;
import com.gm2.dev.request.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Scope("singleton")
public class AccountValidationService {

    private final AccountRequest accountRequest;

    public AccountValidationService(AccountRequest accountRequest) { this.accountRequest = accountRequest; }

    public boolean verifyAccount(String accountNumber){
        ResponseDto responseDto = accountRequest.getUserAccount(accountNumber);

        System.out.println("ACCOUNT_SERVICE PORT: " + responseDto.getPort());

        if(Objects.isNull(responseDto.getData()))
            throw new InvalidUserAccountException(responseDto.getMessage());

        return true;
    }

    public boolean accountsAreValid(String accountFrom, String accountTo) {
        if(verifyAccount(accountFrom) && verifyAccount(accountTo))
            return true;

        return false;
    }
}
