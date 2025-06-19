package ism.sn.gestion_dettes.data.services.Impl;

@Service
public class PaiementServiceImpl implements PaiementService {

    private final PaiementRepository paiementRepository;
    private final DetteRepository detteRepository;

    public PaiementServiceImpl(PaiementRepository paiementRepository, DetteRepository detteRepository) {
        this.paiementRepository = paiementRepository;
        this.detteRepository = detteRepository;
    }

    @Override
    @Transactional
    public Paiement ajouterPaiement(Long detteId, Paiement paiement) {
        Dette dette = detteRepository.findById(detteId)
                .orElseThrow(() -> new RuntimeException("Dette introuvable"));

        paiement.setDette(dette);
        Paiement savedPaiement = paiementRepository.save(paiement);
        double montantTotalPaye = dette.getPaiements().stream()
                .mapToDouble(Paiement::getMontant)
                .sum() + paiement.getMontant();

        dette.setMontantPaye(montantTotalPaye);
        dette.setMontantRestant(dette.getMontantDette() - montantTotalPaye);

        detteRepository.save(dette);

        return savedPaiement;
    }

    @Override
    public Page<Paiement> listerPaiements(String telephoneClient, Long detteId, Pageable pageable) {
        return paiementRepository.findByTelephoneAndDetteId(telephoneClient, detteId, pageable);
    }

    @Override
    public List<Paiement> getAllByDetteId(Long detteId) {
        return paiementRepository.findByDetteId(detteId);
    }
}
