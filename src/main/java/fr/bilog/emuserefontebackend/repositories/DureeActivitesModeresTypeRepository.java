package fr.bilog.emuserefontebackend.repositories;

import fr.bilog.emuserefontebackend.entities.userProfile.DureeActivitesModeresType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DureeActivitesModeresTypeRepository extends JpaRepository<DureeActivitesModeresType, Long> {
    Optional<DureeActivitesModeresType> findByLibelle(String libelle);

}
