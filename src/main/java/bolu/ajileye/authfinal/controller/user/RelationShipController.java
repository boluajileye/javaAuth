package bolu.ajileye.authfinal.controller.user;

import bolu.ajileye.authfinal.dto.request.CarStoreRequest;
import bolu.ajileye.authfinal.dto.request.CourseStoreRequest;
import bolu.ajileye.authfinal.dto.response.AuthenticationResponse;
import bolu.ajileye.authfinal.dto.response.CarResponse;
import bolu.ajileye.authfinal.dto.response.CourseResponse;
import bolu.ajileye.authfinal.dto.response.UserResponse;
import bolu.ajileye.authfinal.entity.Course;
import bolu.ajileye.authfinal.exception.ResourceNotFoundException;
import bolu.ajileye.authfinal.service.AuthenticationService;
import bolu.ajileye.authfinal.service.RelationService;
import bolu.ajileye.authfinal.service.implementation.RelationShipService;
import bolu.ajileye.authfinal.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class RelationShipController {


    private final AuthenticationService authenticationService;

    @PostMapping("/api/car")
    public ResponseEntity<ApiResponse> addCar(@Valid @RequestBody CarStoreRequest request) throws Exception {
        authenticationService.addCar(request);
        return ResponseEntity.ok( ).body(
                ApiResponse.make(HttpStatus.CREATED,
                        "User Registration Success",
                        Optional.ofNullable("null")));
    }


    @GetMapping("/api/car")
    public ResponseEntity<ApiResponse> getCar() throws Exception {
        UserResponse user = authenticationService.getCar();
        return ResponseEntity.ok( ).body(
                ApiResponse.make(HttpStatus.CREATED,
                        "User Registration Success",
                        Optional.ofNullable(user)));
    }


    @GetMapping("/api/car/{id}")
    public ResponseEntity<ApiResponse> getSingleCar(@PathVariable String id) throws ResourceNotFoundException {
        CarResponse user  = authenticationService.getSingleCar(id);
        return ResponseEntity.ok( ).body(
                ApiResponse.make(HttpStatus.CREATED,
                        "User Registration Success",
                        Optional.ofNullable(user)));
    }

    @PostMapping("/api/course")
    public ResponseEntity<ApiResponse> addCourse(@Valid @RequestBody CourseStoreRequest request) throws Exception {
        authenticationService.addCourse(request);
        return ResponseEntity.ok( ).body(
                ApiResponse.make(HttpStatus.CREATED,
                        "User Registration Success",
                        Optional.ofNullable("null")));
    }


    @GetMapping("/api/course")
    public ResponseEntity<ApiResponse> getCourse() throws Exception {
        UserResponse user = authenticationService.getCourse();
        return ResponseEntity.ok( ).body(
                ApiResponse.make(HttpStatus.CREATED,
                        "User Registration Success",
                        Optional.ofNullable(user)));
    }


    @GetMapping("/api/course/{id}")
    public ResponseEntity<ApiResponse> getSingleCourse(@PathVariable String id) throws ResourceNotFoundException {
        Optional<Course> user  = authenticationService.getSingleCourse(id);
        return ResponseEntity.ok( ).body(
                ApiResponse.make(HttpStatus.CREATED,
                        "User Registration Success",
                        Optional.ofNullable(user)));
    }
}
