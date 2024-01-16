package bolu.ajileye.authfinal.repository;

import bolu.ajileye.authfinal.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, String> {
}
