/*package cap.stone;

import cap.stone.dto.MemberDTO;
import cap.stone.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MemberTest {
    @Autowired
    private MemberService memberService;

    /*public MemberDTO newMember(int i){
        MemberDTO member =
                new MemberDTO("테스트용메일"+i, "테스트용비번"+i, "테스트용번호"+i, "테스트용학교이름"+i,
                        "테스트용학번"+i, "테스트용전공"+i, "테스트용닉네임"+i, "테스트용이름"+i);
        return member;
    }

    @Test
    @Transactional
    @Rollback
    //@DisplayName("회원가입 테스트")
    public void memberSaveTest(){
        Long savedId = memberService.save(newMember(300));
        System.out.println("ㅎㅇ가입 " + savedId);
        MemberDTO memberDTO = memberService.findById(savedId);
        assertThat(newMember(300).getMail()).isEqualTo(memberDTO.getMail());
    }

    @Test
    @Transactional
    @Rollback
    //@DisplayName("로그인 테스트")
    public void loginTest(){
        String mail = "로그인용이메일";
        String password = "로그인용비번";
        String username = "로그인용이름";
        String sName = "로그인용학교";
        String phone = "로그인용전화번호";
        String sId = "로그인용학번";
        String major = "로그인용전공";
        String nick = "로그인용닉네임";
        MemberDTO memberDTO = new MemberDTO(mail, password, phone, sName, sId, major, nick, username);
        Long savedId = memberService.save(memberDTO);
        System.out.println("ㅎㅇ로그인 " + savedId);
        MemberDTO loginMemberDTO = new MemberDTO();
        loginMemberDTO.setMail(mail);
        loginMemberDTO.setPassword(password);
        MemberDTO loginResult = memberService.login(loginMemberDTO);

        assertThat(loginResult).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    //@DisplayName("회원 데이터 저장")
    public void memberSave(){
        IntStream.rangeClosed(1, 20).forEach(i -> {
            System.out.println("ㅎㅇ저장 "+memberService.save(newMember(i)));
        });

    }

    @Test
    @Transactional
    @Rollback
    //@DisplayName("회원 삭제")
    /*public void memberDeleteTest(){
        Long savedId = memberService.save(newMember(1));
        System.out.println("ㅎㅇ삭제 " + savedId);
        memberService.delete(savedId);
        assertThat(memberService.findById(savedId)).isNull();
    }
}
 */

