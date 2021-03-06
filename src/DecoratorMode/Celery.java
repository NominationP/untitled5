package DecoratorMode;

/**
 * Created by zhangzexiang on 2016/9/8.
 */
public class Celery extends Decorator{

    public Celery(Ingredient igd){
        super(igd);
    }

    public String getDescription(){
        String base = ingredient.getDescription();
        return base + "\n" +"Decrocated with Celery !";
    }

    public double getCost(){
        double basePrice = ingredient.getCost();
        double celeryPrice = 0.6;
        return basePrice+celeryPrice;
    }
}
