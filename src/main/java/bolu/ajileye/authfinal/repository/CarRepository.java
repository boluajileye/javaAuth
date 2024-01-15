package bolu.ajileye.authfinal.repository;

import bolu.ajileye.authfinal.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, String> {
}
