package br.dev.mercury.mercurylist;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.mercury.mercurylist.user.UserModel;



public interface IUserRepository extends JpaRepository<UserModel, UUID>{
   UserModel findByUsername(String username);
    
}
