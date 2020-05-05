package kmitl.todoapp.todoapp.repository;

import kmitl.todoapp.todoapp.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
    Page<TaskEntity> findByMemberId(Long memberID, Pageable pageable);
    Optional<TaskEntity> findByIdAndMemberId(Long id, Long postId);
}
