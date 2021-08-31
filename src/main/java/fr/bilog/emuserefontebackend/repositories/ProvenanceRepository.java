package fr.bilog.emuserefontebackend.repositories;

import fr.bilog.emuserefontebackend.entities.article.Provenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvenanceRepository extends JpaRepository<Provenance, Long> {
}
