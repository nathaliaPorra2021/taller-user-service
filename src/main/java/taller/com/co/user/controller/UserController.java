package taller.com.co.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import taller.com.co.user.dto.UsersDTO;
import taller.com.co.user.entity.Users;
import taller.com.co.user.service.UserService;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    private UserService userService;


    private ModelMapper modelMapper = new ModelMapper();


    @GetMapping
    public ResponseEntity<List<UsersDTO>> listUsers(){
        List<Users> users = userService.listAllUser();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));

    }

    @GetMapping(value = "/{idUser}")
    public ResponseEntity<UsersDTO> getUser(@PathVariable("idUser") Long idUser) {
        UsersDTO user =  convertToDto(userService.getUserById(idUser));
        if (null==user){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UsersDTO> createUser(@Valid @RequestBody UsersDTO user, BindingResult result) throws ParseException {
        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage((result)));
        }
        Users userCreate = userService.createUser(convertToEntity(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(userCreate));
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("Eliminado con éxito");
        }catch(Exception e){
            return ResponseEntity.ok("No pudo eliminarse");
        }

    }

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String,String> error = new HashMap<>();
                    error.put(err.getField(),err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";

        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return  jsonString;
    }

    private UsersDTO convertToDto(Users user) {
       return modelMapper.map(user, UsersDTO.class);
    }

    private Users convertToEntity(UsersDTO usersDTO) throws ParseException {
        return modelMapper.map(usersDTO, Users.class);

    }


}
