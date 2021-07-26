package design.ProxyPattern;

/**
 * 代理模式(委托模式) ： 
 * provide a surrogate or placeholder for another object to control access to it  
 * 为其他对象提供一种代理以控制对这个对象的访问
 * 
 *  Subject  接口
 * 
 *  RealSubject  具体业务角色
 * 
 *  Proxy  代理
 * 
 *  普通代理 ：客户端只能访问代理角色不能访问真实角色  @CustomProxyClient.java
 *  强制代理 ：必须通过真实角色获取代理，否则不能访问
 *  动态代理 ：在代码运行时才知道要代理的对象
 * 
 */

public class Client{

    public interface Subject{
        public void request();
    }

    public class RealSubject implements Subject{
        @Override
        public void request() {
            System.out.println("Real Subject request");
        }
    }

    public class Proxy implements Subject{
        private Subject subject = null;

        public Proxy(){
            this.subject = new Proxy();
        }

        public Proxy(Subject subject){
            this.subject = subject;
        }

        @Override
        public void request() {
            System.out.println("Proxy request");
            this.defore();
            this.subject.request();
            this.after();
        }

        private void defore(){
            System.out.println("Proxy before");
        }

        private void after(){
            System.out.println("Proxy after");
        }

    }

}