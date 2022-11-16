package hufs.capstone.repository;

import hufs.capstone.domain.MemberVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberVO, Integer> {
    Optional<MemberVO> findById(int id);
}