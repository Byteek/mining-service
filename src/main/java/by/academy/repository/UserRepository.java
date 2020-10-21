package by.academy.repository;


import by.academy.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser,String> {

    AppUser findByUsername(String username);

    AppUser findByEmail(String username);
}
