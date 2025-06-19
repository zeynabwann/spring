package ism.sn.gestion_dettes.data.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ism.sn.gestion_dettes.data.entities.paiement;




public interface PaiementRepository extends JpaRepository<Paiement, Long> {

    @Query("""
        SELECT p FROM Paiement p
        WHERE p.dette.client.telephone LIKE %:telephone%
          AND CAST(p.dette.id AS string) LIKE %:detteId%
        """)
    Page<Paiement> findByClientTelephoneAndDetteId(
            @Param("telephone") String telephone,
            @Param("detteId") String detteId,
            Pageable pageable);
}
