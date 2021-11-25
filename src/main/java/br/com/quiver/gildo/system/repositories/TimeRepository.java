package br.com.quiver.gildo.system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.quiver.gildo.system.model.Time;

/**
 * Interface que possui as classes de inserção, deleção, atualização e seleção disponibilixadas pelo JPA
 * @author gildo_cordeiro
 *
 */
@Repository
public interface TimeRepository extends JpaRepository<Time, Integer>{

}
