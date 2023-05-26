package scratches.data.events.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import scratches.data.events.author.Author;
import scratches.data.events.book.Book;

import java.util.List;

@Data
@NoArgsConstructor
public class DTOAuthor {

    private Long id;

    private String name;

    private List<DTOBook> books;


    private Author.Status status;



    public Author toModel() {
        return this.toModel(null);
    }
    public Author toModel(List<Book> books) {
        Author author = new Author();
        BeanUtils.copyProperties(this, author, "books");
        author.setBooks(books);
        return author;
    }
}
