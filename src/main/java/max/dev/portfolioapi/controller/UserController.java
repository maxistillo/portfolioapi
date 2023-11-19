package max.dev.portfolioapi.controller;

import max.dev.portfolioapi.dto.AuthenticationRequest;
import max.dev.portfolioapi.dto.UserDTO;
import max.dev.portfolioapi.dto.UserRequest;
import max.dev.portfolioapi.model.UserModel;
import max.dev.portfolioapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping("/current")
    public ResponseEntity<String> currentUser(){
        return ResponseEntity.ok(userService.getCurrentUser());
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @PatchMapping("/password/{id}")
    public ResponseEntity<String> changePassword(@PathVariable Long id,@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(userService.changePassword(id, request));
    }



}
