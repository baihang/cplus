package design.MediatorPattern;

/**
 * 中介者模式
 * 
 *  Define an object that encapsulates how a set of objects interact.
 *  Mediator promotes loose coupling by keeping object from referring
 *  to each other explicitly, and it lets you vary their interaction independently.
 * 
 *  用一个中介对象封装一系列对象交互，中介者使对象不需要显示的相互作用，从而解耦。可以独立的改变他们之间的交互
 * 
 *  优势就是减少类之间的复杂依赖，将类与类之间的依赖转换为类与中介之间的依赖
 * 
 */

public class Client {
    
    /**
     * 中介者，协调其他对象
     */
    public static abstract class Mediator{
        protected RealColleague1 c1;
        protected RealColleague2 c2;

        public abstract void doSomething();
    }

    public static class RealMediator extends Mediator{

        @Override
        public void doSomething() {
            c1.selfMethod();
            c2.selfMethod();
        }

    }

    public static abstract class Colleague{
        protected Mediator mediator;

        public Colleague(Mediator mediator){
            this.mediator = mediator;
        }
    }

    public static class RealColleague1 extends Colleague{

        RealColleague1(Mediator mediator){
            super(mediator);
        }

        public void selfMethod(){
            System.out.println("self method");
        }

        public void depMethod(){
            System.out.println("需要依赖中介的方法");
            mediator.doSomething();
        }

    }

    public static class RealColleague2 extends Colleague{

        RealColleague2(Mediator mediator){
            super(mediator);
        }

        public void selfMethod(){
            System.out.println("self method");
        }

        public void depMethod(){
            System.out.println("需要依赖中介的方法");
            mediator.doSomething();
        }

    }

}
