package com.arpinder.restaurantlists.controller;

import com.arpinder.restaurantlists.dto.Checking;
import com.arpinder.restaurantlists.dto.RestaurantDTO;
import com.arpinder.restaurantlists.entity.Restaurant;
import com.arpinder.restaurantlists.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;


    @GetMapping("/fetchAllRestaurants")
    public ResponseEntity<List<RestaurantDTO>> fetchAllRestaurants() {
        List<RestaurantDTO> allRestaurants = restaurantService.findAllRestaurants();
        return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantDTO> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO restaurantAdded = restaurantService.addRestaurantInDB(restaurantDTO);
        return new ResponseEntity<>(restaurantAdded, HttpStatus.CREATED);
    }

    @GetMapping("/fetchById/{id}")
    public ResponseEntity<RestaurantDTO> fetchRestaurantById(@PathVariable Long id) {
        ResponseEntity<RestaurantDTO> restaurantDTO = restaurantService.fetchRestaurantById(id);
        return restaurantDTO;
    }

}











//    @PostMapping("/add")
//    public Restaurant saveRestaurant(@RequestBody Restaurant restaurantDTO) {
//        System.out.println("Restaurant saved"+restaurantDTO);
//        return restaurantDTO;
//    }
//    @PostMapping("/check")
//    public Checking check(@RequestBody Checking checking){
//        System.out.println("Restaurant checking"+checking);
//        return checking;
//    }

