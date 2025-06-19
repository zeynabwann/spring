package ism.sn.gestion_dettes.data.services;

public interface IDetteService {

    Dette ajouterDette(Long clientId, Dette dette);
    Page<Dette> listerDettesParTelephone(String telephone, Pageable pageable);
    Optional<Dette> getById(Long id);
    List<Dette> getAllByClientId(Long clientId);
}
