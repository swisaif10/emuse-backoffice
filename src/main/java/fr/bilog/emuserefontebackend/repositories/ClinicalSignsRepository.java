package fr.bilog.emuserefontebackend.repositories;

import fr.bilog.emuserefontebackend.entities.userProfile.SignesClinique;
import fr.bilog.emuserefontebackend.entities.userProfile.SignesCliniqueItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicalSignsRepository extends JpaRepository<SignesClinique, Long> {
}
