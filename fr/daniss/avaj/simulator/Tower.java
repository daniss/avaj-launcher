package fr.daniss.avaj.simulator;

import java.util.ArrayList;
import java.util.List;

public class Tower {
   private List<Flyable> observers = new ArrayList<Flyable>();

   public void register(Flyable p_flyable) {
      this.observers.add(p_flyable);
      System.out.println("Tower says: " + p_flyable.getIdentifier() + " registered to weather tower.");
   }

   public void unregister(Flyable p_flyable) {
      this.observers.remove(p_flyable);
      System.out.println("Tower says: " + p_flyable.getIdentifier() + " unregistered from weather tower.");
   }

   protected void conditionChanged() {
       List<Flyable> observersCopy = new ArrayList<>(observers);
       for (Flyable observer : observersCopy) {
           observer.updateConditions();
       }
    }
}
