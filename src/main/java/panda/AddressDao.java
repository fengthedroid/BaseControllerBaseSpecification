package panda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Feng on 11-Jan-15.
 */
public interface AddressDao extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {
}
