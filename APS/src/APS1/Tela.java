package APS1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.event.*;
import java.sql.*;

public class Tela extends JFrame {
    private JTextField textf;
    private JTextField tituloVolta1;
    private JTextField tituloVolta2;
    private JTextField tituloTotal;
    private JTextField volta1texto;
    private JTextField volta2texto;
    private JTextField voltatotaltexto;
    private JTextField titulopiloto;
    private JTextField tituloequipe;
    private JTextField nomeequipe;
    private JTextField nomeusa;
    private JButton Biniciar;
    private JButton Bsalvar;
    private JButton Bconsulta;
    private Timer cronometro;
    private int milessimo = 0;
    private int segundo = 0;
    private int minuto = 0;
    private int volta1;
    private int volta2;

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Clipboard clipboard = toolkit.getSystemClipboard();


    public Tela() {


        setSize(500, 300);
        setTitle("Cronômetro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        getContentPane().setBackground(Color.black);
        setLocationRelativeTo(null);
        setLayout(null);

        Biniciar = new JButton("INICIAR");
        Bsalvar = new JButton("SALVAR");
        Bconsulta = new JButton("CONSULTAR");

        Biniciar.setBounds(80, 65, 110, 20);
        Biniciar.setFont(new Font("Arial", Font.BOLD, 12));
        Biniciar.setBackground(new Color(128, 128, 128));
        Biniciar.setForeground(new Color(255, 255, 255));
        add(Biniciar);

        Bsalvar.setBounds(200, 65, 110, 20);
        Bsalvar.setFont(new Font("Arial", Font.BOLD, 12));
        Bsalvar.setBackground(new Color(128, 128, 128));
        Bsalvar.setForeground(new Color(255, 255, 255));
        add(Bsalvar);

        Bconsulta.setBounds(320, 65, 110, 20);
        Bconsulta.setFont(new Font("Arial", Font.BOLD, 12));
        Bconsulta.setBackground(new Color(128, 128, 128));
        Bconsulta.setForeground(new Color(255, 255, 255));
        add(Bconsulta);


        textf = new JTextField();
        textf.setBounds(150, 25, 200, 30);
        textf.setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
        textf.setHorizontalAlignment(JTextField.CENTER);
        textf.setEditable(false);
        textf.setBorder(null);
        textf.setText("00:00:000");
        add(textf);

        tituloVolta1 = new JTextField("Volta 1");
        tituloVolta1.setBounds(155, 105, 100, 20);
        tituloVolta1.setEditable(false);
        tituloVolta1.setBorder(null);
        tituloVolta1.setBackground(new Color(128, 128, 128));
        tituloVolta1.setHorizontalAlignment(JTextField.CENTER);
        add(tituloVolta1);

        tituloVolta2 = new JTextField("Volta 2");
        tituloVolta2.setBounds(155, 130, 100, 20);
        tituloVolta2.setEditable(false);
        tituloVolta2.setBorder(null);
        tituloVolta2.setBackground(new Color(128, 128, 128));
        tituloVolta2.setHorizontalAlignment(JTextField.CENTER);
        add(tituloVolta2);

        tituloTotal = new JTextField("Tempo Total ");
        tituloTotal.setBounds(155, 155, 100, 20);
        tituloTotal.setEditable(false);
        tituloTotal.setBorder(null);
        tituloTotal.setBackground(new Color(128, 128, 128));
        tituloTotal.setHorizontalAlignment(JTextField.CENTER);
        add(tituloTotal);

        volta1texto = new JTextField();
        volta1texto.setBounds(260, 105, 100, 20);
        volta1texto.setEditable(false);
        volta1texto.setHorizontalAlignment(JTextField.CENTER);
        add(volta1texto);

        volta2texto = new JTextField();
        volta2texto.setBounds(260, 130, 100, 20);
        volta2texto.setEditable(false);
        volta2texto.setHorizontalAlignment(JTextField.CENTER);
        add(volta2texto);

        voltatotaltexto = new JTextField();
        voltatotaltexto.setBounds(260, 155, 100, 20);
        voltatotaltexto.setEditable(false);
        voltatotaltexto.setHorizontalAlignment(JTextField.CENTER);
        add(voltatotaltexto);

        titulopiloto = new JTextField("Nome do Piloto:");
        titulopiloto.setBounds(2, 245, 90, 20);
        titulopiloto.setFont(new Font("Arial", Font.BOLD, 12));
        titulopiloto.setEditable(false);
        titulopiloto.setBackground(Color.black);
        titulopiloto.setForeground(Color.WHITE);
        titulopiloto.setBorder(null);
        add(titulopiloto);

        nomeusa = new JTextField();
        nomeusa.setBounds(95, 244, 200, 20);
        nomeusa.setEditable(false);
        nomeusa.setBackground(Color.black);
        nomeusa.setForeground(Color.white);
        nomeusa.setBorder(null);
        add(nomeusa);


        nomeequipe = new JTextField("Nome da Equipe:");
        nomeequipe.setBounds(2, 227, 96, 20);
        nomeequipe.setFont(new Font("Arial", Font.BOLD, 12));
        nomeequipe.setEditable(false);
        nomeequipe.setBackground(Color.black);
        nomeequipe.setForeground(Color.WHITE);
        nomeequipe.setBorder(null);
        add(nomeequipe);

        nomeequipe = new JTextField();
        nomeequipe.setBounds(100, 227, 200, 20);
        nomeequipe.setEditable(false);
        nomeequipe.setBackground(Color.black);
        nomeequipe.setForeground(Color.white);
        nomeequipe.setBorder(null);
        add(nomeequipe);


        Biniciar.addActionListener(this::btniniciar);
        Bsalvar.addActionListener(this::btnsalvar);
        Bconsulta.addActionListener(this::mostrarDadosDoBanco);

        setVisible(true);
        String usu = JOptionPane.showInputDialog(null, "Digite o nome do piloto", "CRONOMETRO", JOptionPane.INFORMATION_MESSAGE);
        nomeusa.setText(usu);
        String equipe = JOptionPane.showInputDialog(null, "Digite o nome da Equipe", "CRONOMETRO", JOptionPane.INFORMATION_MESSAGE);
        nomeequipe.setText(equipe);


        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            @Override
            public void eventDispatched(AWTEvent event) {
                if (event instanceof KeyEvent) {
                    KeyEvent tecla = (KeyEvent) event;
                    if (tecla.getKeyCode() == KeyEvent.VK_PRINTSCREEN) {
                        Window[] janelas = Window.getWindows();
                        for (Window janela : janelas) {
                            if (janela instanceof JFrame) {
                                ((JFrame) janela).setExtendedState(JFrame.ICONIFIED);
                            }
                        }
                    }
                }
            }
        }, AWTEvent.KEY_EVENT_MASK);
        Bsalvar.setEnabled(false);
    }

    private void cronometro() {
        textf.setText(String.format("%02d:%02d:%03d", minuto, segundo, milessimo));
    }

    private void btniniciar(ActionEvent e) {
        if (cronometro == null) {
            cronometro = new Timer(1, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    milessimo++;
                    if (milessimo == 65) {
                        milessimo = 0;
                        segundo++;
                    }
                    if (segundo == 60) {
                        segundo = 0;
                        minuto++;
                    }
                    cronometro();
                }
            });
            cronometro.start();
            volta1texto.setText(null);
            volta2texto.setText(null);
            Bsalvar.setEnabled(true);
        }
    }

    private void btnsalvar(ActionEvent e) {
        if (volta1 == 0) {
            volta1 = minuto * 60000 + segundo * 1000 + milessimo;
            volta1texto.setText(formatarCronometro(volta1));
        } else if (volta2 == 0) {
            volta2 = (minuto * 60000 + segundo * 1000 + milessimo) - volta1;
            volta2texto.setText(formatarCronometro(volta2));

            int tempovoltatotal = volta2 + volta1;
            voltatotaltexto.setText(formatarCronometro(tempovoltatotal));

            String strVolta1 = formatarCronometro(volta1);
            String strVolta2 = formatarCronometro(volta2);
            String strTempottl = formatarCronometro(tempovoltatotal);

            if (volta2 != 0) {
                cronometro.stop();
                Biniciar.setEnabled(false);
                Bsalvar.setEnabled(false);
                Conectar.insertData(strVolta1, strVolta2, strTempottl, nomeusa, nomeequipe);
            }
        }
    }

    private void mostrarDadosDoBanco(ActionEvent e) {
        DefaultTableModel model = DBManager.getAllData();
        JTable tabela = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabela);
        JFrame frame = new JFrame("Dados do Banco de Dados");
        frame.add(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }

    private String formatarCronometro(long tempoMilissegundos) {
        long minutos = tempoMilissegundos / 60000;
        long segundos = (tempoMilissegundos % 60000) / 1000;
        long millis = tempoMilissegundos % 1000;
        return String.format("%02d:%02d:%03d", minutos, segundos, millis);
    }

    public JTextField getTextf() {
        return textf;
    }

    public void setTextf(JTextField textf) {
        this.textf = textf;
    }

    public JTextField getTituloVolta1() {
        return tituloVolta1;
    }

    public void setTituloVolta1(JTextField tituloVolta1) {
        this.tituloVolta1 = tituloVolta1;
    }

    public JTextField getTituloVolta2() {
        return tituloVolta2;
    }

    public void setTituloVolta2(JTextField tituloVolta2) {
        this.tituloVolta2 = tituloVolta2;
    }

    public JTextField getTituloTotal() {
        return tituloTotal;
    }

    public void setTituloTotal(JTextField tituloTotal) {
        this.tituloTotal = tituloTotal;
    }

    public JTextField getVolta1texto() {
        return volta1texto;
    }

    public void setVolta1texto(JTextField volta1texto) {
        this.volta1texto = volta1texto;
    }

    public JTextField getVolta2texto() {
        return volta2texto;
    }

    public void setVolta2texto(JTextField volta2texto) {
        this.volta2texto = volta2texto;
    }

    public JTextField getVoltatotaltexto() {
        return voltatotaltexto;
    }

    public void setVoltatotaltexto(JTextField voltatotaltexto) {
        this.voltatotaltexto = voltatotaltexto;
    }

    public JTextField getTitulopiloto() {
        return titulopiloto;
    }

    public void setTitulopiloto(JTextField titulopiloto) {
        this.titulopiloto = titulopiloto;
    }

    public JTextField getTituloequipe() {
        return tituloequipe;
    }

    public void setTituloequipe(JTextField tituloequipe) {
        this.tituloequipe = tituloequipe;
    }

    public JTextField getNomeequipe() {
        return nomeequipe;
    }

    public void setNomeequipe(JTextField nomeequipe) {
        this.nomeequipe = nomeequipe;
    }

    public void minimizarJanelaMain() {
        setState(Frame.ICONIFIED);
    }

    public JTextField getNomeusa() {
        return nomeusa;
    }

    public void setNomeusa(JTextField nomeusa) {
        this.nomeusa = nomeusa;
    }

    public JButton getBiniciar() {
        return Biniciar;
    }

    public void setBiniciar(JButton biniciar) {
        Biniciar = biniciar;
    }

    public JButton getBsalvar() {
        return Bsalvar;
    }

    public void setBsalvar(JButton bsalvar) {
        Bsalvar = bsalvar;
    }

    public JButton getBconsulta() {
        return Bconsulta;
    }

    public void setBconsulta(JButton bconsulta) {
        Bconsulta = bconsulta;
    }

    public Timer getTimer() {
        return cronometro;
    }

    public void setTimer(Timer cronometro) {
        this.cronometro = cronometro;
    }

    public int getMilessimo() {
        return milessimo;
    }

    public void setMilessimo(int milessimo) {
        this.milessimo = milessimo;
    }

    public int getSegundo() {
        return segundo;
    }

    public void setSegundo(int segundo) {
        this.segundo = segundo;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getVolta1() {
        return volta1;
    }

    public void setVolta1(int volta1) {
        this.volta1 = volta1;
    }

    public int getVolta2() {
        return volta2;
    }

    public void setVolta2(int volta2) {
        this.volta2 = volta2;
    }
    public static void main(String[] args) {
        new Tela();
    }
}

class DBManager {
    private static final String DB_URL = "jdbc:mysql://localhost/aps";
    private static final String USER = "root";
    private static final String PASS = "";

    public static DefaultTableModel getAllData() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nome da Equipe");
        modelo.addColumn("Nome do Piloto");
        modelo.addColumn("Volta 1");
        modelo.addColumn("Volta 2");
        modelo.addColumn("Tempo Total");

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT * FROM cronometro ORDER BY tempo_total";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String nomeEquipe = rs.getString("nomeequipe");
                    String nomePiloto = rs.getString("nomepiloto");
                    String volta1 = rs.getString("volta1");
                    String volta2 = rs.getString("volta2");
                    String tempoTotal = rs.getString("tempo_total");
                    modelo.addRow(new Object[]{nomeEquipe, nomePiloto, volta1, volta2, tempoTotal});
                 
                }
                
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return modelo;
    }
    
    
}
