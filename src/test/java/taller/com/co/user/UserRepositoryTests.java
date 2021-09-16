package taller.com.co.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import taller.com.co.user.entity.Users;
import taller.com.co.user.repository.UserRepository;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByName_thenReturnListUsers(){
        Users users = Users.builder()
                .name("Nathalia")
                .lastName("Porra")
                .build();
        userRepository.save(users);

    }
}
