package cap.stone.controller;

import cap.stone.dto.MemberDTO;
import cap.stone.dto.MemberLoginDTO;
import cap.stone.entity.MemberEntity;
import cap.stone.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login/add")
    public ResponseEntity<MemberDTO> addUser(@RequestBody MemberDTO memberDTO){
        if(memberService.save(memberDTO) != -1){
            return new ResponseEntity<>(memberDTO, HttpStatus.CREATED);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("login/delete")
    public ResponseEntity<String> delUser(@RequestBody MemberLoginDTO dto){
        memberService.delete(dto.getMail(), dto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login/mod")
    public ResponseEntity<MemberDTO> modUser(@RequestBody MemberDTO dto){
        if(memberService.update(dto) != -1){
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login/is-same")
    public ResponseEntity<MemberDTO> isSame(@RequestBody MemberDTO dto){
        if(memberService.isSame(dto) == 1){
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
          return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<MemberDTO>> findAll(){
        List<MemberDTO> memberDTOList;
        memberDTOList = memberService.findAll();
        if(memberDTOList != null){
            return new ResponseEntity(memberDTOList,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<MemberDTO> login(@RequestBody MemberLoginDTO ldto){
        MemberDTO dto = memberService.find(ldto.getMail(), ldto.getPassword());
        if(dto!=null){
            return new ResponseEntity<>(dto ,HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/mannerscore/{userId}")
    public ResponseEntity<Integer> findByuserId(@PathVariable Long userId){
        MemberDTO dto = memberService.findById(userId);
        if(dto!=null){
            return new ResponseEntity<>(dto.getMScore() ,HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/mannerscore/{userId}/{mannerscore}")
    public ResponseEntity<Integer> updateMannerScore(@PathVariable Long userId, @PathVariable int mannerscore){
        MemberDTO dto = memberService.findById(userId);
        if(memberService.updateMemberScore(dto, mannerscore) != -1){
            return new ResponseEntity<>(mannerscore, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*@PostMapping("/mannerscore")
    public ResponseEntity<MemberDTO> mScore(@RequestBody MemberDTO dto){

    }

     */
    /*@PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult != null){
            session.setAttribute("mail", loginResult.getMail());
            session.setAttribute("password", loginResult.getPassword());
            session.setAttribute("id", loginResult.getId());
            return "memberPages/mypage";
        } else {
            return "memberPages/login";
        }
    }

     */

    /*@GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model){
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        System.out.println(memberDTO);
        return "memberPages/detail";
    }

     */

    /*@GetMapping("/mypage")
    public String myPageController(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        Object attr = session.getAttribute("mail");
        if (attr != null) {
            MemberDTO loginResult = memberService.findByMail((String) session.getAttribute("mail"));
            if (loginResult != null) {
                session.setAttribute("mail", loginResult.getMail());
                session.setAttribute("password", loginResult.getPassword());
                session.setAttribute("id", loginResult.getId());
                return "memberPages/mypage";
            } else {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }

     */

    /*@GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "/index";
    }

     */
}
