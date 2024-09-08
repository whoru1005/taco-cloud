package tacos.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;

import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
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

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, Model model){

        if (errors.hasErrors()) {
            // 유효성 오류가 있을 때 재료 목록을 다시 추가
            List<Ingredient> ingredients = Arrays.asList(
                    new Ingredient("FlTO", "Flour Tortilla", Ingredient.Type.WRAP),
                    new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                    new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                    new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                    new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
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

            return "design";  // 유효성 오류가 있으면 다시 디자인 페이지로 이동
        }

        //타코 디자인 저장
        log.info("Processing design: " + design);

        return "redirect:/orders/current";  // 유효성 검사를 통과하면 주문 페이지로 리다이렉트
    }


    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type)
    {
        return ingredients.stream().filter(x->x.getType().equals(type)).collect(Collectors.toList());
    }
}
