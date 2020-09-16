package com.buyyu.clients.service;

import com.buyyu.clients.model.Client;
import com.buyyu.clients.persistance.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.concurrent.CompletableFuture;


@Service
@AllArgsConstructor
public class AsyncClientRegistratorService {

    private final ClientRepository clientRepository;

    @Async("asyncExecutor")
    @Transactional
    public CompletableFuture<Client> register(String name, String address) {
        return CompletableFuture.completedFuture(clientRepository.save(new Client(name, address)));
    }
}
