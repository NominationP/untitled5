package DecoratorMode;

/**
 * Created by zhangzexiang on 2016/9/8.
 *
 * 具体的猪肉成分，同时也是一个具体的修饰器
 *
 */
public class Pork extends Decorator {

    public Pork(Ingredient igd){
        super(igd);
    }

    public String getDescription(){
        String base = ingredient.getDescription();
        return base+"\n"+"Decrocated with Pork !";
    }

    public double getCost(){
        double basePrice = ingredient.getCost();
        double porkPrice = 1.8;
        return basePrice+porkPrice;
    }



}
