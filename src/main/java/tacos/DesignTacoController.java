package tacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;


@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController
{

    @GetMapping
    public String showDesignForm(Model model)
    {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FlTO", "Flour Torilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTIEN),
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTIEN),
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.PROTIEN),
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
                );

        Ingredient.Type[] types = Ingredient.Type.values();
        for(Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("taco", new Taco());

        return "design";

    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type)
    {
        return ingredients.stream().filter(x->x.getType().equals(type)).collect(Collectors.toList());
    }
}
