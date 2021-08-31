package fr.bilog.emuserefontebackend.entities.userProfile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignesCliniqueItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    private int value;
    @ManyToOne(cascade = CascadeType.ALL)
    private SignesCliniqueType signesCliniqueType;
}
