package fr.bilog.emuserefontebackend.entities.profiles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppAdmin extends User {
    @Column()
    private String email;
    @Column()
    private String nom;
    @Column()
    private String prenom;

}
