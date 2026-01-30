package sparta.cloudarchitecture.domain.members.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.cloudarchitecture.domain.members.entity.Member;

public interface MemberRespository extends JpaRepository<Member,Long> {
}
