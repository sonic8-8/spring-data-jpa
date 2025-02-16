package study.springdatajpa.entity;

import lombok.Data;

@Data
public class MemberDto {

    private Long id;
    private String username;

    public MemberDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
