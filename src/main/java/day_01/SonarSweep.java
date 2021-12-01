package day_01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

public class SonarSweep {

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

    static class FirstPuzzle {
        final Path puzzleInput;
        Integer prevDepth;

        public FirstPuzzle(Path puzzleInput) {
            this.puzzleInput = puzzleInput;
        }

        public SweepData sweep() {
            SweepData data = new SweepData();
            try (Stream<String> stream = Files.lines(puzzleInput)) {
                stream.forEach(input -> {
                    int depth = Integer.parseInt(input);
                    if (prevDepth != null) {
                        if (prevDepth < depth) data.increased();
                        else data.decreased();
                    }
                    prevDepth = depth;
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
    }

    public static void main(String[] args) {
        String fileName = "src/main/resources/input.txt";

        Path puzzleInput = Paths.get(fileName);

        new FirstPuzzle(puzzleInput).sweep().print();
    }
}
