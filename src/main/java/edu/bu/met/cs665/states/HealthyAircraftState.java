package edu.bu.met.cs665.states;

import edu.bu.met.cs665.Aircraft;

public class HealthyAircraftState implements AircraftState {
  private Aircraft aircraft;
  private int yearsUntilMaintenance;

  public HealthyAircraftState(Aircraft aircraft) {
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
