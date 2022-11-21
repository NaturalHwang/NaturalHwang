package cap.stone.entity;

import cap.stone.dto.BlackMemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "black")
public class BlackMemberEntity {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, name = "memberId")
    private Long memberId;  //이름
    @Column(name = "blackscore")
    private int blackscore; //주소

    public static BlackMemberEntity toSaveblack(BlackMemberDTO blackMemberDTO){
        BlackMemberEntity blackMember = new BlackMemberEntity();
        blackMember.setMemberId(blackMemberDTO.getMemberId());
        blackMember.setBlackscore(blackMemberDTO.getBlackscore());
        return blackMember;
    }

    public static BlackMemberEntity toUpdateBlack(BlackMemberDTO blackMemberDTO){
        BlackMemberEntity blackMember = BlackMemberEntity.toSaveblack(blackMemberDTO);
        blackMember.setId(blackMemberDTO.getMemberId());
        return blackMember;
    }
}
