package com.gm2.dev.repository;

import com.gm2.dev.entity.Transfer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends MongoRepository<Transfer, String> {

    @Query("{$or: [{'accountFrom': ?0}, {'accountTo': ?0}]}")
    List<Transfer> getTransfersByAccountFrom(String accountNumber);
}
