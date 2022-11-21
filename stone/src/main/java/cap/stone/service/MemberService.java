package cap.stone.service;

import cap.stone.dto.BlackMemberDTO;
import cap.stone.dto.MemberDTO;
import cap.stone.entity.BlackMemberEntity;
import cap.stone.entity.MemberEntity;
import cap.stone.repository.BlackMemberRepository;
import cap.stone.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BlackMemberRepository blackMemberRepository;

    public Long save(MemberDTO memberDTO){
        try{
            MemberEntity memberEntity = MemberEntity.toSaveEntity(memberDTO);
            Long savedId = memberRepository.save(memberEntity).getId();
            System.out.println(savedId);
            return savedId;
        }catch (Exception e){
            System.out.println(e);
            return -1L;
        }
    }
    public List<MemberDTO> findAll(){
        MemberDTO memberDTO = new MemberDTO();
        if(memberDTO.getGrade() == String.valueOf(1)){
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for(MemberEntity member: memberEntityList){
            memberDTOList.add(MemberDTO.toMemberDTO(member));
        }
        return memberDTOList;
        } else{
            return null;
        }
    }
    public MemberDTO find(String mail, String password){
        Optional<MemberEntity> entity = memberRepository.findByMailAndPassword(mail, password);
        return entity.map(MemberDTO::toMemberDTO).orElse(null);
    }
    @Transactional
    public void delete(String mail, String password) {
        System.out.println(memberRepository.deleteByMailAndPassword(mail, password));
    }
    public Long update(MemberDTO memberDTO){
        try{
            Optional<MemberEntity> fbm = memberRepository.findByMail(memberDTO.getMail());
            if(fbm.isEmpty()){
                throw new Exception("존재하지않음");
            }
            MemberEntity memberEntity = MemberEntity.toUpdateEntity(memberDTO);
            memberEntity.setId(fbm.get().getId());
            Long savedId = memberRepository.save(memberEntity).getId();
            return savedId;
        }catch (Exception e){
            System.out.println(e);
            return -1L;
        }
    }
    public int isSame(MemberDTO dto){
        String nick = dto.getNick();
        Optional<MemberEntity> byNick = memberRepository.findByNick(nick);
        if(byNick.isEmpty()){
            return 1;
        }else{
            return 0;
        }
    }
    public MemberDTO findByNick(String nick){
        Optional<MemberEntity>optionalMemberEntity = memberRepository.findByNick(nick);
        if(optionalMemberEntity.isPresent()){
            MemberEntity memberEntity = optionalMemberEntity.get();
            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            return memberDTO;
        } else{
            return null;
        }
    }
    public MemberDTO findById(Long id){
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if(optionalMemberEntity.isPresent()){
            MemberEntity memberEntity = optionalMemberEntity.get();
            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            return memberDTO;
        } else{
            return null;
        }
    }
    public Long updateMemberScore(MemberDTO memberDTO, int mannerscore){
        try{
            Optional<MemberEntity> fbm = memberRepository.findByMail(memberDTO.getMail());
            if(fbm.isEmpty()){
                throw new Exception("존재하지않음");
            }
            MemberEntity memberEntity = fbm.get();
            memberEntity.setMScore(mannerscore);
            Long savedId = memberRepository.save(memberEntity).getId();
            if(mannerscore <= 25){
                System.out.println("블랙리스트추가"); //추가 작업 필요

            }
            return savedId;
        }catch (Exception e){
            System.out.println(e);
            return -1L;
        }
    }

    public void BlackController(BlackMemberDTO blackMemberDTO, Long memberId){
        if(blackMemberDTO.getBlackscore() > 25){
            blackMemberRepository.deleteById(blackMemberDTO.getMemberId());
        } else {

        }
    }

    /*public MemberDTO login(MemberDTO memberDTO){
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMail(memberDTO.getMail());
        if(optionalMemberEntity.isPresent()){
            MemberEntity loginEntity = optionalMemberEntity.get();
            if(loginEntity.getPassword().equals(memberDTO.getPassword())){
                return MemberDTO.toMemberDTO(loginEntity);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    public MemberDTO findByNick(String nick){
        Optional<MemberEntity>optionalMemberEntity = memberRepository.findByNick(nick);
        if(optionalMemberEntity.isPresent()){
            MemberEntity memberEntity = optionalMemberEntity.get();
            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            return memberDTO;
        } else{
            return null;
        }
    }
    public MemberDTO findByMail(String mail){
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMail(mail);
        System.out.println(optionalMemberEntity.get());
        if(optionalMemberEntity.isPresent()){
            MemberEntity memberEntity = optionalMemberEntity.get();
            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            return memberDTO;
        } else{
            return null;
        }
    }

     */
}
