//package com.estudo.designpattern.user;
//
//import com.estudo.designpattern.exception.DatabaseException;
//import com.estudo.designpattern.exception.ResourceNotFoundException;
//import org.h2.engine.Database;
//import org.hibernate.exception.ConstraintViolationException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.test.annotation.DirtiesContext;
//
//import javax.persistence.EntityNotFoundException;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//public class UserServiceTest {
//
//    UserRepository userRepositoryMock;
//    UserService userService;
//    User user;
//
//    @BeforeEach
//    public void setup() {
//        // Mock
//        userRepositoryMock = mock(UserRepository.class);
//        user = UserBuilder.getMockedInstance().build();
//
//        // Dependence injection
//        userService = new UserService(userRepositoryMock);
//    }
//
//    @Test
//    public void should_call_find_all_user_repository_once() {
//        userService.findAll();
//        verify(userRepositoryMock, times(1)).findAll();
//        verifyNoMoreInteractions(userRepositoryMock);
//    }
//
//    @Test
//    public void should_call_find_by_user_repository_once() {
//        when(userRepositoryMock.findById(any())).thenReturn(java.util.Optional.ofNullable(user));
//
//        UserResponse user = userService.findById(any());
//
//        verify(userRepositoryMock, times(1)).findById(any());
//        verifyNoMoreInteractions(userRepositoryMock);
//    }
//
//    @Test
//    public void should_throw_resource_not_found_exception_find_by_id() {
//        when(userRepositoryMock.findById(any())).thenThrow(EntityNotFoundException.class);
//        assertThrows(ResourceNotFoundException.class, () -> userService.findById(any()));
//    }
//
//    @Test
//    @DirtiesContext // it is avoiding save something at DB
//    public void should_call_save_user_repository() {
//        when(userRepositoryMock.save(user)).thenReturn(user);
//        UserResponse userResponse = userService.insert(user);
//        verify(userRepositoryMock, times(1)).save(user);
//        verifyNoMoreInteractions(userRepositoryMock);
//    }
//
//    @Test
//    @DirtiesContext // it is avoiding save something at DB
//    public void should_throw_database_exception_insert() {
//        when(userRepositoryMock.save(any()))
//                .thenThrow(DataIntegrityViolationException.class)
//                        .thenThrow(ConstraintViolationException.class);
//        assertThrows(DatabaseException.class, () -> userService.insert(any()));
//        assertThrows(DatabaseException.class, () -> userService.insert(any()));
//    }
//
//    @Test
//    @DirtiesContext // it is avoiding save something at DB
//    public void should_throw_null_pointer_exception_insert() {
//        when(userRepositoryMock.save(any()))
//                .thenThrow(NullPointerException.class);
//        assertThrows(NullPointerException.class, () -> userService.insert(any()));
//    }
//
//}
