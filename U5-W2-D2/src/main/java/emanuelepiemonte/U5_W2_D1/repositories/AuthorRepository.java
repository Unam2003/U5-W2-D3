package emanuelepiemonte.U5_W2_D1.repositories;

import emanuelepiemonte.U5_W2_D1.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
    boolean existsByEmail(String email);
}
