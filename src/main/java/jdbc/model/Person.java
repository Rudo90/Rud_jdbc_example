package jdbc.model;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder

public class Person {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;

}
