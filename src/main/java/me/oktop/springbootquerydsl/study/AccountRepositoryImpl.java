package me.oktop.springbootquerydsl.study;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static me.oktop.springbootquerydsl.study.QAccount.account;
import static me.oktop.springbootquerydsl.study.QStudy.study;

@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Account> findByName(String name) {
        return queryFactory
                .selectFrom(account)
                .where(account.name.eq(name))
                .fetch();
    }

    @Override
    public Account findByAccountEqualsName() {
        return queryFactory
                .selectFrom(account)
                .innerJoin(study).on(account.name.eq(study.organizer))
                .fetchOne();
    }
}
