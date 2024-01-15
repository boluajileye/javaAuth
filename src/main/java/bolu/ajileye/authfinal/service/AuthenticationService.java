package bolu.ajileye.authfinal.service;

import bolu.ajileye.authfinal.dto.request.CarStoreRequest;
import bolu.ajileye.authfinal.dto.request.CourseStoreRequest;
import bolu.ajileye.authfinal.dto.request.LoginRequest;
import bolu.ajileye.authfinal.dto.request.RegisterRequest;
import bolu.ajileye.authfinal.dto.response.AuthenticationResponse;
import bolu.ajileye.authfinal.dto.response.CarResponse;
import bolu.ajileye.authfinal.dto.response.CourseResponse;
import bolu.ajileye.authfinal.dto.response.UserResponse;
import bolu.ajileye.authfinal.entity.Course;
import bolu.ajileye.authfinal.exception.ResourceNotFoundException;
import jakarta.validation.Valid;

import java.util.Optional;

public interface AuthenticationService {
    UserResponse register(RegisterRequest registerRequest);


    AuthenticationResponse login(LoginRequest request) throws Exception;

    void addCar(CarStoreRequest request) throws Exception;

    UserResponse getCar() throws Exception;

    CarResponse getSingleCar(String id) throws ResourceNotFoundException;

    CourseResponse addCourse(@Valid CourseStoreRequest request) throws Exception;

    Optional<Course> getSingleCourse(String id);

    UserResponse getCourse() throws Exception;
}
