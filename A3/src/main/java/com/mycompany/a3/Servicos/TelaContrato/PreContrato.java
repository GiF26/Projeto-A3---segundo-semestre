package com.mycompany.a3.Servicos.TelaContrato;

import com.mycompany.a3.config.configConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;

public class PreContrato {
    private int id;
    private int idCliente;
    private int idPrestador;
    private double valor;
    private int servico;
    private Date dataIni;
    private Date dataFim;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(int idPrestador) {
        this.idPrestador = idPrestador;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getServico() {
        return servico;
    }

    public void setServico(int servico) {
        this.servico = servico;
    }

    public Date getDataIni() {
        return dataIni;
    }

    public void setDataIni(Date dataIni) {
        this.dataIni = dataIni;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public boolean insert(){
    
        String sql = "INSERT INTO PRECONTRATO (ID_PRE_CONTRATO, ID_CLIENTE, " +
                " ID_PRESTADOR, VALOR_PROPOSTO, SERVICO, DATA_INICIO_CONTRATO," +
                " DATA_FIM_CONTRATO, STATUS)" + 
                " VALUES (?,?,?,?,?,?,?,?)";
        
        try (Connection con = configConexao.getConexao(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            //1. ID CONTRATO
            ps.setInt(1, geraId());
            
            //2. ID CLIENTE 
            ps.setInt(2, getIdCliente());
            
            //3. ID PRESTADOR
            ps.setInt(3, getIdPrestador());
            
            //4. VALOR PROPOSTO
            ps.setDouble(4, getValor());
            
            //5. SERVICO
            ps.setInt(5, getServico());
            
            //6. DATA INICIO DO CONTRATO
            LocalDate dataTratadaIni = getDataIni()
                                  .toInstant()
                                  .atZone(ZoneId.systemDefault())
                                  .toLocalDate();
            ps.setDate(6, java.sql.Date.valueOf(dataTratadaIni));
            
            //7. DATA FIM DO CONTRATO
            LocalDate dataTratadaFim = getDataFim()
                                  .toInstant()
                                  .atZone(ZoneId.systemDefault())
                                  .toLocalDate();
            ps.setDate(7, java.sql.Date.valueOf(dataTratadaFim));
            
            //8. SATUS CONTRATO
            ps.setInt(8, getStatus());
            
            int linhasAfetadas = ps.executeUpdate();
            return linhasAfetadas > 0;

        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null,  "Erro no banco de dados: " + e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,  "Dados inválidos: " + e.getMessage(),
                "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            return false;
        } 
    }
    
    private int geraId(){
        String sql = "SELECT COALESCE(MAX(ID_PRE_CONTRATO), 0) + 1 FROM PRECONTRATO";
        try (Connection con = configConexao.getConexao()) {
            assert con != null;
            
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                 return rs.next() ? rs.getInt(1) : 1; 
            }
            
        } catch (SQLException e) {
            return -1; // Valor inválido que será capturado depois
        }
    }
    
}
