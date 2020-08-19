package GraphQLDemo.GraphQLDemo.datafetchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import GraphQLDemo.GraphQLDemo.entities.Vehicle;
import GraphQLDemo.GraphQLDemo.repositories.VehicleRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class VehicleListDataFetcher implements DataFetcher<List<Vehicle>> {

  @Autowired
  private VehicleRepository vehicleRepository;

  @Override
  public List<Vehicle> get(DataFetchingEnvironment dataFetchingEnvironment) {
    return this.vehicleRepository.findAll().stream()
        .limit(Integer.parseInt(dataFetchingEnvironment.getArgument("count"))).collect(Collectors.toList());
  }
}
