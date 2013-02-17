package br.com.simplechat.server;

import br.com.simplechat.common.DataPackage;
import br.com.simplechat.common.PackageHeader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Henrique
 */
public class Connection {

    private ChatServer chatServer;
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private String user;

    public Connection(ChatServer chatServer, Socket socket, String user) throws IOException {
        this.user = user;
        this.socket = socket;
        this.chatServer = chatServer;
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.flush();
        objectInputStream = new ObjectInputStream(socket.getInputStream());

        listen();
    }

    private DataPackage read() throws IOException {
        try {
            System.out.println("Aguardando mensagem...");
            DataPackage dataPackage = (DataPackage) objectInputStream.readObject();
            System.out.println("Mensagem Recebida.");
            return dataPackage;
        } catch (Exception e) {
            System.out.printf("Erro ao ler dados: [%s]", e.getMessage());
            throw new IOException(e);
        }
    }

    private void write(DataPackage dataPackage) throws IOException {
        objectOutputStream.writeObject(dataPackage);
        objectOutputStream.flush();
    }

    private void listen() {
        final Connection thisConnection = this;

        new Thread(new Runnable() {

            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        DataPackage dataPackage = read();
                        List<Connection> connections = chatServer.getConnections();
                        System.out.printf("Enviando a mensagem para %d usuarios.\n", connections.size());
                        for (Connection connection : connections) {
                            String user = getUser();
                            String content = dataPackage.getContent();
                            content = String.format("[%s]: %s", user, content);
                            DataPackage formattedDataPackage = new DataPackage(PackageHeader.MESSAGE, content);

                            connection.write(formattedDataPackage);
                        }

                    } catch (IOException e) {
                        System.out.println("Erro ao ler a mensagem.");
                        System.out.printf("Erro ao receber a mensagem: [%s].\n", e.getMessage());
                        Thread.currentThread().interrupt();
                    }
                }
                try {
                    chatServer.getConnections().remove(thisConnection);
                    broadcastLogoff();
                } catch (IOException ex) {
                    System.out.println("Erro ao enviar broadcast de logoff");
                }
            }
        }).start();
    }

    public Socket getSocket() {
        return socket;
    }

    public String getUser() {
        return user;
    }

    public void broadcastLogin() throws IOException {
        List<Connection> connections = this.chatServer.getConnections();

        //Coloca o usuario como primeiro na lista
        DataPackage dataPackage = new DataPackage(PackageHeader.USER_LOGIN, this.user);
        this.write(dataPackage);
        for (Connection connection : connections) {
            if (!connection.equals(this)) {
                //Preenche o restante da lista.
                DataPackage localDataPackage = new DataPackage(PackageHeader.USER_LOGIN, connection.getUser());
                this.write(localDataPackage);
                //Envia para os outros usuarios a informacao de login.
                DataPackage remoteDataPackage = new DataPackage(PackageHeader.USER_LOGIN, this.user);
                connection.write(remoteDataPackage);
            }
        }
    }

    public void broadcastLogoff() throws IOException {
        List<Connection> connections = this.chatServer.getConnections();
        for (Connection connection : connections) {
            if (!connection.equals(this)) {
                DataPackage remoteDataPackage = new DataPackage(PackageHeader.USER_LOGOFF, this.user);
                connection.write(remoteDataPackage);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Connection other = (Connection) obj;
        if (!Objects.equals(this.chatServer, other.chatServer)) {
            return false;
        }
        if (!Objects.equals(this.socket, other.socket)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.chatServer);
        hash = 97 * hash + Objects.hashCode(this.socket);
        hash = 97 * hash + Objects.hashCode(this.user);
        return hash;
    }
}