import java.util.*;
import java.io.*;

public class main{
  static Scanner stdin = new Scanner(System.in);

  public static void printList(ArrayList<String> data, String type){
    System.out.println("Type: " + type);
    for(int i=0; i<data.size(); i++)
      System.out.println("Data: " + data.get(i));
  }

  public static ArrayList<String> ReadFile(String csvFile){
    BufferedReader br = null;
    String line = "";
    String csvSplitBy = ",";
    ArrayList<String> data = new ArrayList<String>();
    try {
      br = new BufferedReader(new FileReader(csvFile));
      String type = br.readLine();
      String format[] = type.split(csvSplitBy);
      System.out.println("FORMATO: " + format.length);
      while ((line = br.readLine()) != null) {
        data.add(line);
      }
    } catch (FileNotFoundException e) {
      System.out.println("File does not exist!");
      System.exit(0);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(0);
    } finally {
      if(br!=null){
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return data;
  }

  public static String ReadType(String csvFile){
    String type = "";
    BufferedReader br = null;
    String line = "";
    String csvSplitBy = ",";
    ArrayList<String> data = new ArrayList<String>();
    try {
      br = new BufferedReader(new FileReader(csvFile));
      type = br.readLine();
    } catch (FileNotFoundException e) {
      System.out.println("File does not exist!");
      System.exit(0);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(0);
    } finally {
      if(br!=null){
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return type;
  }

  public static void main(String[] args) {
    System.out.print("Ficheiro: ");
    String csvFile = stdin.next();
    ArrayList<String> data = new ArrayList<String>();
    data = ReadFile(csvFile);
    String type = ReadType(csvFile);
    printList(data,type);
  }
}
