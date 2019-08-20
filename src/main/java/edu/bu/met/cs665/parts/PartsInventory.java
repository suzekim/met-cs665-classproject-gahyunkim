package edu.bu.met.cs665.parts;

import edu.bu.met.cs665.Part;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PartsInventory extends MaintenanceUtil {

  private HashMap<Part, Integer> partsYears;
  private LocalDate date;

  public PartsInventory(LocalDate date) {
    partsYears = new HashMap<>();
    this.date = date;
  }

  public Part getImmedateMaintenancePart() {
    if (partsYears.size() == 0) {
      return null;
    }
    List<Part> parts = new ArrayList<>();
    parts.addAll(partsYears.keySet());

    Part immediatePart = parts.get(0);
    for (Part part : parts) {
      if (partsYears.get(part) < partsYears.get(immediatePart)) {
        immediatePart = part;
      }
    }
    return immediatePart;
  }

  public String getStatus() {
    List<String> status = new ArrayList<>();
    for (Part part : partsYears.keySet()) {
      status.add(checkPartHealth(part, date));
    }
    if (status.contains("critical")) {
      return "critical";
    } else if (status.contains("warning")) {
      return "warning";
    } else {
      return "healthy";
    }
  }

  public List<Part> getCritical() {
    List<Part> criticalParts = new ArrayList<>();
    for (Part part : partsYears.keySet()) {
      if (checkPartHealth(part, date).equalsIgnoreCase("critical")) {
        criticalParts.add(part);
      }
    }
    return criticalParts;
  }

  public List<Part> getWarning() {
    List<Part> warningParts = new ArrayList<>();
    for (Part part : partsYears.keySet()) {
      if (checkPartHealth(part, date).equalsIgnoreCase("warning")) {
        warningParts.add(part);
      }
    }
    return warningParts;
  }

  public List<Part> getParts() {
    List<Part> parts = new ArrayList<>();
    parts.addAll(partsYears.keySet());
    return parts;
  }

  public int getYearsLeft(Part part) {
    return partsYears.get(part);
  }

  public int getSize() {
    return partsYears.size();
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public LocalDate getDate() {
    return date;
  }

  public void add(Part part) {
    partsYears.put(part, part.getYearsLeft(date));
  }

  public void remove(Part part) {
    partsYears.remove(part);
  }

  public boolean contains(Part part) {
    List<Part> parts = new ArrayList<>();
    parts.addAll(partsYears.keySet());
    return parts.contains(part);
  }

  public void print() {
    System.out.println("\n------------------------------------------------");
    System.out.println("| Parts Inventory");
    partsYears.forEach((part, years) -> {
      System.out.println("|\t" + part.getName() + ": ");
      System.out.println("|\t\t" + years + " years left until required maintenance");
    });
    System.out.println("------------------------------------------------\n");
  }
}
