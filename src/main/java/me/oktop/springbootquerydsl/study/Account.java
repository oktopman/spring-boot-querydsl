package me.oktop.springbootquerydsl.study;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String accountNumber;

    @ManyToOne
    private Study study;

    public void setStudy(Study study) {
        this.study = study;
    }

    @Builder
    public Account(String name, String accountNumber) {
        this.name = name;
        this.accountNumber = accountNumber;
    }

}
