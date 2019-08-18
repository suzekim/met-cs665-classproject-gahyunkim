package edu.bu.met.cs665.states;

import edu.bu.met.cs665.Aircraft;
import edu.bu.met.cs665.parts.Part;

public class WarningAircraftState implements AircraftState {
  private Aircraft aircraft;
  private Part warningPart;

  public WarningAircraftState(Aircraft aircraft) {
    this.aircraft = aircraft;
  }

  public void evaluate() {
    // change state if necessary

  }

  public void printResults() {

  }

  public void performMaintenance() {

  }
}