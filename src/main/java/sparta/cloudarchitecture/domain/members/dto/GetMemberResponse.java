package sparta.cloudarchitecture.domain.members.dto;

import lombok.Getter;

@Getter
public class GetMemberResponse {
    private final Long memberId;
    private final String name;
    private final int age;
    private final String mbti;

    private GetMemberResponse(Long memberId, String name, int age, String mbti) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.mbti = mbti;
    }

    public static GetMemberResponse register(Long memberId, String memberName, int age, String mbti) {
        return new GetMemberResponse(memberId, memberName, age, mbti);
    }
}
