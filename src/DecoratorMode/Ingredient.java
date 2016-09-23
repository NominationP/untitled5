package DecoratorMode;

/**
 * Created by zhangzexiang on 2016/9/8.
 *
 * 祖先：定义一个汉堡的描叙和价格
 */

public abstract class Ingredient {
    public abstract String getDescription();
    public abstract double getCost();
    public void printDescription(){
        System.out.println("name "+ this.getDescription());
        System.out.println("Price RMB  "+ this.getCost());

    }

}
