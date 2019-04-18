import java.util.*;
import java.io.*;

public class main{
  static Scanner stdin = new Scanner(System.in);

  public static void printList(ArrayList<String> DataFile, String types){
    System.out.println("types: " + types);
    for(int i=0; i<DataFile.size(); i++)
      System.out.println("DataFile: " + DataFile.get(i));
  }

  public static ArrayList<String> ReadFile(String CSV_File){
    BufferedReader br = null;
    String line = "";
    String csvSplitBy = ",";
    ArrayList<String> DataFile = new ArrayList<String>();
    try {
      br = new BufferedReader(new FileReader(CSV_File));
      String types = br.readLine();
      String format[] = types.split(csvSplitBy);
      //System.out.println("FORMATO: " + format.length);
      while ((line = br.readLine()) != null) {
        DataFile.add(line);
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
    return DataFile;
  }

  public static String Readtypes(String CSV_File){
    String types = "";
    BufferedReader br = null;
    String line = "";
    String csvSplitBy = ",";
    ArrayList<String> DataFile = new ArrayList<String>();
    try {
      br = new BufferedReader(new FileReader(CSV_File));
      types = br.readLine();
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
    return types;
  }

  public static void Menu(ArrayList<String> DataFile, String types){
    System.out.println("Menu");
    System.out.println("1 -> Print Tabela");
    System.out.println("2 -> Inserir nova Informação");
    System.out.println("3 -> Mudar de Ficheiro");
    System.out.println("4 -> Sair");
    System.out.print("Opção:");
    int escolha = stdin.nextInt();
    switch(escolha){
      case 1:
        printList(DataFile,types);
        Menu(DataFile,types);
        break;
      case 2:
        NewInfo(DataFile, types);
        Menu(DataFile,types);
        break;
      case 3:
        System.out.print("Indique o nome do seu Ficheiro: ");
        String CSV_File = stdin.next();
        DataFile = ReadFile(CSV_File);
        types = Readtypes(CSV_File);
        Menu(DataFile,types);
        break;
      case 4:
        return;
      default:
        System.out.println("Opção Inválida!");
        Menu(DataFile,types);
    }
  }

  public static void NewInfo(ArrayList<String> DataFile, String types){

  }

  public static void main(String[] args) {
    System.out.print("Indique o nome do seu Ficheiro: ");
    String CSV_File = stdin.next();
    ArrayList<String> DataFile = new ArrayList<String>();
    DataFile = ReadFile(CSV_File);
    String types = Readtypes(CSV_File);
    Menu(DataFile,types);
  }
}
