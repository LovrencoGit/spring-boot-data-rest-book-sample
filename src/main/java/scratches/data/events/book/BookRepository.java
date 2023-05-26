package scratches.data.events.book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * @author Loris Cernich
 */

// localhost:8080/rest/book
@RepositoryRestResource(path = "book")
public interface BookRepository extends ReadOnlyRestResourceRepository<Book, Long> {

    List<Book> findByAuthorId(@Param("authorId") Long authorId);

}
