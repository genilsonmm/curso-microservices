package com.gm2.dev.service;

import com.gm2.dev.dto.TransferRequestDto;
import com.gm2.dev.dto.TransferResponseDto;
import com.gm2.dev.entity.Transfer;
import com.gm2.dev.repository.TransferRepository;
import com.gm2.dev.util.Operation;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferService extends BaseService {

    private final TransferRepository transferRepository;

    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public Object save(TransferRequestDto transferRequestDto){
        transferRequestDto.setDateTime(LocalDateTime.now());
        Transfer transfer = mapper.map(transferRequestDto, Transfer.class);
        return transferRepository.save(transfer);
    }

    public List<TransferResponseDto> getAllByAccountNumber(String accountNumber){
        var transfers = transferRepository.getTransfersByAccountFrom(accountNumber);
        Type listType = new TypeToken<List<TransferResponseDto>>(){}.getType();

        List<TransferResponseDto> transferResponse = mapper.map(transfers, listType);

        transferResponse.forEach(t-> {
            if(t.getAccountFrom().equals(accountNumber)){
                t.setOperation(Operation.TransferenciaEnviada);
                t.setValue(t.getValue().negate()); //Inverte
            } else {
                t.setOperation(Operation.TransferenciaRecebida);
            }
        });

        return transferResponse;
    }
}
