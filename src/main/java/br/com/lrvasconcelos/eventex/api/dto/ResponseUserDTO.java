package br.com.lrvasconcelos.eventex.api.dto;


import br.com.lrvasconcelos.eventex.domain.entity.User;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseUserDTO extends RepresentationModel<ResponseUserDTO> {

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
