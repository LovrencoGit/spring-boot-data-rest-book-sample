package scratches.data.events.book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RestResource;

import java.io.Serializable;

@NoRepositoryBean
public interface ReadOnlyRestResourceRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {


    @Override
    @RestResource(exported = false)
    <S extends T> S save(S entity);
    @Override
    @RestResource(exported = false)
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);



    @Override
    @RestResource(exported = false)
    void deleteById(ID id);
    @Override
    @RestResource(exported = false)
    void delete(T entity);
    @Override
    @RestResource(exported = false)
    void deleteAllById(Iterable<? extends ID> ids);
    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends T> entities);
    @Override
    @RestResource(exported = false)
    void deleteAll();

}
