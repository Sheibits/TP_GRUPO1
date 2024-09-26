package pe.edu.upc.tfversion1.serviceinterfaces;

import pe.edu.upc.tfversion1.entities.Notifications;

import java.util.List;

public interface INotificationsService {
    public List<Notifications> list();
    public void insert(Notifications notifications);
    public void update(Notifications notifications);
    public void delete(int id);
}
