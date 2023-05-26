package scratches.data.events.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Loris Cernich
 */
@RepositoryRestResource(path = "author")
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
