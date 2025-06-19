package ism.sn.gestion_dettes.data.services;

public interface IPaiementService {
    Paiement ajouterPaiement(Long detteId, Paiement paiement);
    Page<Paiement> listerPaiements(String telephoneClient, Long detteId, Pageable pageable);
    List<Paiement> getAllByDetteId(Long detteId);
}
