package com.buyyu.clients;

import com.buyyu.clients.model.Client;
import com.buyyu.clients.persistance.ClientRepository;
import com.buyyu.clients.ui.ClientCommand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ClientRegistrationTest {

    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    ClientRepository clientRepository;

    @Test
    void shouldRegisteredClient() {
        Client client = registerClient("Neil", "London");
        verifyClient(client);
    }

    private Client registerClient(String name, String address) {
        return restTemplate.postForEntity("/clients", new ClientCommand(name, address), Client.class).getBody();
    }

    private void verifyClient(Client client) {
        List<Client> existed = clientRepository.findByClientId(client.getClientId());
        assertThat(existed).isNotNull();
        assertThat(existed.get(0).getName()).isEqualTo(client.getName());
        assertThat(existed.get(0).getAddress()).isEqualTo(client.getAddress());

    }
}
