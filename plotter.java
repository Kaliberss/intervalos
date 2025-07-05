import javax.swing.*;
import java.awt.*;
import java.awt.*;
import java.util.List;
public class plotter extends JPanel {
    private List <intervalo> Intervalos;
        public plotter (List <intervalo> Intervalos){
            this.Intervalos = Intervalos;
            setPreferredSize(new Dimension(1920,1080));
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            final int min = -50;
            final int max =  50;
            final int eixo = 400;
            final int escala = 20;
            int largura = (max - min) * escala;
            int margem = ((getWidth()- largura)/2) + 950;
            g.drawLine(margem + min*escala,eixo,margem + max*escala, eixo);
            g.setColor(Color.BLACK);
            for (int i = min; i <= max; i++){
                int x = margem + i *escala;
                g.drawLine(x,eixo-5,x,eixo+5);
                g.drawString(Integer.toString(i),x-5,eixo+20);
            }
            for (intervalo Intervalo:Intervalos){
                int x1 = margem + Intervalo.lo * escala;
                int x2 = margem + Intervalo.hi * escala;
                g.setColor(Color.RED);
                g.drawLine(x1,eixo,x2,eixo);
                if (Intervalo.inf == '[')
                    g.fillOval(x1-5,eixo-5,10,10);
                else
                    g.drawOval(x1-5,eixo-5,10,10);
                if (Intervalo.lo == ']')
                    g.fillOval(x2 - 5, eixo-5, 10, 10);
                else
                    g.drawOval(x2 -5, eixo-5,10,10);
            }
            }
        }

