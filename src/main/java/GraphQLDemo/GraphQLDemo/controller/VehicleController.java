package GraphQLDemo.GraphQLDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GraphQLDemo.GraphQLDemo.services.VehicleService;
import graphql.ExecutionResult;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

  @Autowired
  private VehicleService vehicleService;

  @PostMapping
  public ResponseEntity<ExecutionResult> executeGraphQlQuery(@RequestBody String query) {
    ExecutionResult executionResult = vehicleService.of().execute(query);
    return new ResponseEntity<ExecutionResult>(executionResult, HttpStatus.OK);
  }

}
