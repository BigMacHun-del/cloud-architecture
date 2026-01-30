package sparta.cloudarchitecture.domain.members.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateMemberResponse {
    private final Long memberId;
    private final String name;
    private final int age;
    private final String mbti;

    private CreateMemberResponse(Long memberId, String name, int age, String mbti) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.mbti = mbti;
    }

    public static CreateMemberResponse register(Long memberId, String memberName, int age, String mbti) {
        return new CreateMemberResponse(memberId, memberName, age, mbti);
    }

}
