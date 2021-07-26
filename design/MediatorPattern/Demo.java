package design.MediatorPattern;

/**
 * 中介者模式的测试类
 */
public class Demo {
    
    //采购
    public static class Purchase{
        public void buy(int number){

        }
    }

    //库存
    public static class Stock{

        private static int number = 100;

        public void increase(int num){
            number += num;
            System.out.println("库存增加 " + num + " 总库存 = " + number);
        }

        public void decrease(int num){
            number += num;
            System.out.println("库存增加 " + num + " 总库存 = " + number);
        }

        public int getStock(){
            return number;
        }

        //库存压力大，需要通了销售清库存
        public void clearStock(){

        }

    }

    //销售
    public static class Sale{
        public void sell(int num){
            Stock stock = new Stock();
            Purchase purchase = new Purchase();

            if(stock.getStock() < num){
                //库存不够需要采购
                purchase.buy(num);
            }

            stock.decrease(num);
        }

        //打折销售
        public void offSell(){

        }
    }

}
