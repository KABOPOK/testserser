package kabopok.server.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue
  @Column(name = "userid", columnDefinition = "UUID")
  private UUID userID;

  @Column(name = "number", unique = true, nullable = false, length = 15)
  private String number;

  @Column(name = "password", nullable = false, length = 30)
  private String password;

  @Column(name = "username", nullable = false, length = 30)
  private String userName;

  @Column(name = "photourl", length = 100)
  private String photoUrl;

  @Column(name = "location", length = 30)
  private String location;

  @Column(name = "telegramid", length = 30)
  private String telegramID;
}