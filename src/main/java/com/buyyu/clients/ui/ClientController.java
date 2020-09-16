package com.buyyu.clients.ui;

import com.buyyu.clients.model.Client;
import com.buyyu.clients.persistance.ClientRepository;
import com.buyyu.clients.service.AsyncClientRegistratorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
public class ClientController {

    private final AsyncClientRegistratorService asyncClientRegistratorService;
    private final ClientRepository clientRepository;

    @PostMapping("/clients")
    ResponseEntity register(@RequestBody ClientCommand clientCommand) throws ExecutionException, InterruptedException {
        CompletableFuture<Client> registeredClient = asyncClientRegistratorService.register(clientCommand.getName(), clientCommand.getAddress());
        CompletableFuture.allOf(registeredClient).join();
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredClient.get());
    }

    @GetMapping("/clients/{id}")
    ResponseEntity<List<Client>> getClients(@PathVariable("id") String clientId) {
        return ResponseEntity.ok().body(clientRepository.findByClientId(UUID.fromString(clientId)));
    }

    @GetMapping("/clients")
    ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok().body(clientRepository.findAll());
    }
}
