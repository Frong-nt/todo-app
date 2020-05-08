package kmitl.todoapp.repository;

import kmitl.todoapp.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
    Page<TaskEntity> findByMemberId(Integer memberID, Pageable pageable);
    Optional<TaskEntity> findByIdAndMemberId(Integer id, Integer memberID);
}
