package study.springdatajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.springdatajpa.entity.Member;
import study.springdatajpa.entity.MemberDto;
import study.springdatajpa.repository.MemberRepository;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    // 도메인 클래스 컨버터 적용전
    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    // 도메인 클래스 컨버터 적용
    // 트랜잭션이 적용되지 않는 범위에서 가져온 도메인이라 단순 조회용으로만 사용
    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Member member) {
        return member.getUsername();
    }

    // Pageable 객체는 페이징 처리 정보를 담은 객체임
    // 반환 값은 DTO 로 반드시 반환하기
    @GetMapping("/members")
    public Page<MemberDto> list(Pageable pageable) {
        Page<Member> page = memberRepository.findAll(pageable);
        Page<MemberDto> pageDto = page.map(member -> new MemberDto(member.getId(), member.getUsername()));
        return pageDto;
    }



}
