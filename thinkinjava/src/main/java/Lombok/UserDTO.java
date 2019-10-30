package Lombok;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {

    @NonNull
    private String username;

    private int age;

}
