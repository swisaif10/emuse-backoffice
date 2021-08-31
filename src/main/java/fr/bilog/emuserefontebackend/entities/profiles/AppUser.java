package fr.bilog.emuserefontebackend.entities.profiles;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.bilog.emuserefontebackend.entities.article.Highlight;
import fr.bilog.emuserefontebackend.entities.userProfile.*;
import fr.bilog.emuserefontebackend.utilities.CustomerDateAndTimeDeserialize;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppUser extends User {

    @Column()
    private String sexe;
    @Column()
    private float taille;
    @Column()
    private float poid;
    @Column()
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using= CustomerDateAndTimeDeserialize.class)
    private Date creationDate;
    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using= CustomerDateAndTimeDeserialize.class)
    private Date updateDate;
    @OneToMany
    private Set<FCMToken> fcmTokens = new HashSet<FCMToken>();
    @ManyToOne
    private DureeActivitesModeresType dureeActivitesModeresType;
    @ManyToOne
    private DureeActivitesSoutenuesType dureeActivitesSoutenuesType;
    @OneToMany
    private Set<ActivitesPreferencesType> activitesPreferencesTypes = new HashSet<ActivitesPreferencesType>();
    @OneToMany
    private Set<AshmeHistory> ashmeHistories = new HashSet<AshmeHistory>();
    @OneToMany(cascade = CascadeType.ALL)
    private Set<SignesClinique> signesCliniques = new HashSet<SignesClinique>();
    @JsonIgnore
    @OneToMany
    private Set<Highlight> highlights = new HashSet<Highlight>();
    @OneToOne
    private ApplicationType applicationType;

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public float getTaille() {
        return taille;
    }

    public void setTaille(float taille) {
        this.taille = taille;
    }

    public float getPoid() {
        return poid;
    }

    public void setPoid(float poid) {
        this.poid = poid;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Set<FCMToken> getFcmTokens() {
        return fcmTokens;
    }

    public void setFcmTokens(Set<FCMToken> fcmTokens) {
        this.fcmTokens = fcmTokens;
    }

    public DureeActivitesModeresType getDureeActivitesModeresType() {
        return dureeActivitesModeresType;
    }

    public void setDureeActivitesModeresType(DureeActivitesModeresType dureeActivitesModeresType) {
        this.dureeActivitesModeresType = dureeActivitesModeresType;
    }

    public DureeActivitesSoutenuesType getDureeActivitesSoutenuesType() {
        return dureeActivitesSoutenuesType;
    }

    public void setDureeActivitesSoutenuesType(DureeActivitesSoutenuesType dureeActivitesSoutenuesType) {
        this.dureeActivitesSoutenuesType = dureeActivitesSoutenuesType;
    }

    public Set<ActivitesPreferencesType> getActivitesPreferencesTypes() {
        return activitesPreferencesTypes;
    }

    public void setActivitesPreferencesTypes(Set<ActivitesPreferencesType> activitesPreferencesTypes) {
        this.activitesPreferencesTypes = activitesPreferencesTypes;
    }

    public Set<AshmeHistory> getAshmeHistories() {
        return ashmeHistories;
    }

    public void setAshmeHistories(Set<AshmeHistory> ashmeHistories) {
        this.ashmeHistories = ashmeHistories;
    }

    public Set<SignesClinique> getSignesCliniques() {
        return signesCliniques;
    }

    public void setSignesCliniques(Set<SignesClinique> signesCliniques) {
        this.signesCliniques = signesCliniques;
    }

    public Set<Highlight> getHighlights() {
        return highlights;
    }

    public void setHighlights(Set<Highlight> highlights) {
        this.highlights = highlights;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(ApplicationType applicationType) {
        this.applicationType = applicationType;
    }
}
