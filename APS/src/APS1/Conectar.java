package APS1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;


public class Conectar {
	private static final String DB_URL = "jdbc:mysql://localhost/aps";
	private static final String USER = "root";
	private static final String PASS = "";

	public static void insertData(String volta1, String volta2, String tempoTotal, JTextField nomeusa, JTextField nomeequipe) {
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
			String sql = "INSERT INTO cronometro (nomeequipe, nomepiloto,volta1, volta2, tempo_total) VALUES (?, ?, ?, ?, ?)";
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, nomeequipe.getText());
				pstmt.setString(2, nomeusa.getText());
				pstmt.setString(3, formatarTempo(volta1));
				pstmt.setString(4, formatarTempo(volta2));
				pstmt.setString(5, formatarTempo(tempoTotal));
				pstmt.executeUpdate();
			}
			JOptionPane.showMessageDialog(null, "Registro concluído com sucesso", "BANCO DE DADOS",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir os dados no banco de dados.", "Banco DE DADOS",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
	}

	private static String formatarTempo(String tempo) {

		if (tempo.matches("\\d+")) {

			return "00:00:" + tempo;
		} else {
			// Caso contrário, retorna o tempo como está
			return tempo;
		}
	}
}