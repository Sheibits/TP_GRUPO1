package pe.edu.upc.tfversion1.serviceinterfaces;

import pe.edu.upc.tfversion1.entities.RestaurantAds;

import java.util.List;

public interface IRestaurantAdsService {
    public List<RestaurantAds> list();
    public void insert(RestaurantAds restaurantAds);
    public void update(RestaurantAds restaurantAds);
    public void delete(int id);
    RestaurantAds findById(int id) throws RuntimeException;
}
