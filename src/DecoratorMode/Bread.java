package DecoratorMode;

/**
 * Created by zhangzexiang on 2016/9/8.
 *
 * 一个三明治不可缺少的元素：面包，也是被装饰的元素
 *
 * 它是一个具体的成分，因此实现父类的所有的抽象方法
 */
public class Bread extends Ingredient{

    private String description;
    public Bread(String desc){
        this.description = desc;
    }
    public String getDescription(){
        return description;
    }

    public double getCost(){
        return 2.48;
    }
}
