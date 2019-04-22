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
  public static ArrayList<String> ArList(ArrayList<String> OldList){
    ArrayList<String> NewList = new ArrayList<String>();
    for(int i = 0;i < OldList.size();i++){
      NewList.add(OldList.get(i));
    }
    return NewList;
  }

  //Dá return a false caso seja um PureSet de modo a parar o ciclo da função insert
  public static boolean PureSet(ArrayList<String> TheNode){
    String Play = "";
    int i,j = 0,x = 0;
    for(i = 0;true;i++){
      if(TheNode.get(0).charAt(i) == ',') j = i+1;
      if(i == TheNode.get(0).length()-1){
        break;
      }
    }
    for(;j != TheNode.get(0).length();j++){
      Play = Play.concat(Character.toString(TheNode.get(0).charAt(j)));
    }
    for(i = 1;i < TheNode.size();i++){
      for(j = 0;true;j++){
        if(TheNode.get(i).charAt(j) == ',') x = j+1;
        if(j == TheNode.get(i).length()-1){
          break;
        }
      }
      for(j = 0;x != TheNode.get(i).length();x++,j++){
        if(Play.charAt(j) != TheNode.get(i).charAt(x)) return true;
      }
    }
    return false;
  }

  public static String InsertInfo(ArrayList<String> DataFile,String NewData,String types){
    ArrayList<String> TheNode = ArList(DataFile);
    int i,j = 0,x;
    for(j = 0; NewData.charAt(j) != ',';j++){}
    while(PureSet(TheNode)){
      String Play = "";
      if(j >= NewData.length()-1) return NewData;
      j++;
      for(;NewData.charAt(j) != ',';j++){
        Play = Play.concat(Character.toString(NewData.charAt(j)));
      }
      ArrayList<String> temp = ArList(TheNode);
      TheNode.clear();
      int counter1 = 0,counter2 = 0;;
      for(i = 0; i < j; i++){
        if(NewData.charAt(i) == ',') counter1++;
      }
      for(i = 0; i < temp.size();i++){
        for(x = 0,counter2 = 0;counter2 < counter1;x++){
          if(temp.get(i).charAt(x) == ',') counter2++;
        }
        String Option = "";
        for(;temp.get(i).charAt(x) != ',';x++){
          Option = Option.concat(Character.toString(temp.get(i).charAt(x)));
        }
        if(Play.compareTo(Option) == 0){
          TheNode.add(temp.get(i));
        }
      }
      if(TheNode.isEmpty())TheNode = ArList(temp);
    }
    for(i = 0;true;i++){
      if(TheNode.get(0).charAt(i) == ',') j = i+1;
      if(i == TheNode.get(0).length()-1){
        break;
      }
    }
    for(;j != TheNode.get(0).length();j++){
      NewData = NewData.concat(Character.toString(TheNode.get(0).charAt(j)));
    }
    return NewData;
  }

  public static void NewInfo(ArrayList<String> DataFile, String types){
    int i,j = 0;
    String NewData = "";
    for(i = 0; !(DataFile.get(0).charAt(i) >= '0' && DataFile.get(0).charAt(i) <= '9') ;i++){
      NewData = NewData.concat(Character.toString(DataFile.get(0).charAt(i)));
    }
    NewData = NewData.concat(Integer.toString(DataFile.size()+1));
    NewData = NewData.concat(",");
    for(i = 0; types.charAt(i) != ',';i++){}
    i++;
    while(true){
      for(j = i;types.charAt(j) != ',';j++){
        if(j == types.length()-1) break;
      }
      if(j == types.length()-1) break;
      for(;i!=j;i++){
        System.out.print(types.charAt(i));
      }
      System.out.print(':');
      i++;
      NewData = NewData.concat(stdin.next() + ",");
    }
    NewData = InsertInfo(DataFile,NewData,types);
    if(NewData.charAt(NewData.length()-1) == ','){
        System.out.println("Decissão Impossível!");
        return;
    }
    System.out.println("A sua nova Informação:" + NewData);
    DataFile.add(NewData);
    return;
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
