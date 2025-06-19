package ism.sn.gestion_dettes;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sn.ism.gestion.entities.*;
import sn.ism.gestion.repositories.*;

import java.time.LocalDate;
import java.util.*;

@Component
public class DataInitializer {

    @Autowired private ClientRepository clientRepository;
    @Autowired private DetteRepository detteRepository;
    @Autowired private PaiementRepository paiementRepository;

    @PostConstruct
    public void init() {
        paiementRepository.deleteAll();
        detteRepository.deleteAll();
        clientRepository.deleteAll();

        // 1. Clients
        List<Client> clients = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Client client = new Client();
            client.setNom("Client" + i);
            client.setTelephone("77000000" + i);
            client.setAdresse("Adresse" + i);
            clients.add(client);
        }
        clientRepository.saveAll(clients);

        // 2. Dettes
        List<Dette> dettes = new ArrayList<>();
        for (Client client : clients) {
            for (int j = 1; j <= 2; j++) {
                Dette dette = new Dette();
                dette.setClient(client);
                dette.setDate("2024-0" + j + "-15");
                dette.setMontantDette(100.0 * j);
                dette.setMontantPaye(0.0);
                dette.setMontantRestant(100.0 * j);
                dettes.add(dette);
            }
        }
        detteRepository.saveAll(dettes);

        // 3. Paiements
        List<Paiement> paiements = new ArrayList<>();
        for (Dette dette : dettes) {
            Paiement paiement = new Paiement();
            paiement.setDette(dette);
            paiement.setMontant(20.0);
            paiement.setDatePaiement(LocalDate.now());
            paiements.add(paiement);

            double montantPaye = dette.getMontantPaye() + paiement.getMontant();
            dette.setMontantPaye(montantPaye);
            dette.setMontantRestant(dette.getMontantDette() - montantPaye);
        }
        paiementRepository.saveAll(paiements);
        detteRepository.saveAll(dettes);

        System.out.println("Clients, Dettes, Paiements");
    }
}

