package LumExpress.Data.Models;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;
    private String userEmail;
    private LocalDateTime createdAt;
    private  LocalDateTime expiresAt = createdAt.plusMinutes(5);
}
