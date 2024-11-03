package kabopok.server.controllers;

import generated.kabopok.server.api.UserApi;
import generated.kabopok.server.api.model.IdDTO;
import generated.kabopok.server.api.model.LoginDataDTO;
import generated.kabopok.server.api.model.UserDTO;
import kabopok.server.entities.User;
import kabopok.server.mappers.UserMapper;
import kabopok.server.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

  private final UserService userService;
  private final UserMapper userMapper;

  @Override
  public UserDTO getUser(LoginDataDTO loginDataDTO) {
    User user = userService.findByNumber(loginDataDTO);
    return userMapper.map(user);
  }

  @PostMapping("/test")
  public ResponseEntity<String> testEndpoint(@RequestBody String body) {
    return ResponseEntity.ok(body);
  }

  @Override
  public IdDTO saveUser(UserDTO userDTO) {
    User user = userMapper.map(userDTO);
    userService.save(user);
    return new IdDTO(user.getUserID());
  }


}
