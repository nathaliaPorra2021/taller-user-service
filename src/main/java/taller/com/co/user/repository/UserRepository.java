package taller.com.co.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taller.com.co.user.entity.Users;

public interface UserRepository extends JpaRepository<Users,Long> { }
