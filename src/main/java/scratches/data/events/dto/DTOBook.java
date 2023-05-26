package scratches.data.events.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import scratches.data.events.author.Author;
import scratches.data.events.book.Book;

@Data
@NoArgsConstructor
public class DTOBook {

    private Long id;

    private Long authorFk;

    private String title;


    public Book toModel(Author author) {
        Book book = new Book();
        BeanUtils.copyProperties(this, book, "author");
        book.setAuthor(author);
        return book;
    }

}
