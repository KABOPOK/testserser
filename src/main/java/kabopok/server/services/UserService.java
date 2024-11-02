package kabopok.server.services;

import generated.kabopok.server.api.model.LoginDataDTO;
import generated.kabopok.server.api.model.UserDTO;
import kabopok.server.entities.User;
import kabopok.server.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final JdbcTemplate jdbcTemplate;

//  private void createUsersTable() {
//    String sql = "CREATE TABLE IF NOT EXISTS users1 (" +
//            "userid UUID PRIMARY KEY NOT NULL," +
//            "number VARCHAR(15) UNIQUE NOT NULL," +
//            "password VARCHAR(30) NOT NULL," +
//            "username VARCHAR(30) NOT NULL," +
//            "photourl VARCHAR(100)," +
//            "location VARCHAR(30)," +
//            "telegramid VARCHAR(30)" +
//            ")";
//    jdbcTemplate.execute(sql);
//  }
  public UUID save(User user){
    Optional<User> existingUser = userRepository.findByNumber(user.getNumber());
    if (existingUser.isPresent()) {
      throw new RuntimeException("User with this number already exists");
    }

    UUID userID = UUID.randomUUID();
    user.setUserID(userID);
    userRepository.save(user);
    return userID;
  }

  public User findByNumber(LoginDataDTO loginDataDTO){
    User current = userRepository.findByNumber(loginDataDTO.getNumber()).orElseThrow(
            (()-> new RuntimeException("User not found"))
    );
    if(!current.getPassword().equals(loginDataDTO.getPassword())){
      throw new RuntimeException("Wrong answer");
    }
    return current;
  }

}
