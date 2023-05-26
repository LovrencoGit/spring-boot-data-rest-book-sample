package scratches.data.events.author;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import scratches.data.events.book.BookPJ;

import java.util.List;

/**
 * @author Loris Cernich
 */

@Projection(
    name = "AuthorPJ",
    types = { Author.class }
)
public interface AuthorPJ {

    @Value("#{target.id}")
    Long getId();

    String getName();

    Author.Status getStatus();



    List<BookPJ> getBooks();

    @Value("#{target.books.size()}")
    int getNumberOfBooks();

}
