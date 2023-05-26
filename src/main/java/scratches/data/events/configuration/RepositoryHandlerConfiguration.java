package scratches.data.events.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import scratches.data.events.author.AuthorRepositoryEventHandler;

/**
 * @author Loris Cernich
 */
@Configuration
public class RepositoryHandlerConfiguration {

    @Bean
    public AuthorRepositoryEventHandler authorRepositoryEventHandler() {
        return new AuthorRepositoryEventHandler();
    }

}
