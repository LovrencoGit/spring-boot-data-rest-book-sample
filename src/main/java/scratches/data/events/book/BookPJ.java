package scratches.data.events.book;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import javax.persistence.*;

/**
 * @author Loris Cernich
 */
@Projection(
        name = "BookPJ",
        types = { Book.class }
)
public interface BookPJ {

    @Value("#{target.id}")
    Long getId();

    String geTitle();


    @Value("#{target.author.id}")
    Long getAuthorFk();

}
