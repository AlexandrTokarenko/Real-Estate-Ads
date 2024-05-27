package com.example.realestateads.dto;

import com.example.realestateads.valid.Password;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRestoreDTO {

    @NotBlank(message = "Введіть електронну пошту")
    private String email;

    @NotBlank(message = "Введіть пароль")
    @Password(message = "Пароль повинен містити латинські літери та цифри")
    @Length(min = 8,max = 15, message = "Пароль повинен мати довжину від 8 до 15 символів включно")
    private String password;
}
