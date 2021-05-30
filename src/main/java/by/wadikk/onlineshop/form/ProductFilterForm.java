package by.wadikk.onlineshop.form;

import lombok.Data;

import java.util.List;

@Data
public class ProductFilterForm {

    private List<String> category;
    private List<String> brand;
    private Integer pricelow;
    private Integer pricehigh;
    private String sort;
    private Integer page;
    private String search;
}
