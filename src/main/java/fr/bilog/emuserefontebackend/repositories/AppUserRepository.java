package fr.bilog.emuserefontebackend.repositories;

import fr.bilog.emuserefontebackend.entities.profiles.AppUser;
import fr.bilog.emuserefontebackend.entities.userProfile.FCMToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);

    Page<AppUser> findAll(Specification<AppUser> userSpecification, Pageable pageable);

    Optional<AppUser> findByFcmTokensContains(FCMToken fcmToken);

}
