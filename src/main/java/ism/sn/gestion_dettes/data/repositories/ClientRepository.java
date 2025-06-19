package ism.sn.gestion_dettes.data.repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import sn.ism.gestion.data.entities.Client;



    public interface ClientRepository extends JpaRepository<Client, Long> {

        Optional<Client> findByTelephone(String telephone);



    }

