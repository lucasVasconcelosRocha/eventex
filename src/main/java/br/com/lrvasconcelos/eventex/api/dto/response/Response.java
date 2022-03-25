package br.com.lrvasconcelos.eventex.api.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Response<T> {

    private T data;

    private Object errors;

    public void addErrorMsgToResponse(String msgError) {
        ResponseError error = new ResponseError()
                .setDetails(msgError)
                .setTimestamp(LocalDateTime.now());
        setErrors(error);
    }
}
