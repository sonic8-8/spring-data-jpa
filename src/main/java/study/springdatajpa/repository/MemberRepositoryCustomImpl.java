package study.springdatajpa.repository;

import jakarta.persistence.EntityManager;
import study.springdatajpa.entity.Member;

import java.util.List;

public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private EntityManager em;

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m")
                .getResultList();
    }
}
