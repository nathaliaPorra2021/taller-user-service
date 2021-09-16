package taller.com.co.user;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import taller.com.co.user.entity.Users;
import taller.com.co.user.repository.UserRepository;
import taller.com.co.user.service.UserService;
import taller.com.co.user.service.UserServiceImpl;

import java.util.Optional;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public  void setup(){
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository);
        Users user = Users.builder()
                .id(1L)
                .name("Nathalia")
                .lastName("Porra")
                .build();

        Mockito.when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

    }


}
