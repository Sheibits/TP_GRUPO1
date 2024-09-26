package pe.edu.upc.tfversion1.serviceinterfaces;

import pe.edu.upc.tfversion1.entities.ArtistProfile;

import java.util.List;

public interface IArtistProfileService {
    public List<ArtistProfile> list();
    public void insert(ArtistProfile artistProfile);
    public void update(ArtistProfile artistProfile);
    public void delete(int id);
    ArtistProfile findById(int id) throws RuntimeException;
}
