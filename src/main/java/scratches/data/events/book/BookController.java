package scratches.data.events.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// localhost:8080/book
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository repository;

//    @GetMapping("/book/{id}")
//    public ResponseEntity<Long> getById(@PathVariable Long id) {
//        return ResponseEntity.ok(id);
//    }
//    @PostMapping("/book")
//    public ResponseEntity<String> save() {
//        return ResponseEntity.ok("save()");
//    }

    //-----------

    @GetMapping
    public ResponseEntity<String> customGetAll() {
        return ResponseEntity.ok("customGetAll()");
    }
    @PostMapping
    public ResponseEntity<String> customSave() {
        return ResponseEntity.ok("customSave()");
    }

}
