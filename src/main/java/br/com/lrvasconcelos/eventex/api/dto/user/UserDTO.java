package br.com.lrvasconcelos.eventex.api.dto.user;

import br.com.lrvasconcelos.eventex.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotNull(message = "Name cannot be null.")
    @NotEmpty(message = "Name cannot be empty.")
    @Length(max = 60, message = "Name must contain a maximum of 60 characters.")
    private String name;

    @NotNull(message = "E-mail cannot be null.")
    @NotEmpty(message = "Email cannot be empty.")
    @Length(max = 45, message = "E-mail must contain a maximum of 45 characters.")
    @Email(message = "Invalid email.")
    private String email;

    @NotNull(message = "Password cannot be null.")
    @NotEmpty(message = "Password cannot be empty.")
    @Length(min = 8, max = 12, message = "Password must contain between 8 and 12 characters.")
    private String password;

    private Integer age;
    private String avatar;

    public User convertDTOToEntity() {
        return new ModelMapper().map(this, User.class);
    }

}
