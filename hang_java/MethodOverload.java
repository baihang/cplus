public class MethodOverload {

    private static class Humam{
        public void speak(){
            System.out.println("i am human");
        }
    }

    public static class Woman extends Humam{
        @Override
        public void speak() {
            System.out.println("i am women");
        }
    }

    public static class Man extends Humam{
        @Override
        public void speak() {
            System.out.println("i am Man");
        }
    }

    public void who(Humam human){
        System.out.println("who is human");
    }

    public void who(Woman human){
        System.out.println("who is woman");
    }

    public void who(Man human){
        System.out.println("who is man");
    }

    public static void main(String[] args) {
        Humam man = new Man();
        Humam women = new Woman();
        man.speak();
        women.speak();
        MethodOverload method = new MethodOverload();
        method.who(man);
        method.who((Woman)women);
    }
    
}
