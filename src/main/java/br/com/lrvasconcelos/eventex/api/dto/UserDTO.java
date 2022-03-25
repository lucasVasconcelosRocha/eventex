package br.com.lrvasconcelos.eventex.api.dto;

import br.com.lrvasconcelos.eventex.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends RepresentationModel<UserDTO> {

    @NotNull(message = "Name can't be null.")
    @NotEmpty(message = "Name can't be empty.")
    @Length(max = 60, message = "Name must contain a maximum of 60 characters.")
    private String name;

    @NotNull(message = "E-mail can't be null.")
    @NotEmpty(message = "Email can't be empty.")
    @Length(max = 45, message = "E-mail must contain a maximum of 45 characters.")
    @Email(message = "Invalid email.")
    private String email;

    @NotNull(message = "Password can't be null.")
    @NotEmpty(message = "Password can't be empty.")
    @Length(min = 8, max = 12, message = "Password must contain between 8 and 12 characters.")
    private String password;

    private Integer age;
    private String avatar;

    public User convertDTOToEntity() {
        return new ModelMapper().map(this, User.class);
    }

}
