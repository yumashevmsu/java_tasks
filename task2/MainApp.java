import java.util.*;
import java.io.*;


public class MainApp {


    public static void main(String[] args) throws Exception {
        boolean flag = true;

        while (flag) {

            try (FileInputStream fis = new FileInputStream("C://Sport_database//database.txt")) {
                if (args.length == 0) {

                    Scanner sc = new Scanner(fis);

                    HashMap<String, String> mapInFile = new HashMap<String, String>();

                    String currentLine;
                    while(sc.hasNextLine()){
                        currentLine=sc.nextLine();
                        StringTokenizer st = new StringTokenizer(currentLine,"=",false);
                        mapInFile.put(st.nextToken(), st.nextToken());
                    }

                    int max = 0;
                    String maxday = "00.00.00";

                    for(Map.Entry<String,String> m :mapInFile.entrySet()){
                        if (Integer.parseInt(m.getValue()) >= max) {
                            max = Integer.parseInt(m.getValue());
                            maxday = m.getKey();
                        }
                    }

                    System.out.println("Record = " + max + ", at " + maxday);

                }

                if (args.length == 2) {

                    Scanner sc = new Scanner(fis);

                    HashMap<String, String> mapInFile = new HashMap<String, String>();

                    String currentLine;
                    while(sc.hasNextLine()){
                        currentLine=sc.nextLine();
                        StringTokenizer st=new StringTokenizer(currentLine,"=",false);
                        mapInFile.put(st.nextToken(),st.nextToken());
                    }

                    if (mapInFile.get(args[0]) != null) {
                        Integer count = Integer.parseInt(args[1]) + Integer.parseInt(mapInFile.get(args[0]));
                        mapInFile.put(args[0], count.toString());
                    }
                    else {
                        mapInFile.put(args[0], args[1]);
                    }
                    
                    FileOutputStream fos = new FileOutputStream("C://Sport_database//database.txt");

                    PrintWriter pw = new PrintWriter(fos);

                    for(Map.Entry<String,String> m :mapInFile.entrySet()){
                        pw.println(m.getKey()+"="+m.getValue());
                    }

                    pw.close();
                    fos.close();

                }

                fis.close();

                flag = false;
            }

            catch (FileNotFoundException ex) {
                System.out.println("--- Creating database --- Please wait ---");

                File filePath = new File("C://Sport_database");
                filePath.mkdir();
                File file = new File(filePath + "//database.txt");

                file.createNewFile();

            }

            catch (IOException ex) {
                ex.printStackTrace();
                flag = false;
            }

            catch (Throwable ex) {
                ex.printStackTrace();
                flag = false;
            }

        }
    }
}
