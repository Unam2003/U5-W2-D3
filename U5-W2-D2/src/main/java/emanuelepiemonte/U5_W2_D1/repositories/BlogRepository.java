package emanuelepiemonte.U5_W2_D1.repositories;

import emanuelepiemonte.U5_W2_D1.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
