package kandk.springframework.sfkpetclinic.model;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Slf4j
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name= "owner_id")
    private Owner owner;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "pet")
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<Visit> visits = new HashSet<>();

    public Pet() {
    }

    @Builder
    public Pet(Long id, String name, Owner owner, LocalDate birthDate) {
        super(id);
        this.name = name;
        this.owner = owner;
        this.birthDate = birthDate;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

    public void addVisit(Visit visit){
        if(visit.isNew()){
            visits.add(visit);
            log.debug("dodano wizytę o id: " + visit.getId());
            System.out.println("$$$$$$$$$$ dodano wizytę o id: " + visit.getId());
        }
        visit.setPet(this);
        System.out.println("$$$$$$$$$$ dodano peta do wizyty o id: " + visit.getId());

    }

    @Override
    public String toString() {
        return "Pet{" +
                "id" + super.getId()+
                "name='" + name + '\'' +
                ", petType=" + petType +
                ", owner=" + owner +
                ", birthDate=" + birthDate +
                ", visits=" + visits +
                '}';
    }
}
