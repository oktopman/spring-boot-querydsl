package me.oktop.springbootquerydsl;

import me.oktop.springbootquerydsl.study.Account;
import me.oktop.springbootquerydsl.study.AccountRepository;
import me.oktop.springbootquerydsl.study.Study;
import me.oktop.springbootquerydsl.study.StudyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//@Import(QuerydslConfiguration.class)
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    StudyRepository studyRepository;

    @Test
    void crud_테스트() {
        //given
        accountRepository.save(Account.builder()
                .name("hayun")
                .build());

        //when
        List<Account> accounts = accountRepository.findByName("hayun");

        //then
        assertThat(accounts.size()).isEqualTo(1);
        assertThat(accounts.get(0).getName()).isEqualTo("hayun");
    }

    @Test
    @Transactional
    void 수업과_수강한사람_저장_테스트() {
        //given
        Study study = Study.builder()
                .title("JPA 수업")
                .organizer("hayun")
                .build();

        Account account = Account.builder()
                .name("hayun")
                .accountNumber("U1234")
                .build();

        study.addAccount(account);

        studyRepository.save(study);
//        accountRepository.save(account);

        //when
        Optional<Study> getStudy = studyRepository.findById(1l);
        Account accounts = accountRepository.findByName("hayun").get(0);
        //then
        assertThat(getStudy.get().getAccounts().size()).isEqualTo(1);
        assertThat(getStudy.get().getAccounts().get(0).getAccountNumber()).isEqualTo("U1234");
        assertThat(getStudy.get().getAccounts().get(0).getName()).isEqualTo("hayun");
    }

    @Test
    @Transactional
    void querydsl_조인_조회_테스트() {
        //given
        Study study = Study.builder()
                .title("JPA 수업")
                .organizer("hayun")
                .build();

        Account account = Account.builder()
                .name("hayun")
                .accountNumber("U1234")
                .build();

        study.addAccount(account);
        studyRepository.save(study);

        //when
        Account getAccount = accountRepository.findByAccountEqualsName();

        //then
        assertThat(getAccount).isNotNull();
    }

}
