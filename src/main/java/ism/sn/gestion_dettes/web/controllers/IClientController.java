package ism.sn.gestion_dettes.web.controllers;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ism.gestion.entities.Client;
import sn.ism.gestion.entities.Dette;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public interface IClientController {

    // Ajouter un client
    @PostMapping
    ResponseEntity<Client> ajouterClient(@RequestBody Client client);

    @GetMapping("/{telephone}")
    ResponseEntity<Client> getClientByTelephone(@PathVariable String telephone);

    // Lister les clients avec pagination
    @GetMapping
    Page<Client> listerClients(Pageable pageable);

    @PostMapping("/{clientId}/dettes")
    ResponseEntity<List<Dette>> ajouterDettesPourClient(@PathVariable Long clientId, @RequestBody List<Dette> dettes);
}
