package aoc;



import java.util.ArrayList;
import java.util.List;



public class Main {

    public final static boolean TEST = false;
    public final static boolean PRINT_ALL = false;
    public final static String TITLE = "Advent of Code 2024";

    public static void main(String[] args) {
        List<Day> allDays = getAllDays();

        System.out.println(TITLE);
        if (PRINT_ALL) {
            for (Day d: allDays) {
                System.out.println(d.getClass().getSimpleName());
                System.out.println("Part One: " + d.runPartOne(d.forceTest() || TEST));
                System.out.println("Part Two: " + d.runPartTwo(d.forceTest() || TEST));

            }
        } else {
            Day latest = allDays.get(allDays.size()-1);
            List<Day> toPrint = new ArrayList<>(allDays.stream().filter(Day::mustPrint).toList());
            if (toPrint.isEmpty()) {
                toPrint.add(latest);
            }
            for (Day d: toPrint) {
                System.out.println(d.getClass().getSimpleName());
                System.out.println("\tPart One: " + d.runPartOne(d.forceTest() || TEST));
                System.out.println("\tPart Two: " + d.runPartTwo(d.forceTest() || TEST));
            }
        }
     }

    public static List<Day> getAllDays() {
        ArrayList<Day> days = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            try {
                days.add((Day) Main.class.getClassLoader().loadClass("aoc.day" + (i+1) + ".Day" + (i+1)).getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                break;
            }
        }
        return days;
    }
}
