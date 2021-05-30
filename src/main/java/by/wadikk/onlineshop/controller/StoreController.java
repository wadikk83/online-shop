package by.wadikk.onlineshop.controller;


import by.wadikk.onlineshop.entity.Product;
import by.wadikk.onlineshop.form.ProductFilterForm;
import by.wadikk.onlineshop.service.ProductService;
import by.wadikk.onlineshop.type.SortFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.websocket.server.PathParam;


@Controller
public class StoreController {

    @Autowired
    ProductService productService;

    @GetMapping("/store")
    public String store(@ModelAttribute("filters") ProductFilterForm filters,
                        Model model,
                        @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        Integer page = filters.getPage();
        int pagenumber = (page == null || page <= 0) ? 0 : page - 1;
        SortFilter sortFilter = new SortFilter(filters.getSort());
        Page<Product> pageresult = productService.findProductByCriteria(PageRequest.of(pagenumber, 9, sortFilter.getSortType()),
                filters.getPricelow(), filters.getPricehigh(),
                filters.getCategory(), filters.getBrand(), filters.getSearch());


        model.addAttribute("allCategories", productService.getAllCategories());
        model.addAttribute("allBrands", productService.getAllBrands());
        model.addAttribute("products", pageresult.getContent());
        model.addAttribute("totalitems", pageresult.getTotalElements());
        model.addAttribute("itemsperpage", 9);
        return "store";
    }


    @GetMapping("/product-detail")
    public String productDetail(@PathParam("id") Long id, Model model) {
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("notEnoughStock", model.asMap().get("notEnoughStock"));
        model.addAttribute("addProductSuccess", model.asMap().get("addProductSuccess"));
        return "productDetail";
    }
}
