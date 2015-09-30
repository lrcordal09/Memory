/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class Memory extends JFrame{
  
    
    int pontos = 100;   
  
    Random Naleatorio = new Random();
    int Aleatorio[] = new int [16];
    int Posicao[] = new int [16];
  
    // Barra de Ferramenta
    private JToolBar BarraFerramenta = new JToolBar();
    private JButton NovoJogo = new JButton("Novo Jogo");
       
  
    private JPanel Panel = new JPanel();
    private GridLayout Layout = new GridLayout(4,4);
    private JButton Escolha[] = new JButton[16];
  
    private JPanel Barra = new JPanel();
    private JLabel Pontuacao = new JLabel("Pontos: 100");
  
    public Memory() {
        super("Memory");
      
        for (int i=0; i<16; ++i){
            Escolha[i] = new JButton();
            Panel.add(Escolha[i]);
            Escolha[i].setVisible(true);
        }
      
        Panel.setLayout(Layout);
        add(Panel, BorderLayout.CENTER);
      
        Barra.add(Pontuacao);
        add(Barra, BorderLayout.SOUTH);
      
        Memoria Handler = new Memoria();
        for (int i=0; i<16; ++i){
            Escolha[i].addActionListener(Handler);
        }
        NovoJogo.addActionListener(Handler);
                
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);     
        this.setSize(500,500);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
  
    private class Memoria implements ActionListener{
      
        int Acertos,PClick,SClick;
        int NClick, posi, cont, pontos_Anterior, MaiorPontuacao;
        int Partidas_jogadas = 0, Vitorias = 0;
        boolean Novo = true;
        boolean Re_Ini = false;
        boolean Fim = false;                       
      
      
        public void actionPerformed(ActionEvent event){                       

          
            if (event.getSource() == NovoJogo){
                Novo = true;
                Re_Ini = false;
            }
          
            if ( Novo  == true){ 
              
                Acertos = 0;
                Partidas_jogadas++;
                pontos_Anterior = pontos;
                pontos = 100;
                NClick = 0;
                posi = 0; cont = 16;
                PClick = 0;
                SClick = 0;
              
                for (int i=0; i<16; ++i){
                    Escolha[i].setText("");
                    Escolha[i].setEnabled(true);
                }
              
                if (Re_Ini == false){
                  
                    for (int i=0; i<16; ++i){
                        Posicao[i] = i;
                    }
      
                    for (int i=0; i<8; ++i){
                        for (int j=0; j<2; ++j){
                            posi = Naleatorio.nextInt(cont);
                            Aleatorio[Posicao[posi]] = i;
                            if (posi < cont){
                                for (int q=(posi+1); q<(cont); ++q){
                                    Posicao[q-1] = Posicao[q];
                                }
                            } cont--;
                        }
                    }
                }
                 Novo  = false;
            }
          
            for (int i=0; i<16; ++i){
              
                if (event.getSource() == Escolha[i]){
                                    
                    Escolha[i].setText(String.valueOf(Aleatorio[i]));
                    Escolha[i].setEnabled(false);
                    Escolha[i].setVisible(true);
                    NClick++;
                  
                    if (NClick == 1) PClick = i;
                        if (NClick == 2){
                            SClick = i;
                            if (Aleatorio[PClick] != Aleatorio[SClick]){                                                       
                                pontos-=2;
                                JOptionPane.showMessageDialog(Memory.this, "Não correspondem"); 
                                Escolha[PClick].setText("");
                                Escolha[SClick].setText("");
                                Escolha[PClick].setEnabled(true);
                                Escolha[SClick].setEnabled(true);                             
                          
                            }  else {
                                Acertos++;
                                pontos+=10;
                        }
                        NClick = 0;
                    }
                }
            }
          
          
            if (Acertos == 8){
                Vitorias++;
                Acertos = 0;
                if (pontos > pontos_Anterior) MaiorPontuacao = pontos;
                    Fim = true;
            }
          
          
            if (pontos < 0) pontos = 0;
            Pontuacao.setText("Pontos: " + pontos);
                 
        }
    }
  
    /**
     *
     * @param args
     */
    public static void main(String [] args){
      
            new Memory();
      
        }   
}
