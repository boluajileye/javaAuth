package bolu.ajileye.authfinal.service.implementation;

import bolu.ajileye.authfinal.dto.api.BibleResponse;
import bolu.ajileye.authfinal.dto.request.CarStoreRequest;
import bolu.ajileye.authfinal.dto.request.CourseStoreRequest;
import bolu.ajileye.authfinal.dto.request.LoginRequest;
import bolu.ajileye.authfinal.dto.request.RegisterRequest;
import bolu.ajileye.authfinal.dto.response.AuthenticationResponse;
import bolu.ajileye.authfinal.dto.response.CarResponse;
import bolu.ajileye.authfinal.dto.response.CourseResponse;
import bolu.ajileye.authfinal.dto.response.UserResponse;
import bolu.ajileye.authfinal.entity.Car;
import bolu.ajileye.authfinal.entity.Course;
import bolu.ajileye.authfinal.entity.User;
import bolu.ajileye.authfinal.enums.Role;
import bolu.ajileye.authfinal.event.AppEventPublisher;
import bolu.ajileye.authfinal.event.RegisterEvent;
import bolu.ajileye.authfinal.exception.ResourceNotFoundException;
import bolu.ajileye.authfinal.repository.CarRepository;
import bolu.ajileye.authfinal.repository.CourseRepository;
import bolu.ajileye.authfinal.repository.UserRepository;
import bolu.ajileye.authfinal.service.AuthenticationService;
import bolu.ajileye.authfinal.utils.AuthHelper;
import bolu.ajileye.authfinal.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationServiceImplementation extends BaseService implements AuthenticationService {


    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final CourseRepository  courseRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final AuthHelper authHelper;

    @Autowired
    private AppEventPublisher appEvent;


    @Override
    public UserResponse register(RegisterRequest registerRequest) {
        logger("Adding new user with email "+ registerRequest.getEmail() );
        User userData = prepareUserData(registerRequest);

        User user =  userRepository.save(userData);

        CompletableFuture.runAsync(() -> {
            appEvent.publishRegisterEvent(user);
        });
        return UserResponse.builder()
                            .id(user.getId())
                            .email(user.getEmail())
                            .build();
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) throws Exception {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = userRepository.findByEmail(request.getEmail())
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getEmail())
                        .password(user.getPassword())
                        .roles(user.getRole().toString()) // or set authorities as needed
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Optional<User> userOptional = this.userRepository.findByEmail(userDetails.getUsername());

        User user = userOptional.orElseThrow(() -> new Exception("No user found"));

        var jwtToken = jwtUtils.generateToken(userDetails);
        var refreshToken = jwtUtils.generateRefreshToken(userDetails);


        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();

    }


    @Override
    public void addCar(CarStoreRequest request) throws Exception {
        User user = authHelper.getAuthenticatedUser();
        Car carData = prepareCarData(request, user);
        Car car = carRepository.save(carData);
    }

    private Car prepareCarData(CarStoreRequest request, User user) {
        Car car = new Car();
        car.setName(request.getName());
        car.setBrand(request.getBrand());
        car.setYear(request.getYear());
        car.setUser(user);

        return car;
    }

    @Override
    public UserResponse getCar() throws Exception {
        User user = authHelper.getAuthenticatedUser();
        List<Car> cars = user.getCar();
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .about(user.getAbout())
                .role(user.getRole())
                .car(carResponse(cars))
                .build();
    }

    private List<CarResponse> carResponse(List<Car> cars) {
        return cars.stream()
                .map(car -> {
                    CarResponse carResponse = CarResponse.builder().id(car.getId()).name(car.getName()).brand(car.getBrand()).year(car.getYear()).build();
                    return carResponse;
                })
                .collect(Collectors.toList());

    }

    @Override
    public CarResponse getSingleCar(String id) throws ResourceNotFoundException {
        Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car not found"));
        return CarResponse.builder()
                .name(car.getName())
                .brand(car.getBrand())
                .year(car.getYear())
                .build();
    }

    @Override
    public CourseResponse addCourse(CourseStoreRequest request) throws Exception {
        User authUser = authHelper.getAuthenticatedUser();
        List<User> user = userRepository.findAllById(Collections.singleton(authUser.getId()));
        Course course = prepareCourseData(request, user);
        courseRepository.save(course);
        return CourseResponse.builder().name(course.getName()).score(course.getScore()).isGraded(course.getIsGraded()).build();
    }

    private Course prepareCourseData(CourseStoreRequest request, List<User> user) {
        Course course = new Course();
        course.setUser(user);
        course.setName(request.getName());
        course.setScore(request.getScore());
        course.setIsGraded(request.getIsGraded());

        return course;
    }

    @Override
    public Optional<Course> getSingleCourse(String id) {
        Optional<Course> course = courseRepository.findById(id);
        return course;
    }

    @Override
    public UserResponse getCourse() throws Exception {
        User user = authHelper.getAuthenticatedUser();

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .about(user.getAbout())
                .role(user.getRole())
//                .car(cars)
                .courses(courseResponse(user.getCourses()))
                .build();
    }

    public Set<CourseResponse> courseResponse(Set<Course> courses) {
        return courses.stream()
                .map(course -> {
                    CourseResponse courseResponse = CourseResponse.builder()
                            .id(course.getId())
                                                                    .name(course.getName())
                                                                    .score(course.getScore())
                                                                    .isGraded(course.getIsGraded())
                                                                    .build();
                    return courseResponse;
                })
                .collect(Collectors.toSet());
    }
    private User prepareUserData(RegisterRequest registerRequest){
        return User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .phone(registerRequest.getPhone())
                .password(registerRequest.getPassword())
                .about(registerRequest.getAbout())
                .role(Role.valueOf(registerRequest.getRole()))
                .build();
    }
}
