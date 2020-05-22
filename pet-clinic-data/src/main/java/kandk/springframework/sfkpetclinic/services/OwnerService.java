package kandk.springframework.sfkpetclinic.services;

import kandk.springframework.sfkpetclinic.model.Owner;


public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);


}
