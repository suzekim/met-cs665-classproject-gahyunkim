package edu.bu.met.cs665.state;

import edu.bu.met.cs665.BeverageInventory;
import edu.bu.met.cs665.BeverageVendingMachine;
import edu.bu.met.cs665.beverages.Beverage;

public class ReadyMachineState implements MachineState {

  private BeverageVendingMachine vendingMachine;

  public ReadyMachineState(BeverageVendingMachine vendingMachine) {
    this.vendingMachine = vendingMachine;
  }

  // Already initialized and ready to accept and brew a beverage
  public void initialize() {
    System.out.println("Machine already initialized");
    vendingMachine.requestBeverage();
  }

  // Finds appropriate beverage
  // Prints out listed cost
  // If beverage is available, starts brew process and sets to brewing state
  public void brew(String beverageName) {
    Beverage beverage = vendingMachine.getBeverage(beverageName);
    if (beverage != null && available(beverage)) {

      System.out.println("\n" + beverageName + " costs $" + beverage.getPrice());
      System.out.println("------------------------------------------");
      System.out.println("Starting brew process for " + beverageName + "!");

      BeverageInventory inventory = vendingMachine.getInventory();
      inventory.decrementBeverage(beverage);
      beverage.make();
      vendingMachine.setMachineState(vendingMachine.getBrewingState());
    }
  }

  // Checks availability of beverage using machine's inventory.
  private boolean available(Beverage beverage) {
    if (vendingMachine.getInventory().getCount(beverage) == 0) {
      System.out.println("Sorry, we are out of " + beverage.getName());
      System.out.println("Need to restock!");
      return false;
    }
    return true;
  }

  // Cannot serve without specified beverage to brew
  public void serve() {
    System.out.println("Please specify which beverage to brew");
  }

  public String get() {
    String state = "Ready";
    System.out.println(state);
    return state;
  }
}
