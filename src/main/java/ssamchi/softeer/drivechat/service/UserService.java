package ssamchi.softeer.drivechat.service;

import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssamchi.softeer.drivechat.domain.*;
import ssamchi.softeer.drivechat.dto.request.RequestUserRegisterDto;
import ssamchi.softeer.drivechat.dto.response.ResponseUserRegisterDto;
import ssamchi.softeer.drivechat.exception.BusinessException;
import ssamchi.softeer.drivechat.exception.Error;
import ssamchi.softeer.drivechat.repository.*;
import ssamchi.softeer.drivechat.utils.HeaderUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public ResponseUserRegisterDto registerUser(RequestUserRegisterDto requestUserRegisterDto) {
        String username = requestUserRegisterDto.getUsername();

        if (username == null)
            throw BusinessException.of(Error.INTERNAL_SERVER_ERROR);

        Optional<User> optionalUser = userRepository.findByUserName(username);
        if (optionalUser.isPresent()) {
            return ResponseUserRegisterDto.builder()
                .success(false)
                .userId(optionalUser.get().getUserId())
                .build();
        } else {
            User newUser = userRepository.save(User.builder()
                .userName(username)
                .build()
            );

            return ResponseUserRegisterDto.builder()
                .success(true)
                .userId(newUser.getUserId())
                .build();
        }
    }
}
