package fr.bilog.emuserefontebackend.entities.userProfile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.bilog.emuserefontebackend.utilities.CustomerDateAndTimeDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignesClinique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using= CustomerDateAndTimeDeserialize.class)
    private Date creationDate;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<SignesCliniqueItem> signesCliniqueItems = new HashSet<SignesCliniqueItem>();

}
