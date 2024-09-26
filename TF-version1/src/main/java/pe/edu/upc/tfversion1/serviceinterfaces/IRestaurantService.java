package pe.edu.upc.tfversion1.serviceinterfaces;

import pe.edu.upc.tfversion1.entities.Restaurant;

import java.util.List;

public interface IRestaurantService {
    public List<Restaurant> list();
    public void insert(Restaurant restaurant);
    public void update(Restaurant restaurant);
    public void delete(int id);
}
