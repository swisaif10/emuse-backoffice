package fr.bilog.emuserefontebackend.repositories;

import fr.bilog.emuserefontebackend.entities.profiles.AppAdmin;
import fr.bilog.emuserefontebackend.entities.profiles.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppAdminRepository extends JpaRepository<AppAdmin, Long> {
    Optional<AppAdmin> findByUsername(String username);

    List<AppAdmin> findAllByRoleAndIsEnabled(Role role, boolean isEnabled);

}
