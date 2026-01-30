package sparta.cloudarchitecture.domain.members.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.cloudarchitecture.domain.members.dto.CreateMemberRequest;
import sparta.cloudarchitecture.domain.members.dto.CreateMemberResponse;
import sparta.cloudarchitecture.domain.members.dto.GetMemberResponse;
import sparta.cloudarchitecture.domain.members.entity.Member;
import sparta.cloudarchitecture.domain.members.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/api/members")
    public ResponseEntity<CreateMemberResponse> createMember(@RequestBody CreateMemberRequest request){
        CreateMemberResponse response = memberService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/api/members/{memberId}")
    public ResponseEntity<GetMemberResponse> getMember(@PathVariable Long memberId){
        GetMemberResponse response = memberService.getOneMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
