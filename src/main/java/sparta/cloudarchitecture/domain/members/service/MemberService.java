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

import java.net.URL;

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



    // 이미지 키 업데이트
    @Transactional
    public void updateProfileImage(Long memberId, String imageKey) {
        Member member = memberRespository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        member.updateProfileImage(imageKey);
    }


    // 이미지 키 조회
    @Transactional(readOnly = true)
    public String getProfileImageKey(Long memberId) {
        Member member = memberRespository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        String profileImageKey = member.getProfileImageKey();

        if (profileImageKey == null || profileImageKey.isEmpty()) {
            throw new IllegalArgumentException("프로필 이미지가 없습니다.");
        }

        return profileImageKey;
    }

    @Transactional
    public void updateUrl(Long memberId, URL url) {
        Member member = memberRespository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        member.updateUrl(url.toString());
    }
}
