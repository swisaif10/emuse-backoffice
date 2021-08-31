package fr.bilog.emuserefontebackend.repositories;

import fr.bilog.emuserefontebackend.entities.profiles.ApplicationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationTypeRepository extends JpaRepository<ApplicationType, Long> {
}
