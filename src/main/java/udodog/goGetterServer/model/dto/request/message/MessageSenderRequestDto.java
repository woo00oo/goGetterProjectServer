package udodog.goGetterServer.model.dto.request.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageSenderRequestDto {

    @NotNull
    private Long senderId;

    @NotNull
    private Long receiverId;

    @NotEmpty
    private String content;

}
