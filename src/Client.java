import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client  {
    private Socket clientSocket;
    private BufferedReader reader;
    private BufferedWriter writer;

    private void initClient(InetAddress inetAddress,int port){
        try{
            clientSocket=new Socket(inetAddress,port);
            reader=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer=new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));}
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void start(InetAddress inetAddress,int port,String tokenAsJSON)throws Exception{
        initClient(inetAddress,port);

        writer.write(tokenAsJSON);
        writer.newLine();
        writer.flush();
        System.out.println(reader.readLine());



        writer.close();
        reader.close();
        clientSocket.close();
    }


    public static void main(String[] args) throws Exception{
        String JSONToken="{ {merchantName}:{Azza}, {product}:{Profitrol} ,{price}:{34}}";

        Client client=new Client();

        System.out.println();
        client.start(InetAddress.getByName("DESKTOP-C702F27"),8000,JSONToken);
    }
}

