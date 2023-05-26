package scratches.data.events.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import scratches.data.events.book.Book;
import scratches.data.events.book.BookRepository;
import scratches.data.events.dto.DTOAuthor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// localhost:8080/author
@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

//    @GetMapping("/book/{id}")
//    public ResponseEntity<Long> getById(@PathVariable Long id) {
//        return ResponseEntity.ok(id);
//    }
//    @PostMapping("/book")
//    public ResponseEntity<String> save() {
//        return ResponseEntity.ok("save()");
//    }

    //-----------

    @GetMapping("/count")
    public ResponseEntity<Integer> count() {
        return ResponseEntity.ok( authorRepository.findAll().size() );
    }

//    @PostMapping
//    public ResponseEntity<DTOAuthor> save(@RequestBody DTOAuthor dtoAuthor) {
//        // save Author
//        Author author = dtoAuthor.toModel(null);
//        author = authorRepository.save(author);
//
//        Author finalAuthor = author;
//        List<Book> finalBooks = new ArrayList<>();
//        dtoAuthor.getBooks().forEach(dtoBook -> {
//            Book book = dtoBook.toModel(finalAuthor);
//            finalBooks.add(book);
//        });
//        List<Book> books = (List<Book>) bookRepository.saveAll(finalBooks);
//
//        // set books added inside author added
//        author.setBooks(books);
//        return ResponseEntity.status(HttpStatus.CREATED).body(author.toDTO());
//    }

    @Transactional
    @PostMapping
    public ResponseEntity<DTOAuthor> save(@RequestBody DTOAuthor dtoAuthor) {
        // save Author
        Author author = dtoAuthor.toModel(null);
        author = authorRepository.save(author);

        // save Books (linked to previous author added)
        Author finalAuthor = author;
        List<Book> booksREQUEST = new ArrayList<>();
        dtoAuthor.getBooks().forEach(dtoBook -> {
            Book book = dtoBook.toModel(finalAuthor);
            booksREQUEST.add(book);
        });

        List<Book> booksACTUAL = bookRepository.findByAuthorId(author.getId());

        // [ books to insert ] request.books[i].id == null --> book is new
        List<Book> booksToInsert = booksREQUEST.stream().filter(b -> b.getId()==null).collect(Collectors.toList());
        // [ books to delete / update ]
        List<Book> booksToDelete = new ArrayList<>();
        List<Book> booksToUpdate = new ArrayList<>();
        for(Book bookActual : booksACTUAL){
            Optional<Book> bookOptional = booksREQUEST.stream().filter(b -> b.getId()==bookActual.getId()).findFirst();
            if( bookOptional.isPresent() )
                booksToUpdate.add(bookOptional.get());
            else
                booksToDelete.add(bookActual);
        }


        bookRepository.deleteAll(booksToDelete);

        booksToUpdate.addAll(booksToInsert);
        List<Book> booksNEW = (List<Book>) bookRepository.saveAll(booksToUpdate);


        // set books inside author
        author.setBooks( booksNEW );
        //if(!booksNEW.isEmpty()) throw new RuntimeException("STOP !!!- test @Transactional");
        return ResponseEntity.status(HttpStatus.CREATED).body(author.toDTO());
    }

}
