package GraphQLDemo.GraphQLDemo.datafetchers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import GraphQLDemo.GraphQLDemo.entities.Vehicle;
import GraphQLDemo.GraphQLDemo.repositories.VehicleRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class CreateVehicle implements DataFetcher<Vehicle> {

  @Autowired
  private VehicleRepository vehicleRepository;

  @Override
  public Vehicle get(DataFetchingEnvironment dataFetchingEnvironment) {
    final Vehicle vehicle = new Vehicle();
    vehicle.setType(dataFetchingEnvironment.getArgument("type"));
    vehicle.setModelCode(dataFetchingEnvironment.getArgument("modelCode"));
    vehicle.setBrandName(dataFetchingEnvironment.getArgument("brandName"));
    vehicle.setLaunchDate(LocalDate.parse(dataFetchingEnvironment.getArgument("launchDate")));
    return this.vehicleRepository.save(vehicle);
  }
}
