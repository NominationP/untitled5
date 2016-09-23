package DecoratorMode;

/**
 * Created by zhangzexiang on 2016/9/8
 *
 * 所有其他成分的父类，（猪肉羊肉。。。）.
 * 也是一个实际不存在的类，仅仅表示一个概
 * 念，具有装饰功能的所有对象的父类
 *
 * 装饰器对象，所有具体装饰器对象父类。它最经典的特征就是：
 * 1.必须有一个它自己的父类为自己的成员变量；
 * 2.必须继承公共父类。这是因为装饰器也是一种成分，只不过是那些具体具有装饰功能的成分的公共抽象罢了。
 * 在我们的例子中就是有一个Ingredient作为其成员变量。Decorator继承了Ingredient类。
 */
public abstract class Decorator extends Ingredient{

    Ingredient ingredient;
    public Decorator(Ingredient igd){
        this.ingredient = igd;
    }

    public abstract String getDescription();
    public abstract double getCost();

}
