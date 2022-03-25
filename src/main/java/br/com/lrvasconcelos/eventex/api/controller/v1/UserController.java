package br.com.lrvasconcelos.eventex.api.controller.v1;

import br.com.lrvasconcelos.eventex.api.dto.ResponseUserDTO;
import br.com.lrvasconcelos.eventex.api.dto.UserDTO;
import br.com.lrvasconcelos.eventex.api.dto.response.Response;
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
    public ResponseEntity<Response<ResponseUserDTO>> create(@Valid  @RequestBody UserDTO dto, BindingResult result) {

        Response<ResponseUserDTO> response = new Response<>();

        if(result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        User user = service.create(dto.convertDTOToEntity());
        response.setData(ResponseUserDTO.convertToUserResponseDTO(user));

        return new ResponseEntity<>( response, HttpStatus.CREATED);
    }

}
