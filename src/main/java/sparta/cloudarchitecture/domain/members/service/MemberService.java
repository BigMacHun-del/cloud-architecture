package sparta.cloudarchitecture.domain.members.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.cloudarchitecture.domain.members.dto.CreateMemberRequest;
import sparta.cloudarchitecture.domain.members.dto.CreateMemberResponse;
import sparta.cloudarchitecture.domain.members.dto.GetMemberResponse;
import sparta.cloudarchitecture.domain.members.entity.Member;
import sparta.cloudarchitecture.domain.members.repository.MemberRespository;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRespository memberRespository;

    @Transactional
    public CreateMemberResponse create(CreateMemberRequest request) {
        Member member = Member.register(request.getName(), request.getAge(), request.getMbti());
        Member createdMember = memberRespository.save(member);
        return CreateMemberResponse.register(
                createdMember.getMemberId(),
                createdMember.getName(),
                createdMember.getAge(),
                createdMember.getMbti()
        );
    }

    @Transactional(readOnly = true)
    public GetMemberResponse getOneMember(Long memberId) {
        Member member = memberRespository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 팀원입니다.")
        );

        return GetMemberResponse.register(
                member.getMemberId(),
                member.getName(),
                member.getAge(),
                member.getMbti()
        );
    }
}
