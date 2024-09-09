package uz.jvh.telegramfrontend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uz.jvh.telegramfrontend.dto.request.LoginDTO;
import uz.jvh.telegramfrontend.dto.request.UserCreateDto;
import uz.jvh.telegramfrontend.dto.response.BaseResponse;
import uz.jvh.telegramfrontend.dto.response.UserResponse;
import uz.jvh.telegramfrontend.model.UserEntity;
import uz.jvh.telegramfrontend.util.RequestSender;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${api-host}")
    private String apiHost;

    public String save(UserCreateDto dto) {
        String url = apiHost + "/auth/register";
        return RequestSender.post(url, dto, BaseResponse.class).getMessage();
    }

    public UserResponse signIn(LoginDTO loginDTO) {
        String url = apiHost + "/auth/login";
        return RequestSender.post(url, loginDTO, UserResponse.class);
    }

    public List<UserEntity> getAll() {
        String url = apiHost + "/user";
        return RequestSender.get(url, List.class);
    }

}
