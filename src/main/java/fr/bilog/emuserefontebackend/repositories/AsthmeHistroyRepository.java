package fr.bilog.emuserefontebackend.repositories;

import fr.bilog.emuserefontebackend.entities.userProfile.AshmeHistory;
import fr.bilog.emuserefontebackend.models.AsthmeControlUpdateRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsthmeHistroyRepository extends JpaRepository<AshmeHistory, Long> {


   @Query("select asthme from AshmeHistory asthme where asthme.value1 =:val1 and  asthme.value2 =:val2 and asthme.value3 =:val3 and asthme.value4 =:val4  and asthme.value5 =:val5")

   Optional <AshmeHistory> findByValue1AndValue2AndValue3AAndValue4AndValue5(@Param("val1") int val1,@Param("val2") int val2,@Param("val3") int val3,@Param("val4") int val4,@Param("val5") int val5);


}
