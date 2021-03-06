package edu.bu.met.cs665;

import edu.bu.met.cs665.parts.Engine;
import edu.bu.met.cs665.parts.Radar;
import java.time.LocalDate;
import java.time.Month;

public class SampleAircraft {

  long id = 1L;
  String modelName = "F-22";

  Radar radar = new Radar("electronic span");
  Engine engine = new Engine("gas turbine");

  int refuelingRate = 1000;
  int speed = 2410;
  int range = 1600;
  int engineThrust = 35000;
  int weight = 38000;

  public Aircraft buildF22() {
    radar.setLifespan(10);
    radar.setStartDate(LocalDate.of(2000, Month.APRIL, 1));

    engine.setLifespan(5);
    engine.setStartDate(LocalDate.of(2000, Month.APRIL, 1));

    Aircraft F22 = new Aircraft.Builder(id)
        .withModelName(modelName)
        .withDate(LocalDate.now())
        .withRadar(radar)
        .withEngine(engine)
        .withRefuelRate(refuelingRate)
        .withSpeed(speed)
        .withRange(range)
        .withEngineThrust(engineThrust)
        .withWeight(weight)
        .build();

    return F22;
  }
}
