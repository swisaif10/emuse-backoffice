package fr.bilog.emuserefontebackend.repositories;

import fr.bilog.emuserefontebackend.entities.userProfile.SignesCliniqueItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicalSignsItems extends JpaRepository <SignesCliniqueItem,Long> {
}
