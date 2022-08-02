package com.example.demo.service;

import com.example.demo.dao.entity.UserEntity;
import com.example.demo.dao.repository.UserRepository;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.exception.UserAlreadyExist;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity create(@Valid @Email String email, String password, String firstName, String lastName, String info) {
        validateEmailNotExist(email);
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setEncodedPassword(password + " imagine it's encoded");
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setInfo(info);
        return userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> getAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override
    public UserEntity getById(long id) {
        return userRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException(UserEntity.class, Long.toString(id))
        );
    }

    @Override
    public List<UserEntity> findByIds(Set<Long> ids) {
        return StreamSupport.stream(userRepository.findAllById(ids).spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override
    public UserEntity update(long id, String firstName, String lastName, String info) {
        UserEntity user = getById(id);

        if (!Strings.isBlank(firstName)) {
            user.setFirstName(firstName);
        }

        if (!Strings.isBlank(lastName)) {
            user.setLastName(lastName);
        }

        if (!Strings.isBlank(info)) {
            user.setInfo(info);
        }
        return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        UserEntity entity = getById(id);
        userRepository.delete(entity);
    }

    private void validateEmailNotExist(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExist(email);
        }
    }
}
