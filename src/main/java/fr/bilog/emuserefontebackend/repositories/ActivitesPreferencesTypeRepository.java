package fr.bilog.emuserefontebackend.repositories;

import fr.bilog.emuserefontebackend.entities.userProfile.ActivitesPreferencesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivitesPreferencesTypeRepository extends JpaRepository<ActivitesPreferencesType, Long> {
    Optional<ActivitesPreferencesType> findByLibelle(String libelle);

}
