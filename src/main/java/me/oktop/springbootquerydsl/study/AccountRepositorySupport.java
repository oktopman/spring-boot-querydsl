package me.oktop.springbootquerydsl.study;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static me.oktop.springbootquerydsl.study.QAccount.account;


@Repository
public class AccountRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public AccountRepositorySupport(JPAQueryFactory queryFactory) {
        super(Account.class);
        this.queryFactory = queryFactory;

    }

    public List<Account> findByName(String name) {
        return queryFactory
                .selectFrom(account)
                .where(account.name.eq(name))
                .fetch();
    }
}
