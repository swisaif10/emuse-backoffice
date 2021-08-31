package fr.bilog.emuserefontebackend.repositories;

import fr.bilog.emuserefontebackend.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
