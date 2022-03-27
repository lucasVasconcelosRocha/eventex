package br.com.lrvasconcelos.eventex.api.controller.v1;

import br.com.lrvasconcelos.eventex.api.dto.ResponseUserDTO;
import br.com.lrvasconcelos.eventex.api.dto.UserDTO;
import br.com.lrvasconcelos.eventex.api.exceptions.UserNotFoundException;
import br.com.lrvasconcelos.eventex.domain.entity.User;
import br.com.lrvasconcelos.eventex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/eventex/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<ResponseUserDTO> create(@RequestBody @Valid UserDTO dto) {

        User user = service.create(dto.convertDTOToEntity());

        return new ResponseEntity<>( ResponseUserDTO.convertToUserResponseDTO(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> update(@RequestBody UserDTO dto, @PathVariable("id") Long id) {
        User userUpdated = service.findById(id)
                .map(userExists -> {
                    User user = dto.convertDTOToEntity();
                    user.setId(userExists.getId());
                    return service.create(user);
                }).orElseThrow(() -> new UserNotFoundException("User not found."));

        return new ResponseEntity<>(ResponseUserDTO.convertToUserResponseDTO(userUpdated), HttpStatus.OK);

    }

}
