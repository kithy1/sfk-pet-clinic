package kandk.springframework.sfkpetclinic.services.springdatajpa;

import kandk.springframework.sfkpetclinic.model.Vet;
import kandk.springframework.sfkpetclinic.repositories.VetRepository;
import kandk.springframework.sfkpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VetSDJpaService implements VetService {

    public VetSDJpaService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    private final VetRepository vetRepository;

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet findById(Long aLong) {
        return vetRepository.findById(aLong).orElse(null);
    }

    @Override
    public Vet save(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public void delete(Vet vet) {
        vetRepository.delete(vet);

    }

    @Override
    public void deleteById(Long aLong) {
     vetRepository.deleteById(aLong);

    }

    @Override
    public Vet saveAndFlush(Vet var1) {
        return null;
    }
}
