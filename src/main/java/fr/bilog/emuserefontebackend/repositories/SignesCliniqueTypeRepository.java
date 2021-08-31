package fr.bilog.emuserefontebackend.repositories;

import fr.bilog.emuserefontebackend.entities.userProfile.SignesCliniqueType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignesCliniqueTypeRepository extends JpaRepository<SignesCliniqueType , Long> {
}
