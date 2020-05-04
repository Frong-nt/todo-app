package kmitl.todoapp.todoapp.repository;

import kmitl.todoapp.todoapp.entity.MemberEntity;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<MemberEntity, Integer> {
}
