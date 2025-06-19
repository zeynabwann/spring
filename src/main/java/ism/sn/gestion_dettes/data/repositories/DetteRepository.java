package ism.sn.gestion_dettes.data.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sn.ism.gestion.data.entities.Dette;

public interface DetteRepository extends JpaRepository<Dette, Long> {

    @Query("SELECT d FROM Dette d WHERE d.client.telephone LIKE %:telephone%")
    Page<Dette> findByClientTelephoneContaining(@Param("telephone") String telephone, Pageable pageable);


}

}
