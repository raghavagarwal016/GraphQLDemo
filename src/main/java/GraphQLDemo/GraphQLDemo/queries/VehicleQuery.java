package GraphQLDemo.GraphQLDemo.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import java.util.List;
import java.util.Optional;

import GraphQLDemo.GraphQLDemo.entities.Vehicle;
import GraphQLDemo.GraphQLDemo.services.VehicleService;

@Component
public class VehicleQuery implements GraphQLQueryResolver {

  @Autowired
  private VehicleService vehicleService;

  public List<Vehicle> getVehicles(final int count) {
    return this.vehicleService.getAllVehicles(count);
  }

  public Vehicle getVehicle(final String id) {
    return this.vehicleService.getVehicle(id);
  }

}
