package uz.jvh.telegramfrontend.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiException extends RuntimeException{
    private int status;
    private String error;
    private LocalDateTime timestamp;
    private String path;
}
