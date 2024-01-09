import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EasyIn {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static char getChar() {
        try {
            return reader.readLine().charAt(0);
        } catch (IOException e) {
            e.printStackTrace();
            return '\0';
        }
    }

    public static int getInt() {
        try {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void pause(String message) {
        System.out.println(message);
        try {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  public static String getString() {
      try {
          return reader.readLine();
      } catch (IOException e) {
          e.printStackTrace();
          return "";
      }
  }

  public static double getDouble() {
      try {
          return Double.parseDouble(reader.readLine());
      } catch (IOException | NumberFormatException e) {
          e.printStackTrace();
          return 0.0;
      }
  }
}
