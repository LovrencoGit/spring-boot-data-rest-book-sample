package scratches.data.events.author;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import scratches.data.events.book.Book;
import scratches.data.events.dto.DTOAuthor;
import scratches.data.events.dto.DTOBook;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Loris Cernich
 */
@Entity
@Data
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(
            mappedBy = "author"//,
            //cascade = CascadeType.ALL,
            //orphanRemoval = true
    )
    private List<Book> books;

    private Status status;



    public enum Status { ACTIVE, INACTIVE }




    public DTOAuthor toDTO(){
        DTOAuthor author = new DTOAuthor();
        BeanUtils.copyProperties(this, author);

        List<DTOBook> dtoBooks = new ArrayList<>();
        this.getBooks().forEach( b -> {
            DTOBook dtoBook = b.toDTO(author.getId());
            dtoBooks.add(dtoBook);
        });
        author.setBooks(dtoBooks);

        return author;
    }
}
