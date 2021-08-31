package fr.bilog.emuserefontebackend.repositories;

import fr.bilog.emuserefontebackend.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByRoleName(String roleName);

}
