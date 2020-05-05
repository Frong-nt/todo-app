package kmitl.todoapp.todoapp.controller;

import kmitl.todoapp.todoapp.entity.MemberEntity;
import kmitl.todoapp.todoapp.entity.TaskEntity;
import kmitl.todoapp.todoapp.exception.ResourceNotFoundException;
import kmitl.todoapp.todoapp.repository.MemberRepository;
import kmitl.todoapp.todoapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path="/members")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private MemberRepository memberRepository;


    @GetMapping("/{memberID}/tasks")
    public Page<TaskEntity> getAllTaskByMemberId(@PathVariable(value = "memberID") Long memberID,
                                                   Pageable pageable) {
        return taskRepository.findByMemberId(memberID, pageable);
    }

    @PostMapping("/{memberID}/tasks")
    public ResponseEntity<TaskEntity> createTask(@PathVariable (value = "memberID") Integer memberID,  @Valid @RequestBody TaskEntity task){

      Optional<TaskEntity> taskData =  memberRepository.findById(memberID).map(member -> {
            task.setMember(member);
            return taskRepository.save(task);
        });
      if(taskData.isPresent()) {
          TaskEntity taskEntity = taskData.get();
          return new ResponseEntity<TaskEntity>(taskEntity, HttpStatus.OK);
      }else{
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }


    @PutMapping("/{memberID}/tasks/{taskID}")
    public TaskEntity updateComment(@PathVariable (value = "memberID") Integer memberID,
                                 @PathVariable (value = "taskID") Integer taskID,
                                 @Valid @RequestBody TaskEntity taskRequest) {
        if(!memberRepository.existsById(memberID)) {
            throw new ResourceNotFoundException("member id " + memberID + " not found");
        }

        return taskRepository.findById(taskID).map(task -> {
            task.setName(taskRequest.getName());
            task.setDescription(taskRequest.getDescription());
            return taskRepository.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException("TaskId " + taskID + "not found"));
    }

    @DeleteMapping("/{memberID}/tasks/{taskID}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "memberID") Long memberID,
                                           @PathVariable (value = "taskID") Long taskID) {
        return taskRepository.findByIdAndMemberId(taskID, memberID).map(comment -> {
            taskRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("task not found with id " + taskID + " and member id " + memberID));
    }

}
