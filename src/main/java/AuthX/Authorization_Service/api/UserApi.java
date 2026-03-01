package AuthX.Authorization_Service.api;

import AuthX.Authorization_Service.config.CurrentUser;
import AuthX.Authorization_Service.dto.UserRequestDto;
import AuthX.Authorization_Service.dto.UserResponseDto;
import AuthX.Authorization_Service.entity.Role;
import AuthX.Authorization_Service.entity.User;
import AuthX.Authorization_Service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/v1")
public class UserApi {

    private final UserService service;

    @GetMapping
    public List<UserResponseDto> get() {
        return service.get();
    }

    @GetMapping("/{userId}")
    public UserResponseDto getById(@PathVariable("userId") UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> created(@RequestBody UserRequestDto request) {
        UserResponseDto created = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @GetMapping("/me")
    public UserResponseDto me(@CurrentUser User user) {
        return ResponseEntity.ok(service.toDto(user)).getBody();
    }

    @GetMapping("/statuses")
    public Role[] getStatuses() {
        return Role.values();
    }

}
