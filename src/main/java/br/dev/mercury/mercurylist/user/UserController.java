package br.dev.mercury.mercurylist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.dev.mercury.mercurylist.IUserRepository;



@RestController
@RequestMapping("/users")
public class UserController {
    /**
     * String (testo)
     * Integer (int) Numeros inteiros 
     * Double (Double) Numeros 0.0000
     * Float (Float) NUmeros 0.000
     * Char (A C )
     * DAte (data)
     * Void sem retorno 
     */
    //@RequestBody é uma requisição o boby, ou seja, no corpo da requisição
    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
     public ResponseEntity create(@RequestBody UserModel userModel){
        var user =this.userRepository.findByUsername(userModel.getUsername());

         
        if(user != null){
            System.out.println("Usuário já cadatradado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já cadatradado");

        }

        var passwordHashred = BCrypt.withDefaults()
                .hashToString(12, userModel.getPassword().toCharArray()); 
        
                userModel.setPassword(passwordHashred);


        var userCreated =this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
     }


}
