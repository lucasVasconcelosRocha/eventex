package br.com.lrvasconcelos.eventex.api.dto;


import br.com.lrvasconcelos.eventex.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseUserDTO {

    private Long id;
    private String name;
    private String email;
    private Integer age;
    private Boolean isAdmin;
    private String avatar;

    public static ResponseUserDTO convertToUserResponseDTO(User user) {
        return new ModelMapper().map(user, ResponseUserDTO.class);
    }
}
