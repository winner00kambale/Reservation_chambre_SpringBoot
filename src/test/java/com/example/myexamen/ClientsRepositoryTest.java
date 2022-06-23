package com.example.myexamen;

import com.example.myexamen.clients.Clients;
import com.example.myexamen.clients.ClientsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ClientsRepositoryTest {
    @Autowired private ClientsRepository repo;
    @Test
    public void testAddClients(){
        Clients cli = new Clients();
        cli.setNom("kambale");
        cli.setPostnom("karahire");
        cli.setPrenom("victoire");
        cli.setGenre("masculin");
        cli.setNationalite("congolaise");
        cli.setProfession("etudiant");
        cli.setEtatcivil("celibat");
        cli.setMail("vainqueurkambale@gmail.com");
        cli.setAdresse("katoyi");
        cli.setTelephone("+243 997604471");

        Clients saveClient = repo.save(cli);
        Assertions.assertThat(saveClient).isNotNull();
        Assertions.assertThat(saveClient.getId()).isGreaterThan(0);
    }
    @Test
    public  void ListTestAll(){
        Iterable<Clients> client = repo.findAll();
        Assertions.assertThat(client).hasSizeGreaterThan(0);

        for (Clients clients : client) {
            System.out.println(clients);
        }
    }
    @Test
    public void updateClient(){
        Integer clientid = 1;
        Optional<Clients> optionalClients = repo.findById(clientid);
        Clients client = optionalClients.get();
        client.setNom("winner");

        Clients updatecli = repo.findById(clientid).get();
        Assertions.assertThat(updatecli.getNom()).isEqualTo("winner");
    }
    @Test
    public  void TestGet(){
        Integer clientid = 2;
        Optional<Clients> optionalClients = repo.findById(clientid);
        Assertions.assertThat(optionalClients).isPresent();
        System.out.println(optionalClients.get());
    }
    @Test
    public void deleteTest(){
        Integer clientid = 2;
        repo.deleteById(clientid);

        Optional<Clients> optionalClients = repo.findById(clientid);
        Assertions.assertThat(optionalClients).isNotPresent();
    }
}
