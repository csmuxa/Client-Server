
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader reader;
    private BufferedWriter writer;



    public void start()throws Exception{
        serverSocket=new ServerSocket(8000);
        while (true){
            clientSocket=serverSocket.accept();
            reader=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer=new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            String responseToken=reader.readLine();
            System.out.println(responseToken);

            writer.write("Server said : You sent me " +responseToken);
            writer.newLine();
            writer.flush();

            writer.close();
            reader.close();
            clientSocket.close();

        }


    }

    public static void main(String[] args)throws Exception{
        Server server=new Server();
        server.start();


    }
}
