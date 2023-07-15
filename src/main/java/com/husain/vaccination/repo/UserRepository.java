package com.husain.vaccination.repo;

import com.husain.vaccination.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmailId(String emailId);
//    User findByEmailId(String str);
}
