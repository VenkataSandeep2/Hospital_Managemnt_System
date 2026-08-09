package util;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputUtil {

    private static Scanner scanner =
            new Scanner(System.in);

    public static String getString(String message) 
    {

        System.out.print(message);

        return scanner.nextLine();
    }

    public static int getInt(String message) 
    {

        while (true) {

            try {

                String input = getString(message);

                return Integer.parseInt(input);

            } catch (NumberFormatException e) 
            {

                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static BigDecimal getBigDecimal(String message)
    {

        while (true) 
        {

            try 
            {

                String input = getString(message);

                return new BigDecimal(input);

            } catch (NumberFormatException e) 
            {

                System.out.println("Please enter a valid amount.");
            }
        }
    }
}