package io.github.asher0913.platformer.mvc.model;
/**
 * Represents the predefined level data for the game. Each level is a 2D array of strings,
 * where each character corresponds to a specific game object or entity.
 *
 * <p>The characters represent the following entities:</p>
 * <ul>
 *     <li>'0' - Empty space (no entity)</li>
 *     <li>'1' - Platform block</li>
 *     <li>'C' - Coin (collectible)</li>
 *     <li>'H' - Heart (health collectible)</li>
 *     <li>'D' - Spike (obstacle)</li>
 *     <li>'M' - Monster (enemy)</li>
 *     <li>'E' - Goal (end of the level)</li>
 * </ul>
 *
 * <p>Each level is represented as a {@code String[]} array where each row corresponds to
 * a horizontal slice of the level. The levels are defined as public static constants
 * to be easily accessible throughout the application.</p>
 */
public class LevelData {

    /**
     * Represents the layout for Level 1.
     */
    public static final String[] Level1 = new String[]{
            "000000000000000000000000000000",
            "000000000000000000000000000000",
            "000000000000000000000000000000",
            "000000000000000000000000000000",
            "000000000000000000000000000000",
            "000000000000000000000000000000",
            "0000C0000000000000000000000000",
            "0001110000C0000000000000000000",
            "00000000DD1000H00000000000C000",
            "00000000000001110000C00000M000",
            "000C01110000H0000001110001100E",
            "111111110011110001111100111111"
    };

    /**
     * Represents the layout for Level 2.
     */
    public static final String[] Level2 = new String[]{
            "000000000000000000000000000000",
            "000000000000000000000000000C00",
            "000000000000000000000000000000",
            "0000000000000000000000M1100000",
            "000000000000000000000111100000",
            "000000000M00000001111110000000",
            "0000C0000000000111111100000000",
            "00011100000000111100000000000M",
            "00000000D11000H000000000000011",
            "00000000000001110000C000000000",
            "000C0111000C00000001110001100E",
            "111111110011110001111100111111"
    };

    /**
     * Represents the layout for Level 3.
     */
    public static final String[] Level3 = new String[]{
            "000000000000000000000000000000",
            "0000000000000000000000M0000000",
            "000000000000000001111111110000",
            "000H00000000001111111111100000",
            "001110000001111111111000000000",
            "000000001111000000000000000000",
            "000C00001111000000000000000000",
            "0011110011110000000000000000M0",
            "000000001111000000000000000111",
            "000000C0000001D1000C0000000000",
            "000001110000C00000M1D100011C0E",
            "111111110011110001111100111111"
    };

    /**
     * Represents the layout for Level 4.
     */
    public static final String[] Level4 = new String[]{
            "0000000000000000000000C0000000",
            "000000000000000000000111000000",
            "0000000000000000001DD1111D0000",
            "000000000000000011111111000C00",
            "000000000000001D11100000000000",
            "00000000000011100000000000000M",
            "0000C0000001110000000000001111",
            "0001110000111C0000000000000000",
            "00000000D1111110H000000000000M",
            "00000000000001111100C000000001",
            "00000111000H000000H1110001100E",
            "111111110011110001111100111111"
    };
}