package sparta.cloudarchitecture.domain.members.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String mbti;

    @Column(name = "profile_image_key")
    private String profileImageKey;

    @Column(name = "url")
    private String url;

    public static Member register(String name, int age, String mbti) {
        Member member = new Member();
        member.name = name;
        member.age = age;
        member.mbti = mbti;

        return member;
    }

    // 이미지 키 업데이트 메서드
    public void updateProfileImage(String imageKey) {
        this.profileImageKey = imageKey;
    }

    // 이미지 url 업데이트 메서드
    public void updateUrl(String url) {
        this.url = url;
    }
}
