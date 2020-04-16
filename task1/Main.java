import java.io.*;

public class Main
{
	public static void main(String[] args) throws Exception {

        try(FileInputStream fin = new FileInputStream(args[0]))
        {
            int count = 0;
            int pos;

            byte[] buffer = new byte[fin.available()];
            fin.read(buffer, 0, buffer.length);
            String str = new String(buffer);

            while((pos = str.indexOf(args[1])) >= 0) {
                ++count;
                str = str.substring(pos + 1, str.length());
            }

            System.out.println("The word \"" + args[1] + "\" appears in the text " + count + " times.");

        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}