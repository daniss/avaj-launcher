package fr.daniss.avaj.simulator;

import java.util.ArrayList;
import java.util.List;

public class Tower {
   private List<Flyable> observers;

   public void register(Flyable var1) {
      this.observers.add(var1);
      System.out.println("Tower says: " + var1.getClass().getSimpleName() + " registered to weather tower.");
   }

   public void unregister(Flyable var1) {
      System.out.println("Tower says: " + var1.getClass().getSimpleName() + " unregistered from weather tower.");
   }

   protected void conditionsChanged() {
        List<Flyable> observersCopy = new ArrayList<>(observers);
        for (Flyable observer : observersCopy) {
            observer.updateConditions();
        }
    }
}
