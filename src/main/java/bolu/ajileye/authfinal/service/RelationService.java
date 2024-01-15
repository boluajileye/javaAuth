package bolu.ajileye.authfinal.service;

import bolu.ajileye.authfinal.dto.request.CarStoreRequest;

public interface RelationService{
    void addCar(CarStoreRequest request);

    void getCar();

    void getSingleCar(String id);
}
