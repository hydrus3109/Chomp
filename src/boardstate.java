public class boardstate {
    int a;
    int b;
    int c;
    boolean winning = true;
    boardstate(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public void win(){
        this.winning = true;
    }
    public void lose(){
        this.winning = false;
    }
    public void print(){
        System.out.println(a + " " + b + " " + c);
    }
}


