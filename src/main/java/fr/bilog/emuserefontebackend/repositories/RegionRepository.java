package fr.bilog.emuserefontebackend.repositories;

import fr.bilog.emuserefontebackend.entities.article.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByLibelle(String libelle);
}
