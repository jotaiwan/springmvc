package com.book.Repository;

import com.book.data.entity.Member;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jotaiwan on 22/07/2017.
 */
@Repository
public class MemberRepository extends AbstractRepository {

    public void saveMember(Member member) {
        persist(member);
    }

    @SuppressWarnings("unchecked")
    public List<Member> findAll() {
        Criteria criteria = getSession().createCriteria(Member.class);
        return (List<Member>) criteria.list();
    }
}
