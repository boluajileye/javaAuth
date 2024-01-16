package bolu.ajileye.authfinal.service.implementation;

import bolu.ajileye.authfinal.dto.response.BankResponse;
import bolu.ajileye.authfinal.dto.response.UserResponse;
import bolu.ajileye.authfinal.entity.Bank;
import bolu.ajileye.authfinal.entity.User;
import bolu.ajileye.authfinal.repository.UserRepository;
import bolu.ajileye.authfinal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {


    private final UserRepository userRepository;

    @Override
    public UserResponse getUser() throws IllegalStateException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .about(user.getAbout())
                .role(user.getRole())
                .bank(preprareBankResponse(user.getBank()))
                .build();

    }

    private BankResponse preprareBankResponse(Bank bank) {
        return BankResponse.builder()
                .id(bank.getId())
                .uid(bank.getUid())
                .bankName(bank.getBankName())
                .accountNumber(bank.getAccountNumber())
                .routingNumber(bank.getRoutingNumber())
                .iban(bank.getIban())
                .swiftBic(bank.getSwiftBic())
                .build();
    }

}
