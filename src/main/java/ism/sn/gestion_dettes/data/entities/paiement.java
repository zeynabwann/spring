package ism.sn.gestion_dettes.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class paiement {



    @Getter
    @Setter
    @Entity
    @Table(name = "paiements")
    public class Paiement {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        @Temporal(TemporalType.DATE)
        private Date datePaiement;

        @Column(nullable = false)
        private Double montant;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "dette_id", nullable = false)
        private Dette dette;
    }

}
