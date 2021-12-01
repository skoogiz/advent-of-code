package day_01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SonarSweep {

    final List<Integer> depths;
    Integer prevDepth;

    public SonarSweep(List<Integer> depths) {
        this.depths = depths;
    }

    public SweepData sweep() {
        SweepData data = new SweepData();
        depths.forEach(depth -> {
            if (prevDepth != null) {
                if (prevDepth < depth) data.increased();
                else data.decreased();
            }
            prevDepth = depth;
        });

        return data;
    }

    static class SweepData {
        Integer increases = 0;
        Integer decreases = 0;

        public void increased() {
            this.increases++;
        }

        public void decreased() {
            this.decreases++;
        }

        @Override
        public String toString() {
            return "SonarSweep: {" +
                    "increases: " + increases +
                    ", decreases: " + decreases +
                    '}';
        }

        public void print() {
            System.out.println(this);
        }
    }

    public static List<Integer> mapThreeMeasurementSlidingWindow(List<Integer> depths) {
        List<Integer> threeMeasurements = new ArrayList<>();
        for (int i = 0; i < depths.size() - 2; i++) {
            threeMeasurements.add(depths.get(i) + depths.get(i + 1) + depths.get(i + 2));
        }
        return threeMeasurements;
    }

    public static void main(String[] args) {
        List<Integer> depths = parsePuzzleInput();

        new SonarSweep(depths).sweep().print();
        new SonarSweep(mapThreeMeasurementSlidingWindow(depths)).sweep().print();

    }

    public static List<Integer> parsePuzzleInput() {
        try (Stream<String> stream = Files.lines(Paths.get("src/main/resources/input.txt"))) {
            return stream.map(Integer::parseInt).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
