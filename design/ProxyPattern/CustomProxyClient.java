package design.ProxyPattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理测试类
 */

public class CustomProxyClient {

    public static void main(String[] args) {
        // 不使用代理逻辑
        IGamePlayer gamePlayer = new GamePlayer("test");
        gamePlayer.login("test", "password");
        gamePlayer.killBoss();
        gamePlayer.upgrade();

        // 使用普通代理
        IGamePlayer proxy = new GamePlayerProxy(gamePlayer);
        proxy.login("proxy", "password");
        proxy.killBoss();
        proxy.upgrade();

        // 调整1， 调用者无须知道代理哪个对象,对真实对象的修改不会影响到调用者
        IGamePlayer proxy2 = new GamePlayerProxy("test");
        proxy2.login("proxy2", "password");
        proxy2.killBoss();
        proxy2.upgrade();

        // 强制代理
        IGamePlayer force = ForceGamePalyer.getInstence("force");
        force.login("force", "password");
        force.killBoss();
        force.upgrade();

        // 动态代理
        InvocationHandler handler = new DynamicGamePlayer(gamePlayer);
        ClassLoader pClass = gamePlayer.getClass().getClassLoader();
        IGamePlayer proxy3 = (IGamePlayer)Proxy.newProxyInstance(pClass, new Class[] { IGamePlayer.class }, handler);
        
        proxy3.login("dynamic", "password");
        proxy3.killBoss();
        proxy3.upgrade();
    }

    public static interface IGamePlayer {
        public void login(String userName, String password);

        public void killBoss();

        public void upgrade();
    }

    public static class GamePlayer implements IGamePlayer {

        private String name;

        public GamePlayer(String name) {
            this.name = name;
        }

        @Override
        public void login(String userName, String password) {
            this.name = userName;
            System.out.println("Game player : " + name + " login");
        }

        @Override
        public void killBoss() {
            System.out.println("Game player : " + name + " kill Boss");
        }

        @Override
        public void upgrade() {
            System.out.println("Game player : " + name + " upgrade");
        }

    }

    /**
     * 强制代理，没有按照参考书的代码 基本思路就是即使创建真实对象拿到的也是代理
     */
    public static class ForceGamePalyer extends GamePlayer {

        private ForceGamePalyer(String name) {
            super(name);
        }

        public static IGamePlayer getInstence(String name) {
            GamePlayerProxy proxy = new GamePlayerProxy(new ForceGamePalyer(name));
            return proxy;
        }
    }

    public static class GamePlayerProxy implements IGamePlayer {

        private IGamePlayer gamePlayer;

        public GamePlayerProxy(IGamePlayer player) {
            this.gamePlayer = player;
        }

        public GamePlayerProxy(String name) {
            this.gamePlayer = new GamePlayer(name);
        }

        @Override
        public void login(String userName, String password) {
            System.out.println("proxy call: ");
            gamePlayer.login(userName, password);
        }

        @Override
        public void killBoss() {
            System.out.println("proxy call: ");
            gamePlayer.killBoss();
        }

        @Override
        public void upgrade() {
            System.out.println("proxy call: ");
            gamePlayer.upgrade();
        }
    }

    /**
     * 动态代理
     */
    public static class DynamicGamePlayer implements InvocationHandler {

        private Object object;

        public DynamicGamePlayer(Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(method.getName().equalsIgnoreCase("login")){
                System.out.println("synamic login");
            }
            return method.invoke(object, args);
        }

    }

}
