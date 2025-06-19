package ism.sn.gestion_dettes.Config.impl;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.ism.gestion.Config.Service;
import sn.ism.gestion.utils.exceptions.EntityNotFoundExecption;

import java.util.List;

@AllArgsConstructor
public abstract class IService<T, ID, R extends JpaRepository<T, ID>> implements Service<T, ID> {

    protected final R repository;

    protected IService(R repository) {
        this.repository = repository;
    }

    @Override
    public T create(T object) {
        return repository.save(object);
    }

    @Override
    public T update(ID id, T object) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundExecption("Entity not found with id: " + id);
        }
        return repository.save(object);
    }

    @Override
    public boolean delete(ID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundExecption("Entité non trouvé: " + id));
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}

