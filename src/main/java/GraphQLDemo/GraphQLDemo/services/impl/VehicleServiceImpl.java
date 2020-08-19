package GraphQLDemo.GraphQLDemo.services.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import GraphQLDemo.GraphQLDemo.datafetchers.CreateVehicle;
import GraphQLDemo.GraphQLDemo.datafetchers.DeleteVehicle;
import GraphQLDemo.GraphQLDemo.datafetchers.UpdateVehicle;
import GraphQLDemo.GraphQLDemo.datafetchers.VehicleDataFetcher;
import GraphQLDemo.GraphQLDemo.datafetchers.VehicleListDataFetcher;
import GraphQLDemo.GraphQLDemo.entities.Vehicle;
import GraphQLDemo.GraphQLDemo.repositories.VehicleRepository;
import GraphQLDemo.GraphQLDemo.services.VehicleService;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class VehicleServiceImpl implements VehicleService {

  @Value("classpath:vehicleql.graphqls")
  private Resource resource;
  @Autowired
  private VehicleRepository vehicleRepository;
  @Autowired
  private VehicleListDataFetcher vehicleListDataFetcher;
  @Autowired
  private VehicleDataFetcher vehicleDataFetcher;
  @Autowired
  private CreateVehicle createVehicle;
  @Autowired
  private UpdateVehicle updateVehicle;
  @Autowired
  private DeleteVehicle deleteVehicle;
  private GraphQL graphQL;

  @PostConstruct
  private void loadSchema() throws IOException {
    File schemaFile = this.resource.getFile();
    TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
    RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
        .type("Query", typeWiring -> typeWiring
            .dataFetcher("vehicles", this.vehicleListDataFetcher)
            .dataFetcher("vehicle", this.vehicleDataFetcher))
        .type("Mutation", typeWiring -> typeWiring
            .dataFetcher("createVehicle", this.createVehicle)
            .dataFetcher("updateVehicle", this.updateVehicle)
            .dataFetcher("deleteVehicle", this.deleteVehicle))
        .build();
    GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
    this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
  }

  @Override
  public GraphQL of() {
    return this.graphQL;
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public Vehicle createVehicle(String type, String modelCode, String brandName, String launchDate) {
    final Vehicle vehicle = new Vehicle();
    vehicle.setType(type);
    vehicle.setModelCode(modelCode);
    vehicle.setBrandName(brandName);
    vehicle.setLaunchDate(LocalDate.parse(launchDate));
    return this.vehicleRepository.save(vehicle);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Vehicle> getAllVehicles(int count) {
    return this.vehicleRepository.findAll().stream().limit(count).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  @Override
  public Vehicle getVehicle(String id) {
    return this.vehicleRepository.findOne(id);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public Vehicle updateVehicle(final String id, final String type, final String modelCode, final String brandName,
      final String launchDate) {
    Vehicle vehicle = this.vehicleRepository.getOne(id);
    vehicle.setType(type);
    vehicle.setModelCode(modelCode);
    vehicle.setBrandName(brandName);
    vehicle.setLaunchDate(LocalDate.parse(launchDate));
    return this.vehicleRepository.save(vehicle);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public Vehicle deleteVehicle(String id) {
    Vehicle vehicle = vehicleRepository.findOne(id);
    vehicleRepository.delete(vehicle.getId());
    return vehicle;
  }

}
