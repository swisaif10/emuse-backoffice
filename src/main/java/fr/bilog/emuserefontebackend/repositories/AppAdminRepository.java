package fr.bilog.emuserefontebackend.repositories;

import fr.bilog.emuserefontebackend.entities.AppAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppAdminRepository extends JpaRepository<AppAdmin,Long> {
}
