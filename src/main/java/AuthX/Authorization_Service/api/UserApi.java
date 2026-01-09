package AuthX.Authorization_Service.api;

import AuthX.Authorization_Service.dto.UserResponseDto;
import AuthX.Authorization_Service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users/v1")
public class UserApi {

    private final UserService service;

    @GetMapping
    public List<UserResponseDto> get(){
        return service.get();
    }
}
