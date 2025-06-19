package ism.sn.gestion_dettes.data.services.Impl;

@Service
public class DetteServiceImpl implements DetteService {

    private final DetteRepository detteRepository;
    private final ClientRepository clientRepository;

    public DetteServiceImpl(DetteRepository detteRepository, ClientRepository clientRepository) {
        this.detteRepository = detteRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Dette ajouterDette(Long clientId, Dette dette) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        dette.setClient(client);

        dette.setMontantPaye(0);
        dette.setMontantRestant(dette.getMontantDette());

        return detteRepository.save(dette);
    }

    @Override
    public Page<Dette> listerDettesParTelephone(String telephone, Pageable pageable) {
        return detteRepository.findByClientTelephone(telephone, pageable);
    }

    @Override
    public Optional<Dette> getById(Long id) {
        return detteRepository.findById(id);
    }

    @Override
    public List<Dette> getAllByClientId(Long clientId) {
        return detteRepository.findByClientId(clientId);
    }
}
