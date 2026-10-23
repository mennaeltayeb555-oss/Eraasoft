package org.example.businesslogic.library.repository;

import org.example.businesslogic.common.repository.InMemoryRepository;
import org.example.businesslogic.library.model.Member;
import org.example.businesslogic.library.model.MemberType;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository extends InMemoryRepository<Member> {

    public MemberRepository() {
        save(new Member(null, "Ali Mostafa", MemberType.NORMAL));
        save(new Member(null, "Rana Ashraf", MemberType.PREMIUM));
        save(new Member(null, "Hossam Gamal", MemberType.NORMAL));
    }
}
