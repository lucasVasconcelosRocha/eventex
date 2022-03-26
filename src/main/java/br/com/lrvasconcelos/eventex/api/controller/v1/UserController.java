package br.com.lrvasconcelos.eventex.api.controller.v1;

import br.com.lrvasconcelos.eventex.api.dto.ResponseUserDTO;
import br.com.lrvasconcelos.eventex.api.dto.UserDTO;
import br.com.lrvasconcelos.eventex.domain.entity.User;
import br.com.lrvasconcelos.eventex.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/eventex/users")
public class UserController {

    @Autowired
    private UserServiceImpl service;

    @PostMapping
    public ResponseEntity<ResponseUserDTO> create(@RequestBody @Valid UserDTO dto) {

        User user = service.create(dto.convertDTOToEntity());

        return new ResponseEntity<>( ResponseUserDTO.convertToUserResponseDTO(user), HttpStatus.CREATED);
    }

}
