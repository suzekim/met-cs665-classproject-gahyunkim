package edu.bu.met.cs665.state;

import edu.bu.met.cs665.Aircraft;
import edu.bu.met.cs665.AircraftState;
import edu.bu.met.cs665.Part;
import edu.bu.met.cs665.parts.MaintenanceUtil;
import edu.bu.met.cs665.parts.PartsInventory;
import java.util.List;

public class WarningAircraftState extends MaintenanceUtil implements AircraftState {
  private Aircraft aircraft;
  private List<Part> warningParts;
  private PartsInventory inventory;

  public WarningAircraftState(Aircraft aircraft) {
    this.aircraft = aircraft;
    this.inventory = aircraft.getInventory();
  }

  // Assesses and changes state appropriately based on critical/warning/healthy status
  // If status is same, gets list of parts with warnings, needing maintenance soon
  public void evaluate() {
    PartsInventory inventory = aircraft.getInventory();
    if (inventory.getStatus().equalsIgnoreCase("critical")) {
      aircraft.setState(aircraft.getCriticalAircraftState());
      aircraft.checkHealth();
      return;
    } else if (inventory.getStatus().equalsIgnoreCase("healthy")) {
      aircraft.setState(aircraft.getHealthyAircraftState());
      aircraft.checkHealth();
      return;
    } else {
      warningParts = inventory.getWarningParts();
      return;
    }
  }

  public void printResults() {
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("WARNING - Please schedule maintenance for following:");
    for (Part part: warningParts) {
      printMaintenance(part);
    }
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
  }

  private void printMaintenance(Part part) {
    System.out.println("Part " + part.getName() + " inspection");
    System.out.print(" due in " + inventory.getYearsLeft(part));
  }

  public void performMaintenance() {
    List<Part> partList = aircraft.getInventory().getParts();
    for (Part part : partList) {
      performPartMaintenance(part, aircraft.getDate());
    }
    aircraft.setState(aircraft.getHealthyAircraftState());
    System.out.println("\nMaintenance Complete!\n");
  }

  public String get() {
    return "warning";
  }
}
