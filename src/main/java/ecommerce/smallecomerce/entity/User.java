package ecommerce.smallecomerce.entity;

import ecommerce.smallecomerce.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank(message = "name is require")
    private String username;

    @NotBlank(message = "password is require")
    @Size(min = 3, message = "At least 3")
    private String password;

    @Column(unique = true)
    @NotBlank(message = "password is require")
    private String email;

    private UserRole role;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user",fetch = FetchType.LAZY)
    private Address address;

    @Column(name = "created_at")
    private final LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;


}
