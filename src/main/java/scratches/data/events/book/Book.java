package scratches.data.events.book;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import scratches.data.events.author.Author;
import scratches.data.events.dto.DTOAuthor;
import scratches.data.events.dto.DTOBook;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Loris Cernich
 */
@Entity
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "author_id")
    })
    private Author author;

    private String title;





    public DTOBook toDTO(Long authorFk){
        DTOBook dtoBook = new DTOBook();
        BeanUtils.copyProperties(this, dtoBook, "authorFk");

        dtoBook.setAuthorFk(authorFk);

        return dtoBook;
    }

}
