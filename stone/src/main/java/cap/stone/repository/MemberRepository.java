package cap.stone.repository;

import cap.stone.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByMail(String mail);
    Optional<MemberEntity> deleteByMailAndPassword(String mail, String password);
    Optional<MemberEntity> findByNick(String nick);
    Optional<MemberEntity> findByMailAndPassword(String mail, String password);
    Optional<MemberEntity> findByIdAndUsername(Long id, String username);
}
