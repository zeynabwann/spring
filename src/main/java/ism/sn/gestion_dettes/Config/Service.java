package ism.sn.gestion_dettes.Config;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Service<T, ID> {

    T create(T object);
    T update(ID id, T object);
    boolean delete(ID id);
    T findById(ID id);
    List<T> findAll();
    Page<T> findAll(Pageable pageable);
}
