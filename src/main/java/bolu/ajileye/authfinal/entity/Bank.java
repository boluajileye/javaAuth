package bolu.ajileye.authfinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String uid;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private String iban;

    @Column(nullable = false)
    private String bankName;

    @Column(nullable = false)
    private String routingNumber;

    @Column(nullable = false)
    private String swiftBic;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "bank_user_fk"))
    @JsonIgnore
    private User user;
}
