package br.com.lrvasconcelos.eventex.api.controller.v1.user;

import br.com.lrvasconcelos.eventex.api.dto.user.ResponseUserDTO;
import br.com.lrvasconcelos.eventex.api.dto.user.UserDTO;
import br.com.lrvasconcelos.eventex.api.exceptions.ApiNotFoundException;
import br.com.lrvasconcelos.eventex.domain.entity.User;
import br.com.lrvasconcelos.eventex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
                }).orElseThrow(() -> new ApiNotFoundException("User not found."));

        return new ResponseEntity<>(ResponseUserDTO.convertToUserResponseDTO(userUpdated), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> getUsersById(@PathVariable("id") Long id) {
        ResponseUserDTO response = service.findById(id)
                .map(ResponseUserDTO::convertToUserResponseDTO)
                .orElseThrow(() -> new ApiNotFoundException("User not found."));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        service.findById(id)
                .map(user -> {
                    service.deleteUser(user);
                    return Void.TYPE;
                }).orElseThrow(() -> new ApiNotFoundException("User not found."));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findUsers(@RequestParam(required = false) Set<String> emails) {
        List<UserDTO> result = service.findByEmails(emails).stream()
                .map(User::convertEntityToDTO).collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
