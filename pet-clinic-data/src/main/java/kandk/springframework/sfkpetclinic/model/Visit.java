package kandk.springframework.sfkpetclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

    private String description;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pets;

    public Pet getPets() {
        return pets;
    }

    public void setPets(Pet pets) {
        this.pets = pets;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


}
