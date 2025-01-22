package com.arpinder.restaurantlists.service;

import com.arpinder.restaurantlists.dto.RestaurantDTO;
import com.arpinder.restaurantlists.entity.Restaurant;
import com.arpinder.restaurantlists.mapper.RestaurantMapper;
import com.arpinder.restaurantlists.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class RestaurantService {
    @Autowired
    RestaurantRepo restaurantRepo;

    public List<RestaurantDTO> findAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        // map data comping from DB to DTOs
        List<RestaurantDTO> restaurantDTOList = restaurants.stream().map(restaurant ->
                        RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant))
                .collect(Collectors.toList());
        return restaurantDTOList;

    }

    public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
        //map DTO to restaurant to store in DB
        Restaurant restaurant = RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO);
        restaurantRepo.save(restaurant);
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant);
    }

    public ResponseEntity<RestaurantDTO> fetchRestaurantById(Long id) {
        Optional<Restaurant> restaurant = restaurantRepo.findById(id);
        if (restaurant.isPresent()) {
            return new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

}
