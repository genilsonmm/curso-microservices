package com.gm2.dev.service;

import com.gm2.dev.dto.CustomerDto;
import com.gm2.dev.dto.CustomerResponseDto;
import com.gm2.dev.entity.Customer;
import com.gm2.dev.exceptions.InvalidAccountException;
import com.gm2.dev.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CustomerService {

    private final Random random;
    private final int upperbound = 9;
    private final CustomerRepository repository;
    private final ModelMapper mapper;

    public CustomerService(CustomerRepository repository){
        this.repository = repository;
        this.mapper = new ModelMapper();
        this.random = new Random();
    }

    public CustomerResponseDto save(CustomerDto customerDto){
        Customer newCustomer = mapper.map(customerDto, Customer.class);
        newCustomer.setEnable(true);
        newCustomer.setAccountNumber(getAccountNumber());

        return mapper.map(repository.save(newCustomer), CustomerResponseDto.class);
    }

    public List<CustomerResponseDto> getAll() {
        Type listType = new TypeToken<List<CustomerResponseDto>>() {}.getType();
        return mapper.map(repository.findAll(), listType);
    }

    public CustomerResponseDto findByAccountNumber(String account){
        Optional<Customer> response = repository.findByAccountNumber(account);

        if(!response.isPresent())
            throw new InvalidAccountException(String.format("A conta %s não é válida", account));

        return mapper.map(response.get(), CustomerResponseDto.class);
    }

    private String getAccountNumber(){
        String account = "";
        for (int i = 0; i < 6; i++){
            account += random.nextInt(upperbound);
        }
        return account;
    }
}
