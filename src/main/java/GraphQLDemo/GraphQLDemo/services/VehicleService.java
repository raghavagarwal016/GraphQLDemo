package GraphQLDemo.GraphQLDemo.services;

import java.util.List;
import java.util.Optional;

import GraphQLDemo.GraphQLDemo.entities.Vehicle;
import graphql.GraphQL;

public interface VehicleService {
  GraphQL of();

  Vehicle createVehicle(final String type, final String modelCode, final String brandName, final String launchDate);

  public List<Vehicle> getAllVehicles(final int count);

  public Vehicle getVehicle(final String id);

  public Vehicle updateVehicle(final String id, final String type, final String modelCode, final String brandName,
      final String launchDate);

  public Vehicle deleteVehicle(String id);
}
