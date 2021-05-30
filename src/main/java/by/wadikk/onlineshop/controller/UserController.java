package by.wadikk.onlineshop.controller;

import by.wadikk.onlineshop.entity.User;
import by.wadikk.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /*@GetMapping("/add")
    public String addProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("allBrands", productService.getAllBrands());
        model.addAttribute("allCategories", productService.getAllCategories());
        return "addProduct";
    }*/

    /*@PostMapping("/add")
    public String addAProductPost(@ModelAttribute("product") Product product, HttpServletRequest request) {
        Product newProduct = new Product();
        newProduct.setTitle(product.getTitle());
        newProduct.setStock(product.getStock());
        newProduct.setPrice(product.getPrice());
        newProduct.setPicture(product.getPicture());

        List<String> listOfCategories = Arrays.asList(request.getParameter("category").split("\\s*,\\s*"));
        if (!listOfCategories.isEmpty()) {
            Set<Category> catElements = new HashSet<>();
            for (String category : listOfCategories) {
                catElements.add(new Category(category, newProduct));
            }
            newProduct.setCategories(catElements);
        }*/

        /*List<String> listOfBrands = Arrays.asList(request.getParameter("brand").split("\\s*,\\s*"));
        if (!listOfBrands.isEmpty()) {
            Set<Brand> brandElements = new HashSet<>();
            for (String brand : listOfBrands) {
                brandElements.add(new Brand(brand, newProduct));
            }
            newProduct.setBrands(brandElements);
        }

        productService.saveProduct(newProduct);
        return "redirect:product-list";
    }*/

    @GetMapping("/user-list")
    public String userList(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "userList";
    }

    /*@GetMapping("/edit")
    public String editProduct(@RequestParam("id") Long id, Model model) {
        Product product = productService.findProductById(id);

        String preselectedBrands = "";
        for (Brand brand : product.getBrands()) {
            preselectedBrands += (brand.getName() + ",");
        }
        String preselectedCategories = "";
        for (Category category : product.getCategories()) {
            preselectedCategories += (category.getName() + ",");
        }
        model.addAttribute("product", product);
        model.addAttribute("preselectedBrands", preselectedBrands);
        model.addAttribute("preselectedCategories", preselectedCategories);
        model.addAttribute("allBrands", productService.getAllBrands());
        model.addAttribute("allCategories", productService.getAllCategories());
        return "editProduct";
    }*/

    /*@PostMapping("/edit")
    public String editProductPost(@ModelAttribute("article") Product product, HttpServletRequest request) {
        Product newProduct = new Product();
        newProduct.setTitle(product.getTitle());
        newProduct.setStock(product.getStock());
        newProduct.setPrice(product.getPrice());
        newProduct.setPicture(product.getPicture());

        List<String> listOfCategories = Arrays.asList(request.getParameter("category").split("\\s*,\\s*"));
        if (!listOfCategories.isEmpty()) {
            Set<Category> catElements = new HashSet<>();
            for (String category : listOfCategories) {
                catElements.add(new Category(category, newProduct));
            }
            newProduct.setCategories(catElements);
        }

        List<String> listOfBrands = Arrays.asList(request.getParameter("brand").split("\\s*,\\s*"));
        if (!listOfBrands.isEmpty()) {
            Set<Brand> brandElements = new HashSet<>();
            for (String brand : listOfBrands) {
                brandElements.add(new Brand(brand, newProduct));
            }
            newProduct.setBrands(brandElements);
        }

        newProduct.setId(product.getId());
        productService.saveProduct(newProduct);

        return "redirect:product-list";
    }*/

    @GetMapping("/delete")
    public String deleteUserById(@RequestParam("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:user-list";
    }
}
