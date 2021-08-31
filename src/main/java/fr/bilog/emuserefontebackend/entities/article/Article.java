package fr.bilog.emuserefontebackend.entities.article;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.bilog.emuserefontebackend.entities.profiles.AppAdmin;
import fr.bilog.emuserefontebackend.entities.profiles.ApplicationType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String url;
    private int status;
    private String image;
    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modificationDate;
    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publicationDate;
    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expirationDate;
    @ManyToOne
    private Provenance provenance;
    @ManyToOne
    private Departement departement;
    @ManyToOne
    private AppAdmin appAdmin;

    @JsonIgnore
    @OneToMany
    private Set<Highlight> highlights = new HashSet<Highlight>();
    @ManyToMany
    @JoinTable(name = "Article_ApplicationType_Associations",
            joinColumns = @JoinColumn(name = "idArticle"),
            inverseJoinColumns = @JoinColumn(name = "idApplicationType"))
    private Set<ApplicationType> applicationTypes = new HashSet<ApplicationType>();

    public AppAdmin getAppAdmin() {
        return appAdmin;
    }

    public void setAppAdmin(AppAdmin appAdmin) {
        this.appAdmin = appAdmin;
    }

    public Set<ApplicationType> getApplicationTypes() {
        return applicationTypes;
    }

    public void setApplicationTypes(Set<ApplicationType> applicationTypes) {
        this.applicationTypes = applicationTypes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Provenance getProvenance() {
        return provenance;
    }

    public void setProvenance(Provenance provenance) {
        this.provenance = provenance;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Set<Highlight> getHighlights() {
        return highlights;
    }

    public void setHighlights(Set<Highlight> highlights) {
        this.highlights = highlights;
    }
}
