package aoc.day21;

import java.util.HashMap;
import java.util.Map;

public class NumericalKeypadOperator extends KeypadOperator {

    public NumericalKeypadOperator() {
        // When multiple alternatives exist, changing direction is less good than not changing
        // direction. Hense, those are not even listed (for example A7 could be ^<^<^, but that is simply not good
        // as it would require moving the robot one step down in the process.
        // Since each move is followed by a button press (ie: movement to A for the next bot, we pick the alternative
        // whose latest direction to A is most efficient in size. For a directional bot:
        //  Movement from ^ or > to A is a single movement. So it doesn't matter whether a movement ends on ^ or >
        //          ^A --> >
        //          >A --> ^
        //  Movement from v or < to A is worse
        //          <A --> >>^
        //          vA --> >^
        // So we prefer alternatives that end on ^ or >, then those ending on v and only pick those ending on <
        // when there is no alternative
        conversionMap.put("01", "^<");
        conversionMap.put("02", "^");
        conversionMap.put("03", "^>");
        conversionMap.put("04", "^^<");
        conversionMap.put("05", "^^");
        conversionMap.put("06", "^^>"); // ">^^"
        conversionMap.put("07", "^^^<");
        conversionMap.put("08", "^^^");
        conversionMap.put("09", "^^^>");
        conversionMap.put("0A", ">");

        conversionMap.put("10", ">v");
        conversionMap.put("12", ">");
        conversionMap.put("13", ">>");
        conversionMap.put("14", "^");
        conversionMap.put("15", "^>"); // >^
        conversionMap.put("16", "^>>"); // >>^
        conversionMap.put("17", "^^");
        conversionMap.put("18", "^^>"); // >^^
        conversionMap.put("19", "^^>>"); // >>^^
        conversionMap.put("1A", ">>v");

        conversionMap.put("20", "v");
        conversionMap.put("21", "<");
        conversionMap.put("23", ">");
        conversionMap.put("24", "<^"); // ^< (less efficient)
        conversionMap.put("25", "^");
        conversionMap.put("26", "^>"); // >^
        conversionMap.put("27", "<^^"); // ^^< (less efficient)
        conversionMap.put("28", "^^");
        conversionMap.put("29", "^^>"); // >^^
        conversionMap.put("2A", "v>"); // >v (less efficient)

        conversionMap.put("30", "<v"); // v< (less efficient)
        conversionMap.put("31", "<<");
        conversionMap.put("32", "<");
        conversionMap.put("34", "<<^"); // ^<< (less efficient)
        conversionMap.put("35", "<^"); // ^<  (less efficient)
        conversionMap.put("36", "^");
        conversionMap.put("37", "<<^^"); // ^^<< (less efficient)
        conversionMap.put("38", "<^^"); // ^^< (less efficient)
        conversionMap.put("39", "^^");
        conversionMap.put("3A", "v");

        conversionMap.put("40", ">vv");
        conversionMap.put("41", "v");
        conversionMap.put("42", "v>"); // >v (less efficient)
        conversionMap.put("43", "v>>"); // >>v (less efficient)
        conversionMap.put("45", ">");
        conversionMap.put("46", ">>");
        conversionMap.put("47", "^");
        conversionMap.put("48", "^>"); // >^
        conversionMap.put("49", "^>>");//>^^
        conversionMap.put("4A", ">>vv"); // >>vv (less efficient)

        conversionMap.put("50", "vv");
        conversionMap.put("51", "<v"); // v< (less efficient)
        conversionMap.put("52", "v");
        conversionMap.put("53", "v>"); // >v (less efficient)
        conversionMap.put("54", "<");
        conversionMap.put("56", ">");
        conversionMap.put("57", "<^"); // ^< (less efficient)
        conversionMap.put("58", "^");
        conversionMap.put("59", "^>"); // >^
        conversionMap.put("5A", "vv>"); // >vv (less efficient)

        conversionMap.put("60", "<vv"); // vv< (less efficient)
        conversionMap.put("61", "<<v"); // v<< (less efficient)
        conversionMap.put("62", "<v"); // v< (less efficient)
        conversionMap.put("63", "v");
        conversionMap.put("64", "<<");
        conversionMap.put("65", "<");
        conversionMap.put("67", "<<^"); // ^<< (less efficient)
        conversionMap.put("68", "<^"); // ^< (less efficient)
        conversionMap.put("69", "^");
        conversionMap.put("6A", "vv");

        conversionMap.put("70", ">vvv");
        conversionMap.put("71", "vv");
        conversionMap.put("72", "vv>"); // >vv (less efficient)
        conversionMap.put("73", "vv>>"); // >>vv (less efficient)
        conversionMap.put("74", "v");
        conversionMap.put("75", "v>"); // >v (less efficient)
        conversionMap.put("76", "v>>"); // >>v (less efficient)
        conversionMap.put("78", ">");
        conversionMap.put("79", ">>");
        conversionMap.put("7A", ">>vvv"); // >>vvv (less efficient)

        conversionMap.put("80", "vvv");
        conversionMap.put("81", "vv>"); // <vv
        conversionMap.put("82", "vv");
        conversionMap.put("83", "vv>"); // >vv
        conversionMap.put("84", "<v"); //  v< (less efficient)
        conversionMap.put("85", "v");
        conversionMap.put("86", "v>"); // >v
        conversionMap.put("87", "<");
        conversionMap.put("89", ">");
        conversionMap.put("8A", "vvv>"); //  >vvv (less efficient)

        conversionMap.put("90", "<vvv"); // vvv<
        conversionMap.put("91", "<<vv"); // vv<< (less efficient)
        conversionMap.put("92", "<vv"); // vv<
        conversionMap.put("93", "vv");
        conversionMap.put("94", "<<v"); // v<< (less efficient)
        conversionMap.put("95", "<v"); // v< (less efficient)
        conversionMap.put("96", "v");
        conversionMap.put("97", "<<");
        conversionMap.put("98", "<");
        conversionMap.put("9A", "vvv");

        conversionMap.put("A0", "<");
        conversionMap.put("A1", "^<<");
        conversionMap.put("A2", "<^"); // ^< (less efficient)
        conversionMap.put("A3", "^");
        conversionMap.put("A4", "^^<<");
        conversionMap.put("A5", "<^^"); // ^^< (less efficient)
        conversionMap.put("A6", "^^");
        conversionMap.put("A7", "^^^<<");
        conversionMap.put("A8", "<^^^"); // ^^^< (less efficient)
        conversionMap.put("A9", "^^^");
    }
}
