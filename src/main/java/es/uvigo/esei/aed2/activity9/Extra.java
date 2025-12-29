package es.uvigo.esei.aed2.activity9;

import es.uvigo.esei.aed2.map.Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Extra {

    public static boolean fillUpFlashDrive(int capacityCD, Map<String, Integer> espacePrograms, Set<String> flashDrive) {
        boolean objetivo = false;
        List<String> candidates = new ArrayList<>(espacePrograms.getKeys());

        int currentCandidate = 0;

        while (currentCandidate < candidates.size() && !objetivo) {
            String program = candidates.get(currentCandidate);
            int programSize = espacePrograms.get(program);

            // Si se puede añadir el programa
            if (capacityCD >= programSize) {
                flashDrive.add(program);
                espacePrograms.remove(program);

                if (capacityCD - programSize == 0) {
                    objetivo = true;
                } else {
                    objetivo = fillUpFlashDrive(capacityCD - programSize, espacePrograms, flashDrive);

                    if (!objetivo) {
                        flashDrive.remove(program);
                        espacePrograms.add(program, programSize);
                    }
                }
            }

            currentCandidate++;
        }

        return objetivo;
    }

}
