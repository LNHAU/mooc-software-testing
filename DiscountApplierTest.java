package tudelft.discount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiscountApplierTest {
    @ParameterizedTest(name="{0} : ({1}) = {2}")
    @CsvSource(delimiter = ';', value = {"NullProductsList; null; null", "EmptyProductsList; []; []",
            "OneBusinessProductList; [Produit de test1 <BUSINESS> 34.71]; [Produit de test1 (BUSINESS) : 38.18]",
            "ManyProductsList; [Produit de test3 <BUSINESS> 04., Produit de test0 <OTHER> .99, Produit de test2 <HOME> 1586.10, Toto <> ];" +
                    "[Produit de test3 (BUSINESS) : 4.40, Produit de test0 (OTHER) : 0.99, Produit de test2 (HOME) : 1427.49, Toto () : 0.00]",
            "ManyProductsList; [Produit de test2 <HOME> +1586.10, Produit de test1 <BUSINESS> 34.71, Produit de test-3 <BUSINESS> -4, Produit de test4 <HOME> 0.00, Produit de test5 <HOME> -3.06, Produit de test6 <BUSINESS> 0, Produit de test0 <OTHER> 0.99];" +
                    "[Produit de test2 (HOME) : 1427.49, Produit de test1 (BUSINESS) : 38.18, Produit de test-3 (BUSINESS) : -4.40, Produit de test4 (HOME) : 0.00, Produit de test5 (HOME) : -2.75, Produit de test6 (BUSINESS) : 0.00, Produit de test0 (OTHER) : 0.99]"})
    public void testAlgorithm(String partition, String productsListStr, String expectedResultStr) {
        ProductDao dao = Mockito.mock(ProductDao.class);

        List<Product> results = null;
        if (!"null".equals(productsListStr)) {
            results = new ArrayList<Product>();
            if (productsListStr.length() > 2) {
                List<String> productsList = Arrays.asList(
                        Arrays.stream(productsListStr.substring(1, productsListStr.length() - 1)
                        .split(","))
                        .map(String::trim) // supprime l'/les espace(s) qui précède(nt) et/ou suive(nt) le délimitateur ','
                        .toArray(String[]::new));
                for (int i = 0; i < productsList.size(); i++) {
                    String productStr = productsList.get(i);
                    List<String> productAttributesList = Arrays.asList(
                            Arrays.stream(productStr
                                    .split("<|>"))
                                    .map(String::trim) // supprime l'/les espace(s) qui précède(nt) et/ou suive(nt) le délimitateur '<' ou '>'
                                    .toArray(String[]::new));
                    if ((productAttributesList != null) && (productAttributesList.size() > 0)) {
                        String name = productAttributesList.get(0);
                        String category = (productAttributesList.size() > 1 ? productAttributesList.get(1) : "");
                        double price = (productAttributesList.size() == 3 ? Double.parseDouble(productAttributesList.get(2)) : 0.);
                        Product p = new Product(name, price, category);
                        results.add(p);
                    }
                }
            }
        }

        Mockito.when(dao.all()).thenReturn(results);

        DiscountApplier da = new DiscountApplier(dao);
        da.setNewPrices();

        String resultStr = "null";
        if (results != null) {
            resultStr = "[";
            for(Product product : dao.all()) {
                if (!"[".equals(resultStr)) {
                    resultStr += ", ";
                }
                resultStr += String.format("%s (%s) : %.2f",
                                                product.getName(), product.getCategory(), product.getPrice());
            }
            resultStr += "]";
        }

        Assertions.assertEquals(expectedResultStr, resultStr);
    }

}
