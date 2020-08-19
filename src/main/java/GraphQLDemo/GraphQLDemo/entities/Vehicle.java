package GraphQLDemo.GraphQLDemo.entities;
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity(name = "vehicle")
public class Vehicle implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name="uuid",strategy = "uuid2")
  private String id;

  @Column(name = "type", nullable = false)
  private String type;

  @Column(name = "model_code", nullable = false)
  private String modelCode;

  @Column(name = "brand_name")
  private String brandName;

  @Column(name = "launch_date")
  private LocalDate launchDate;
}
