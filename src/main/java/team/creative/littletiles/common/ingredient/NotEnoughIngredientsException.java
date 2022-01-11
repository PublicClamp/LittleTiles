package team.creative.littletiles.common.ingredient;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import team.creative.littletiles.common.action.LittleActionException;
import team.creative.littletiles.common.item.tooltip.ActionMessage;

public class NotEnoughIngredientsException extends LittleActionException {
    
    protected LittleIngredients ingredients;
    
    protected NotEnoughIngredientsException(String msg, LittleIngredient ingredient) {
        super(msg);
        this.ingredients = new LittleIngredients();
        this.ingredients.set(ingredient.getClass(), ingredient);
    }
    
    protected NotEnoughIngredientsException(String msg, LittleIngredients ingredients) {
        super(msg);
        this.ingredients = ingredients;
    }
    
    public NotEnoughIngredientsException(LittleIngredient ingredient) {
        this("exception.ingredient.missing", ingredient);
    }
    
    public NotEnoughIngredientsException(ItemStack stack) {
        this(new StackIngredient());
        ingredients.get(StackIngredient.class).add(new StackIngredientEntry(stack, stack.getCount()));
    }
    
    public NotEnoughIngredientsException(LittleIngredients ingredients) {
        super("exception.ingredient.missing");
        this.ingredients = ingredients;
    }
    
    public LittleIngredients getIngredients() {
        return ingredients;
    }
    
    @Override
    public List<Component> getActionMessage() {
        String message = getLocalizedMessage() + "\n";
        List objects = new ArrayList();
        for (LittleIngredient ingredient : ingredients)
            message += ingredient.print(objects);
        return new ActionMessage(message, objects.toArray());
    }
    
    public static class NotEnoughSpaceException extends NotEnoughIngredientsException {
        
        public NotEnoughSpaceException(LittleIngredient ingredient) {
            super("exception.ingredient.space", ingredient);
        }
        
        public NotEnoughSpaceException(LittleIngredients ingredients) {
            super("exception.ingredient.space", ingredients);
        }
        
        public NotEnoughSpaceException(ItemStack stack) {
            this(new StackIngredient());
            ingredients.get(StackIngredient.class).add(new StackIngredientEntry(stack, stack.getCount()));
        }
        
    }
}
