package com.estudo.designpattern.user;

import com.estudo.designpattern.exception.DatabaseException;
import com.estudo.designpattern.exception.ResourceNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<UserResponse> findAll() {
        List<User> result = repository.findAll();
        return result.stream().map(x -> new UserResponse(x)).collect(Collectors.toList());
    }

    public UserResponse findById(Long id) {
        try {
            Optional<User> obj = repository.findById(id);
            UserResponse result = new UserResponse(obj.get());
            return result;

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }

    }

    public UserResponse insert(User obj) {
        try {
            User userResult = repository.save(obj);
            UserResponse result = new UserResponse(userResult);
            return result;
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        } catch (ConstraintViolationException e) {
            throw new DatabaseException(e.getMessage());
        } catch (NullPointerException e) {
            throw e;
        }
    }

    public UserResponse update(Long id, User obj) {
        try {
            Optional<User> entity = repository.findById(id);
            updateData(entity.get(), obj);
            User userResult = repository.save(entity.get());
            UserResponse result = new UserResponse(userResult);
            return result;
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        } catch (NullPointerException e) {
            throw e;
        }catch (DateTimeParseException e) {
            throw e;
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setDob(obj.getDob());
    }
}
