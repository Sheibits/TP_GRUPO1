package pe.edu.upc.tfversion1.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tfversion1.entities.RestaurantAds;
import pe.edu.upc.tfversion1.repositories.IRestaurantAdsRepository;
import pe.edu.upc.tfversion1.serviceinterfaces.IRestaurantAdsService;

import java.util.List;

@Service
public class RestaurantAdsServiceImplement implements IRestaurantAdsService {

    @Autowired
    private IRestaurantAdsRepository restaurantAdsRepository;

    @Override
    public List<RestaurantAds> list() {
        return restaurantAdsRepository.findAll();
    }

    @Override
    public void insert(RestaurantAds restaurantAds) {
        restaurantAdsRepository.save(restaurantAds);
    }

    @Override
    public void update(RestaurantAds restaurantAds) {
        restaurantAdsRepository.save(restaurantAds);
    }

    @Override
    public void delete(int id) {
        restaurantAdsRepository.deleteById(id);
    }

    @Override
    public RestaurantAds findById(int id) throws RuntimeException {
        return restaurantAdsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RestaurantAds not found"));
    }
}
