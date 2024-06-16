package net.codejava.repository;
import net.codejava.entity.Involce;
import net.codejava.entity.Permission;
import net.codejava.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvolceRepository extends JpaRepository<Involce, Long> {

}
