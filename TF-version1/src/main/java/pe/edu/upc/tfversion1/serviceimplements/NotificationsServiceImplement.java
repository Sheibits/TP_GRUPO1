package pe.edu.upc.tfversion1.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tfversion1.entities.Notifications;
import pe.edu.upc.tfversion1.repositories.INotificationsRepository;
import pe.edu.upc.tfversion1.serviceinterfaces.INotificationsService;

import java.util.List;

@Service
public class NotificationsServiceImplement implements INotificationsService {

    @Autowired
    private INotificationsRepository notificationsRepository;

    @Override
    public List<Notifications> list() {
        return notificationsRepository.findAll();
    }

    @Override
    public void insert(Notifications notifications) {
        notificationsRepository.save(notifications);
    }

    @Override
    public void update(Notifications notifications) {
        notificationsRepository.save(notifications);
    }

    @Override
    public void delete(int id) {
        notificationsRepository.deleteById(id);
    }
}
