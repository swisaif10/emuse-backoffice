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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AshmeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    private int value1;
    @Column()
    private int value2;
    @Column()
    private int value3;
    @Column()
    private int value4;
    @Column()
    private int value5;
    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    @JsonDeserialize(using= CustomerDateAndTimeDeserialize.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
}
