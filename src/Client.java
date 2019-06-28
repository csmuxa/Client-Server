import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client  {
    private Socket clientSocket;
    private BufferedReader reader;
    private BufferedWriter writer;

    private void initClient(String ip,int port){
        try{
            clientSocket=new Socket(ip,port);
            reader=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer=new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));}
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void start(String ip,int port,String tokenAsJSON)throws Exception{
        initClient(ip,port);

        writer.write(tokenAsJSON);
        writer.newLine();
        writer.flush();



        writer.close();
        reader.close();
        clientSocket.close();
    }


    public static void main(String[] args) throws Exception{

        Client client=new Client();
        client.start("192.168.0.105",8000,"{ {merchantName}:{Azza}, {product}:{Profitrol} ,{price}:{34}}");
    }
}

