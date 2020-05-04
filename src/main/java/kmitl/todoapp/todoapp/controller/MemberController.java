package kmitl.todoapp.todoapp.controller;

import kmitl.todoapp.todoapp.entity.MemberEntity;
import kmitl.todoapp.todoapp.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/members")
public class MemberController {
    @Autowired
    MemberRepository memberRepository;
    // create member
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<MemberEntity> createMember(@RequestBody MemberEntity memberEntity) {
        MemberEntity returnValue = memberRepository.save(memberEntity);
        return new ResponseEntity<MemberEntity>(returnValue, HttpStatus.OK);
    }

    @RequestMapping( value = "/{memberID}/{friendID}",method = RequestMethod.PUT)
    public ResponseEntity<MemberEntity> addFriend(@PathVariable("memberID") String memberID, @PathVariable String friendID) {
        Optional<MemberEntity> memberEntityData = memberRepository.findById(Integer.parseInt(memberID));
        Optional<MemberEntity> friendEntityData = memberRepository.findById(Integer.parseInt(friendID));

        if(memberEntityData.isPresent() && friendEntityData.isPresent()){
            MemberEntity _memberEntity = memberEntityData.get();
            MemberEntity _friendEntity = friendEntityData.get();
            _memberEntity.addFriendCollection(_friendEntity);
            return new ResponseEntity<MemberEntity>(memberRepository.save(_memberEntity), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @RequestMapping( value = "/{memberID}/{friendID}",method = RequestMethod.DELETE)
    public ResponseEntity<MemberEntity> deleteFriend(@PathVariable("memberID") String memberID, @PathVariable String friendID) {
        Optional<MemberEntity> memberEntityData = memberRepository.findById(Integer.parseInt(memberID));
        Optional<MemberEntity> friendEntityData = memberRepository.findById(Integer.parseInt(friendID));

        if(memberEntityData.isPresent() && friendEntityData.isPresent()){
            MemberEntity _memberEntity = memberEntityData.get();
            MemberEntity _friendEntity = friendEntityData.get();
            _memberEntity.delelteFriendCollection(_friendEntity);
            return new ResponseEntity<MemberEntity>(memberRepository.save(_memberEntity), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping
    public  ResponseEntity<MemberEntity> getMember(){
        return null;
    }


}
