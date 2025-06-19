package ism.sn.gestion_dettes.data.services;

public interface IClientService {


    Client ajouterClient(Client client);

    Page<Client> listerClients(Pageable pageable);

    Optional<Client> getClientById(Long id);

    Optional<Client> getClientByTelephone(String telephone);
}
