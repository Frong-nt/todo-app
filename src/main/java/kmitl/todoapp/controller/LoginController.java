package kmitl.todoapp.controller;

import kmitl.todoapp.entity.MemberEntity;
import kmitl.todoapp.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(path="/login")
public class LoginController  {
    @Autowired
    MemberRepository memberRepository;


    @RequestMapping(method = RequestMethod.POST)
    public HashMap<String, Object> login(@RequestBody MemberEntity user){
        HashMap<String, Object> map = new HashMap<>();
        MemberEntity memberEntity;
        try {
             memberEntity = memberRepository.findByEmail(user.getEmail());
             if(memberEntity.getPassword().equals(user.getPassword())){
                 map.put("status", true);
             }
             else {
                 map.put("status", false);
             }


        }catch (Exception ex){
            memberEntity = null;
            map.put("status", false);
        }

        map.put("member", memberEntity);
        return map;
    }
}
