package br.com.simplechat.client;

import br.com.simplechat.common.DataPackage;
import br.com.simplechat.common.PackageHeader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ChatClient extends Thread {

    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public ChatClient() {
    }

    public boolean connect(String ip, int port) throws IOException {
        InetAddress inetAddress = InetAddress.getByName(ip);
        InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddress, port);
        System.out.println("Conectando ao servidor...");
        socket = new Socket();
        socket.connect(inetSocketAddress, 10000);
        System.out.println("Conectado.");

        if (socket.isConnected()) {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.flush();
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            return true;

        } else {
            return false;
        }
    }

    public DataPackage read() throws IOException {
        try {
            System.out.println("Aguardando mensagem...");
            DataPackage dataPackage = (DataPackage) objectInputStream.readObject();

            System.out.println("Mensagem recebida.");

            return dataPackage;
        } catch (Exception e) {
            System.out.printf("Erro ao receber informacoes do servidor: %s\n", e.getMessage());
            throw new IOException(e);
        }
    }

    private void write(DataPackage dataPackage) throws IOException {
        try {
            objectOutputStream.writeObject(dataPackage);
            objectOutputStream.flush();

        } catch (IOException e) {
            System.out.printf("Erro ao enviar informacoes do servidor: %s\n", e.getMessage());
            throw new IOException(e);
        }
    }

    public void send(String message) throws IOException {

        System.out.println("Enviando mensagem para o servidor...");
        DataPackage dataPackage = new DataPackage(PackageHeader.MESSAGE, message);
        this.write(dataPackage);
        System.out.printf("Mensagem [%s] enviada.\n", message);
    }

    public void register(String user) throws IOException {

        System.out.println("Enviando nome de usuario para o servidor...");
        DataPackage dataPackage = new DataPackage(PackageHeader.USER_LOGIN, user);
        this.write(dataPackage);
        System.out.printf("Nome de usuario [%s] enviado.\n", dataPackage.getContent());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream.flush();
    }
}
