package kmitl.todoapp.controller;

import kmitl.todoapp.entity.MemberEntity;
import kmitl.todoapp.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @RequestMapping(value = "/{memberID}/{friendID}", method = RequestMethod.PUT)
    public ResponseEntity<MemberEntity> addFriend(@PathVariable("memberID") String memberID, @PathVariable String friendID) {
        Optional<MemberEntity> memberEntityData = memberRepository.findById(Integer.parseInt(memberID));
        Optional<MemberEntity> friendEntityData = memberRepository.findById(Integer.parseInt(friendID));

        if (memberEntityData.isPresent() && friendEntityData.isPresent()) {
            MemberEntity _memberEntity = memberEntityData.get();
            MemberEntity _friendEntity = friendEntityData.get();
            _memberEntity.addFriendCollection(_friendEntity);

            return new ResponseEntity<MemberEntity>(memberRepository.save(_memberEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/{memberID}/{friendID}", method = RequestMethod.DELETE)
    public ResponseEntity<MemberEntity> deleteFriend(@PathVariable("memberID") String memberID, @PathVariable String friendID) {
        Optional<MemberEntity> memberEntityData = memberRepository.findById(Integer.parseInt(memberID));
        Optional<MemberEntity> friendEntityData = memberRepository.findById(Integer.parseInt(friendID));

        if (memberEntityData.isPresent() && friendEntityData.isPresent()) {
            MemberEntity _memberEntity = memberEntityData.get();
            MemberEntity _friendEntity = friendEntityData.get();
            //me delete friend
            _memberEntity.delelteFriendCollection(_friendEntity);


            return new ResponseEntity<MemberEntity>(memberRepository.save(_memberEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/{memberID}", method = RequestMethod.GET)
    public ResponseEntity<MemberEntity> getMember(@PathVariable String memberID) {
        Optional<MemberEntity> memberEntityData = memberRepository.findById(Integer.parseInt(memberID));
        if (memberEntityData.isPresent()) {
            MemberEntity _memberEntity = memberEntityData.get();
            return new ResponseEntity<MemberEntity>(_memberEntity, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public Page<MemberEntity> getAllMember(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }
}