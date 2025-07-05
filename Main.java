import javax.swing.*;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        intervalo a = intervalo.string_to_interval("(3,10]");
        System.out.println(a.contem(6));
        intervalo b = intervalo.string_to_interval("[9,12]");
        System.out.println(a.intercepta(b));
        System.out.println(a.media());
        a.impr_int();
        b.impr_int();
        intervalo c = a.produto(b);
        c.impr_int();
        a.intersect(b);
        a.uniao(b);
        System.out.println(b.soma());
        a.delta(b);
        List<intervalo> intervalos = List.of(a,b);
        JFrame frame = new JFrame("Teste");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new plotter(intervalos));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}