package kandk.springframework.sfkpetclinic.model;

import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;



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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

    public Pet() {
    }

    @Builder
    public Pet(Long id, String name, Owner owner, LocalDate birthDate, Set<Visit> visits) {
        super(id);
        this.name = name;
        this.owner = owner;
        this.birthDate = birthDate;
        if (visits != null)this.visits = visits;
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
        }
        visit.setPet(this);

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
