package ism.sn.gestion_dettes.data.services.Impl;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client ajouterClient(Client client) {
        if (clientRepository.findByTelephone(client.getTelephone()).isPresent()) {
            throw new RuntimeException("Le téléphone est déjà utilisé par un autre client");
        }
        return clientRepository.save(client);
    }

    @Override
    public Page<Client> listerClients(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public Optional<Client> getClientTelephone(Long id) {
        return clientRepository.findById(id);
    }
