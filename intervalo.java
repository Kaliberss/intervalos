
public class intervalo {
    final int lo;
    final int hi;
    final char inf;
    final char sup;
    public intervalo (char inf, int lo, int hi, char sup){
        if (lo > hi){
            throw new IllegalArgumentException("Intervalo inválido");
        }
        if (inf != '[' && inf != '('){
            throw new IllegalArgumentException("O intervalo deve ser aberto ou fechado no primeiro ponto.");
        }
        if (sup != ']' && sup != ')'){
            throw new IllegalArgumentException("O intervalo deve ser aberto ou fechado no ultimo ponto");
        }
        this.lo = lo;
        this.hi = hi;
        this.inf = inf;
        this.sup = sup;
    }
    public boolean contem(int x){
        if (x > lo && x < hi){
            return true;
        }
        if (inf == '[' && sup == ']'){
            return (x >= lo && x <= hi);
        }
        if (inf == '(' && sup == ']'){
            return (x > lo && x <= hi);
        }
        if (inf == '[' && sup == ')'){
            return (x >= lo && x < hi);
        }
        return false;
    }

    public static intervalo string_to_interval(String expr){
        expr = expr.trim();
        char inf = expr.charAt(0);
        char sup = expr.charAt(expr.length() -1);
        if (inf != '[' && inf != '(' || sup != ']' && sup != ')'){
            throw new IllegalArgumentException("Delimitadores invalidos");
        }
        String numeros = expr.substring(1, expr.length() -1);
        String [] separados = numeros.split(",");
        if(separados.length != 2){
            throw new IllegalArgumentException("Tamanho invalido");
        }
        int lo = Integer.parseInt(separados[0].trim());
        int hi = Integer.parseInt(separados[1].trim());
        return new intervalo(inf,lo,hi,sup);
    }
    public float media(){
        int i;
        float cont = 0;
        if (this.inf == '[' && this.sup == ']'){
            for (i = this.lo; i <= this.hi; i++){
                cont += i;
            }
            return cont/((this.hi - this.lo) + 1) ;
        }
        if (this.inf == '[' && this.sup == ')'){
            for (i = this.lo; i < this.hi; i++){
                cont += i;
            }
            return cont/(this.hi - this.lo);
        }
        if (this.inf == '(' && this.sup == ']'){
            for (i = this.lo + 1; i <= this.hi; i++){
                cont += i;
            }
            return cont/(this.hi - this.lo);
        }
        //se não entrar em nenhum dos if's, o intervalo é da forma (a,b)//
        for (i = this.lo + 1; i < this.hi; i++){
            cont += i;
        }
        return cont/((this.hi - this.lo) - 1);
    }
    public boolean intercepta(intervalo b) {
    int min = Math.min(this.lo,b.lo);
    int max = Math.max(this.hi,b.hi);
    for (int i = min; i <= max; i++)
        if (this.contem(i) && b.contem(i))
            return true;
    return false;
    }
    public intervalo produto(intervalo b){
        int[] prods = {this.lo * b.lo, this.lo * b.hi, this.hi * b.lo, this.hi * b.hi};
        int min = prods[0];
        int max = prods[0];
        for (int i = 0; i < 4; i++){
            if (min > prods[i]){
                min = prods[i];
            }
            if (max < prods[i]){
                max = prods[i];
            }
        }
        return new intervalo('[',min,max,']');
    }
public void impr_int(){
        if (this.inf == '[' && this.sup == ']'){
            System.out.print('[');
            for (int i = this.lo; i <= this.hi; i++){
                System.out.print(i + " ");
            }
            System.out.println(']');
            return;
        }
        if (this.inf == '[' && this.sup == ')'){
            System.out.print('[');
            for (int i = this.lo; i < this.hi; i++){
                System.out.print(i + " ");
            }
            System.out.println(')');
            return;
        }
        if (this.inf == '(' && this.sup == ']'){
            System.out.print('(');
            for (int i = this.lo + 1; i <= this.hi; i++){
                System.out.print(i + " ");
            }
            System.out.println(']');
            return;
        }
        System.out.print('(');
        for (int i = this.lo+1; i < this.hi; i++){
            System.out.print(i + " ");
        }
        System.out.println(')');
}
public void intersect(intervalo b){
        int min = Math.min(this.lo,b.lo);
        int max = Math.max(this.hi,b.hi);
        System.out.print("{");
        for (int i = min; i <= max; i++){
            if (this.contem(i) && b.contem(i)){
                System.out.print(i + " ");
            }
        }
        System.out.println("}");
}
public void uniao(intervalo b){
        int min = Math.min(this.lo,b.lo);
        int max = Math.max(this.hi,b.hi);
        System.out.print("{");
        for (int i = min; i <= max; i++){
            if(this.contem(i) || b.contem(i)){
                System.out.print(i + " ");
            }
        }
        System.out.println("}");
}
public int soma(){
        int cont = 0;
        for(int i = this.lo; i <= this.hi; i++){
            cont += i;
        }
        if (this.inf == '(')
            cont = cont - this.lo;
        if (this.hi == ')')
            cont = cont - this.hi;
        return cont;
}
public void delta (intervalo b){
        int min = Math.min(this.lo,b.lo);
        int max = Math.max(this.hi,b.hi);
        System.out.print('{');
        for (int i = min; i <= max; i++){
            if (this.contem(i) && !b.contem(i) || !this.contem(i) && b.contem(i))
                System.out.print(i + " ");
        }
        System.out.println('}');
}
}
