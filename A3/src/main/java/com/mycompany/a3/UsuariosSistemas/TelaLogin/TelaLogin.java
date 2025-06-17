package com.mycompany.a3.UsuariosSistemas.TelaLogin;

import com.mycompany.a3.Servicos.TelaPesquisaServicos.TelaPesquisaServicos;
import com.mycompany.a3.Servicos.TelasContrato.TelaRecebeContratoPrestador;
import com.mycompany.a3.UsuariosSistemas.TelaCadastroCliente.TelaCadastroCliente;
import com.mycompany.a3.UsuariosSistemas.TelaCadastroPrestador.TelaCadastroPrestador;
import com.mycompany.a3.UsuariosSistemas.UsuarioSistema;
import com.mycompany.a3.config.configConexao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TelaLogin extends JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaLogin.class.getName());
    public UsuarioSistema usuarioSistema;

    public TelaLogin() {
        initComponents();
        configTela();
        eventos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnFields = new javax.swing.JPanel();
        lblCpf = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblSenha = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        btnEntrar = new javax.swing.JButton();
        lblTituloTela = new javax.swing.JLabel();
        btnCadPrestador = new javax.swing.JButton();
        btnCadConsumidor = new javax.swing.JButton();
        lblAlternantivaCadastro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblCpf.setText("Digite seu email ou CPF");

        lblSenha.setText("Senha");

        btnEntrar.setText("Entrar");

        lblTituloTela.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTituloTela.setText("Login");

        btnCadPrestador.setText("Cadastrar Prestador");

        btnCadConsumidor.setText("Cadastrar Consumidor");

        lblAlternantivaCadastro.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblAlternantivaCadastro.setText("Ou...");

        javax.swing.GroupLayout jpnFieldsLayout = new javax.swing.GroupLayout(jpnFields);
        jpnFields.setLayout(jpnFieldsLayout);
        jpnFieldsLayout.setHorizontalGroup(
            jpnFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnFieldsLayout.createSequentialGroup()
                .addGroup(jpnFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpnFieldsLayout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(lblTituloTela, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                    .addGroup(jpnFieldsLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jpnFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCpf))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpnFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                            .addComponent(txtSenha))))
                .addGroup(jpnFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnFieldsLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jpnFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCadPrestador, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCadConsumidor, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnFieldsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAlternantivaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))))
            .addGroup(jpnFieldsLayout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnFieldsLayout.setVerticalGroup(
            jpnFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnFieldsLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jpnFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTituloTela)
                    .addComponent(lblAlternantivaCadastro))
                .addGap(18, 18, 18)
                .addGroup(jpnFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCpf)
                    .addComponent(btnCadPrestador))
                .addGap(30, 30, 30)
                .addGroup(jpnFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSenha)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCadConsumidor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(btnEntrar)
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnFields, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnFields, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(157, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelaLogin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadConsumidor;
    private javax.swing.JButton btnCadPrestador;
    private javax.swing.JButton btnEntrar;
    private javax.swing.JPanel jpnFields;
    private javax.swing.JLabel lblAlternantivaCadastro;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblTituloTela;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables

    private void configTela(){
        setLocationRelativeTo(null); // centraliza tela
    }
    
    private void eventos(){
       clickBtnEntrar();
       clickBtnCadPrestadorServico();
       clickBtnCadConsumidor();
    }
    
    private void clickBtnEntrar(){
        btnEntrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validaLogin()){
                    dispose();
                    if(usuarioSistema.getTipoUsuario() == 1){
                        new TelaRecebeContratoPrestador(usuarioSistema).setVisible(true);
                    }else{
                        new TelaPesquisaServicos(usuarioSistema).setVisible(true);
//                          new TelaEnviaContratoCliente(usuarioSistema).setVisible(true);
                    }              
                } else{
                    JOptionPane.showMessageDialog(null,  "Login ou senha de usuario incorreto",  
                    "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void clickBtnCadPrestadorServico(){
        btnCadPrestador.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new TelaCadastroPrestador().setVisible(true);
            }
        });
    }
    
    private void clickBtnCadConsumidor(){
        btnCadConsumidor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    
                dispose();
                new TelaCadastroCliente().setVisible(true);
            }
        });
    }
    
    private boolean validaLogin(){

        String sql = "SELECT ID, CPF, SENHA, TIPOUSUARIO, EMAIL" +
                    " FROM USUARIOSSISTEMA" + 
                    " WHERE" +
                    " (CPF = ? AND SENHA = ?)" +
                    " OR" +
                    " (EMAIL = ? AND SENHA = ?)";
    
        try (Connection con = configConexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);){
        
                ps.setString(1, txtEmail.getText().trim());
                ps.setString(2, txtSenha.getText().trim());
                ps.setString(3, txtEmail.getText().trim());
                ps.setString(4, txtSenha.getText().trim());
            
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    usuarioSistema = new UsuarioSistema(rs.getInt("ID"), 
                            rs.getInt("TIPOUSUARIO"), rs.getString("EMAIL"),
                            rs.getString("CPF"), rs.getString("SENHA"));
                    return true;
                }
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
}

