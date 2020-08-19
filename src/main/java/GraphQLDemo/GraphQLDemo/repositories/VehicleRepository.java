package GraphQLDemo.GraphQLDemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import GraphQLDemo.GraphQLDemo.entities.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
}
