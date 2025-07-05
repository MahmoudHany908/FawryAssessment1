import java.util.*;

public class ShippingService {

    // Might improve logic later
    public static void ship(List<Shippable> stuffToShip) {
        if (stuffToShip == null || stuffToShip.isEmpty()) {
            System.out.println("Nothing to ship right now.");
            return;
        }

        System.out.println("** Shipment notice **");

        Map<String, Integer> nameToCountMap = new LinkedHashMap<>();
        //need a reference weight per name (just taking the latest one we see)
        Map<String, Double> nameToWeight = new HashMap<>();

        double grandTotalWeight = 0.0;

        for (Shippable thing : stuffToShip) {
            String label = thing.getName();

            if (!nameToCountMap.containsKey(label)) {
                nameToCountMap.put(label, 1);
            } else {
                int currentCount = nameToCountMap.get(label);
                nameToCountMap.put(label, currentCount + 1);
            }

            // If same-named items have diff weights, we'll just take the latest one ¯\_(ツ)_/¯
            nameToWeight.put(label, thing.getWeight());
            grandTotalWeight += thing.getWeight();
        }

        for (String itemName : nameToCountMap.keySet()) {
            int count = nameToCountMap.get(itemName);
            double singleWeight = nameToWeight.getOrDefault(itemName, 0.0);
            double combinedWeight = singleWeight * count;

            // Note: Could improve formatting, but this works for now
            System.out.printf("%dx %-12s %.0fg\n", count, itemName, combinedWeight);
        }

        // Convert to kg and print final weight
        double weightInKg = grandTotalWeight / 1000.0;
        System.out.printf("Total package weight %.1fkg\n", weightInKg);
    }
}
