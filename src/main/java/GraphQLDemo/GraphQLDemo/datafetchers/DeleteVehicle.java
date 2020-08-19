package GraphQLDemo.GraphQLDemo.datafetchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import GraphQLDemo.GraphQLDemo.entities.Vehicle;
import GraphQLDemo.GraphQLDemo.repositories.VehicleRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class DeleteVehicle implements DataFetcher<Vehicle> {

  @Autowired
  private VehicleRepository vehicleRepository;

  @Override
  public Vehicle get(DataFetchingEnvironment dataFetchingEnvironment) {
    Vehicle vehicle = vehicleRepository.findOne(dataFetchingEnvironment.getArgument("id").toString());
    vehicleRepository.delete(vehicle.getId());
    return vehicle;
  }
}
