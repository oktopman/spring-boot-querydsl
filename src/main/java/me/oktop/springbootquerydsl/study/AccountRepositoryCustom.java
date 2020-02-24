package me.oktop.springbootquerydsl.study;

import java.util.List;
import java.util.Optional;

public interface AccountRepositoryCustom {

    List<Account> findByName(String name);
    Account findByAccountEqualsName();
}
