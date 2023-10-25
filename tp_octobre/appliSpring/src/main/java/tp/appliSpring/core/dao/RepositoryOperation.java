package tp.appliSpring.core.dao;


import org.springframework.data.repository.CrudRepository;
import tp.appliSpring.core.entity.Operation;

public interface RepositoryOperation extends CrudRepository<Operation,Long>{

}
