package br.com.simplechat.server;

import br.com.simplechat.common.DataPackage;
import br.com.simplechat.common.PropertiesUtil;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatServer extends Thread {

    private List<Connection> connections;

    public ChatServer() {
        connections = new ArrayList<Connection>();
    }

    @Override
    public void run() {
        ServerSocket serverSocket;

        try {
            Properties properties = PropertiesUtil.loadProperties("/server.properties");
            int port = Integer.parseInt(properties.getProperty("server.port"));

            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new IllegalStateException("Não foi possível iniciar o servidor.", e);
        }

        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;

        while (!currentThread().isInterrupted()) {
            try {
                System.out.println("Aguardando conexao...");
                Socket socket = serverSocket.accept();
                System.out.printf("Conexao recebida: [%s].\n", socket.getInetAddress().getHostAddress());

                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.flush();
                objectInputStream = new ObjectInputStream(socket.getInputStream());

                System.out.println("Aguardando nome do usuario...");
                DataPackage dataPackage = (DataPackage)objectInputStream.readObject();
                System.out.println("Nome do usuario recebido.");
                String user = dataPackage.getContent().trim();

                Pattern regex = Pattern.compile("%s(\\((\\d)\\))?");

                int lastIndex = 0;

                for(Connection connection : connections){
                    String connectionUser = connection.getUser();

                    Matcher matcher = regex.matcher(connectionUser);

                    if(matcher.matches()){
                        String index = matcher.group(1);

                        if(!index.trim().isEmpty()){
                            int i = Integer.parseInt(index);
                            lastIndex = i > lastIndex ? i : lastIndex;
                        }
                    }
                }

                if(lastIndex > 0){
                    user = String.format("%s(%d)", user, lastIndex + 1);
                }

                Connection connection = new Connection(this, socket, user);
                connections.add(connection);
                System.out.printf("Usuario [%s] logado.\n", user);

                connection.broadcastLogin();

            } catch (Exception e) {
                System.out.printf("Não foi possível estabelecer a conexão: %s\n", e.getMessage());
            }
        }
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    public static void main(String[] args) {
        new ChatServer().start();
    }
}
