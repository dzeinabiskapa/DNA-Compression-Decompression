import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

// COMPRESS comand ======================================================================================================================================
  public static String[] comp(String symbol) {
    symbol = symbol.toUpperCase();
    if (!symbol.matches("[ACGT]+")) {
      System.out.print("wrong command format");
      return new String[0];
    }

    int letterCount = symbol.length();
    String compList = "";

    // Pārvērst katru DNA burtu uz tā bināro ekvivalentu === Convert each DNA base to its binary representation
    for (char letter : symbol.toCharArray()) {
      switch (letter) {
        case 'A':
          compList += "00";
          break;
        case 'C':
          compList += "01";
          break;
        case 'G':
          compList += "10";
          break;
        case 'T':
          compList += "11";
          break;
      }
    }

    ArrayList<String> resultComp = new ArrayList<>();

    //BIN to HEX
    while (!compList.equals("")) {
      if (compList.length() < 8) {
        int zeroCount = 8 - compList.length();
        byte shortComp = Byte.parseByte(compList, 2);
        shortComp = (byte) (shortComp << zeroCount);
        String hex_shortComp = Integer.toHexString(shortComp & 0xFF).toUpperCase();
        resultComp.add(hex_shortComp);
        break;
      } else {
        String strLongComp = compList.substring(0, 8);
        compList = compList.substring(8);
        byte longComp = Byte.parseByte(strLongComp, 2);
        String hex_longComp = Integer.toHexString(longComp & 0xFF).toUpperCase();
        resultComp.add(hex_longComp);
      }
    }

    resultComp.add(0, Integer.toString(letterCount));
    return resultComp.toArray(new String[0]);
  }

  private static String byteToBinaryString(byte b) {
    StringBuilder sb = new StringBuilder();
    for (int i = 7; i >= 0; i--) {
      sb.append((b >> i) & 1);
    }
    return sb.toString();
  }

// DECOMPRESS comand ======================================================================================================================================
  public static String[] decomp(String parts) {
    String[] inputParts = parts.split(" ");
    byte[] byteList = new byte[inputParts.length];

    for (int i = 0; i < inputParts.length; i++) {
      try {
        byteList[i] = Byte.parseByte(inputParts[i]);
      } catch (NumberFormatException e) {
        System.out.println("wrong command format");
        return new String[0];
      }
    }

    byte elementCount = byteList[0];
    List<String> hexList = new ArrayList<>();

    for (byte b : byteList) {
      String hex = Integer.toHexString(b & 0xFF);
      hexList.add(hex.toUpperCase());
    }

    StringBuilder longBinString = new StringBuilder();

    for (int i = 1; i < byteList.length; i++) {
      byte b = byteList[i];
      String binString = byteToBinaryString(b);
      longBinString.append(binString);
    }

    int decodeLength = (elementCount * 2) - 1;
    StringBuilder DNAstring = new StringBuilder();

    // binārās vērtības atgriezt uz tām atbilstošo DNS burtu === binary representation to DNA bases
    for (int i = 0; i < decodeLength; i += 2) {
      String pair = longBinString.substring(i, i + 2);
      switch (pair) {
        case "00":
          DNAstring.append("A");
          break;
        case "01":
          DNAstring.append("C");
          break;
        case "10":
          DNAstring.append("G");
          break;
        case "11":
          DNAstring.append("T");
          break;
      }
    }

    String hexString = String.join(" ", hexList) + " ";
    return new String[]{hexString, DNAstring.toString()};
  }

// main ============================================================================================================================
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String command = sc.nextLine();

    while (!command.equals("exit")) {
      String[] splitCommand = command.split(" ", 2);

      if (splitCommand[0].equals("comp")) {
        String compInput = splitCommand[1];
        String[] result = comp(compInput);
        String resultString = String.join(" ", result).trim();
        System.out.println(resultString);

      } else if (command.equals("about")) {
        System.out.println("Author: Džeina Bīskapa");

      } else if (splitCommand[0].equals("decomp")) {
        String splitLength = splitCommand[1];
        String[] splitBytes = splitLength.split(" ", 2);
        int byteCount = Integer.valueOf(splitBytes[0]);
        String[] elements = command.split(" ");
        int decompLength = elements.length - 2;

        if (decompLength == byteCount) {
          String decompInput = splitBytes[1];
          String[] result = decomp(decompInput);
          for (String s : result) {
            System.out.println(s);
          }
        } else {
          System.out.println("wrong command format");
        }

      } else {
        System.out.println("wrong command");
      }

      command = sc.nextLine();
    }

    sc.close();
  }
}