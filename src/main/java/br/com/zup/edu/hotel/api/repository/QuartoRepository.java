package br.com.zup.edu.hotel.api.repository;

import br.com.zup.edu.hotel.api.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface QuartoRepository extends JpaRepository<Quarto, Long> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Quarto> findById(Long aLong);
}
