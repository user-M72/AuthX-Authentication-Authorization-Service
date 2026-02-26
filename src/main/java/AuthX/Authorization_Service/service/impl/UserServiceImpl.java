package AuthX.Authorization_Service.service.impl;

import AuthX.Authorization_Service.dto.UserRequestDto;
import AuthX.Authorization_Service.dto.UserResponseDto;
import AuthX.Authorization_Service.entity.Role;
import AuthX.Authorization_Service.entity.User;
import AuthX.Authorization_Service.mapper.UserMapper;
import AuthX.Authorization_Service.repository.UserRepository;
import AuthX.Authorization_Service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponseDto> get() {
        return userRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public UserResponseDto getById(UUID id) {
        User user =  userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        return mapper.toDto(user);
    }

     @Override
    public UserResponseDto create(UserRequestDto request) {
        User user = mapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.password()));

        if (user.getRole() == null){
            user.setRole(Role.User);
        }


        User saveUser = userRepository.save(user);

        return mapper.toDto(saveUser);
    }

    @Override
    public UserResponseDto getByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));
        return mapper.toDto(user);
    }

    @Override
    public UserResponseDto getCurrentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        return getByEmail(email);
    }
}
