package br.com.simplechat.client.ui;

import br.com.simplechat.client.ChatClient;
import br.com.simplechat.common.DataPackage;
import br.com.simplechat.common.PackageHeader;
import br.com.simplechat.common.PropertiesUtil;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Henrique
 */
public class ChatFrame extends javax.swing.JFrame {

    private ChatClient chatClient;
    private List<String> users;

    public ChatFrame() {
        initComponents();
        users = new ArrayList<String>();
        chatClient = new ChatClient();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        registerFrame = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        userNameLabel = new javax.swing.JTextField();
        registerButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        messageLabel = new javax.swing.JLabel();
        messageTextField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        historyTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        usersTextArea = new javax.swing.JTextArea();

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        registerFrame.setMinimumSize(new java.awt.Dimension(306, 160));

        jLabel1.setText("Digite um nome:");

        userNameLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userNameLabelKeyPressed(evt);
            }
        });

        registerButton.setText("Entrar");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(registerButton)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(registerButton)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout registerFrameLayout = new javax.swing.GroupLayout(registerFrame.getContentPane());
        registerFrame.getContentPane().setLayout(registerFrameLayout);
        registerFrameLayout.setHorizontalGroup(
            registerFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        registerFrameLayout.setVerticalGroup(
            registerFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        messageLabel.setText("Mensagem:");

        messageTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                messageTextFieldKeyPressed(evt);
            }
        });

        sendButton.setText("Enviar");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        historyTextArea.setColumns(20);
        historyTextArea.setEditable(false);
        historyTextArea.setRows(5);
        historyTextArea.setFocusable(false);
        historyTextArea.setName(""); // NOI18N
        jScrollPane1.setViewportView(historyTextArea);

        usersTextArea.setColumns(20);
        usersTextArea.setEditable(false);
        usersTextArea.setRows(5);
        usersTextArea.setFocusable(false);
        jScrollPane2.setViewportView(usersTextArea);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(messageLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(messageTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendButton))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(messageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(messageLabel)
                    .addComponent(sendButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        sendMessage();
    }//GEN-LAST:event_sendButtonActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        register();
    }//GEN-LAST:event_registerButtonActionPerformed

    private void userNameLabelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userNameLabelKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            register();
        }
    }//GEN-LAST:event_userNameLabelKeyPressed

    private void messageTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_messageTextFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            sendMessage();
        }
    }//GEN-LAST:event_messageTextFieldKeyPressed

    private void listen() {
        new Thread(new Runnable() {

            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        DataPackage dataPackage = chatClient.read();
                        PackageHeader header = dataPackage.getHeader();

                        if (header == PackageHeader.MESSAGE) {
                            System.out.println(dataPackage.getContent());
                            historyTextArea.append(dataPackage.getContent());
                            historyTextArea.append("\n");
                        } else if (header == PackageHeader.USER_LOGIN) {
                            users.add(dataPackage.getContent());

                            StringBuilder userList = new StringBuilder();
                            for (String user : users) {
                                userList.append(user).append("\n");
                            }
                            usersTextArea.setText(userList.toString());
                        } else if (header == PackageHeader.USER_LOGOFF) {
                            users.remove(dataPackage.getContent());

                            StringBuilder userList = new StringBuilder();
                            for (String user : users) {
                                userList.append(user).append("\n");
                            }
                            usersTextArea.setText(userList.toString());
                        }

                    } catch (IOException ex) {
                        System.out.println("Erro ao receber mensagem.");
                        listen();
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }).start();
    }

    private void sendMessage() {
        if (messageTextField.getText() != null && !messageTextField.getText().isEmpty()) {
            try {
                String message = messageTextField.getText();
                chatClient.send(message);
                messageTextField.setText("");
            } catch (IOException ex) {
                System.out.println("Erro ao enviar mensagem.");
            }
        }
    }

    private void register() {
        try {

            Properties properties = PropertiesUtil.loadProperties("/client.properties");
            String ip = properties.getProperty("server.ip");
            int port = Integer.parseInt(properties.getProperty("server.port"));

            String userName = userNameLabel.getText();
            if (userName != null && !userName.trim().isEmpty()) {
                chatClient.connect(ip, port);
                chatClient.register(userName);
                this.listen();
                registerFrame.setVisible(false);
                this.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Digite um nome de usuário.", "Informacao", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            System.out.println("Não oi possível conectar ao servidor.");
            JOptionPane.showMessageDialog(null, "Não foi possível conectar ao servidor.", "Entrar", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.out.println("Erro ao carregar look and feel.");
        }


        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ChatFrame chatFrame = new ChatFrame();
                chatFrame.registerFrame.setVisible(true);
                chatFrame.setLocationRelativeTo(chatFrame.getRootPane());
                chatFrame.registerFrame.setLocationRelativeTo(chatFrame.getRootPane());
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea historyTextArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JTextField messageTextField;
    private javax.swing.JButton registerButton;
    private javax.swing.JFrame registerFrame;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextField userNameLabel;
    private javax.swing.JTextArea usersTextArea;
    // End of variables declaration//GEN-END:variables
}
