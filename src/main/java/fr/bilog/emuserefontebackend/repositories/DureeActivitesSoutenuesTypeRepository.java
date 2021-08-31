package fr.bilog.emuserefontebackend.repositories;

import fr.bilog.emuserefontebackend.entities.userProfile.DureeActivitesSoutenuesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DureeActivitesSoutenuesTypeRepository extends JpaRepository<DureeActivitesSoutenuesType, Long> {
    Optional<DureeActivitesSoutenuesType> findByLibelle(String libelle);

}
