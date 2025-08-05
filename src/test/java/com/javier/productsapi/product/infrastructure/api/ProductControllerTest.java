package com.javier.productsapi.product.infrastructure.api;

import com.javier.productsapi.common.mediator.Mediator;
import com.javier.productsapi.product.application.querys.getAll.GetAllProductRequest;
import com.javier.productsapi.product.application.querys.getAll.GetAllProductResponse;
import com.javier.productsapi.product.domain.entity.Product;
import com.javier.productsapi.product.infrastructure.api.dto.ProductDto;
import com.javier.productsapi.product.infrastructure.api.mapper.ProductMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Test unitario para ProductController usando Mockito.
 *
 * Al final es ir recorriendo el código poco a poco e ir haciendo la función y lo que uno espera.
 *
 * <ol>
 *   <li><b>@ExtendWith(MockitoExtension.class)</b> - Activa Mockito</li>
 *   <li><b>@Mock</b> - Crea dependencias falsas</li>
 *   <li><b>@InjectMocks</b> - Crea objeto real con mocks inyectados</li>
 *   <li><b>when().thenReturn()</b> - Programa qué devuelven los mocks</li>
 *   <li><b>Llamar método real</b> - Ejecutar lo que queremos probar</li>
 *   <li><b>Assertions</b> - Verificar que funciona como esperamos</li>
 * </ol>
 *
 * Los mocks son "actores" que simulan las dependencias.
 * Tú les dices qué hacer y verificas que el objeto principal responda bien.
 *
 * @author Javier
 */



@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private Mediator mediator;// ponemos los mocks como objetos falsos y los injectamos en el controller con Mockito
    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductController productController;


    @Test
    public void getAllProducts() {

        GetAllProductResponse getAllProductResponse = new GetAllProductResponse(List.of(
                Product.builder().id(1L).build(),
                Product.builder().id(2L).build()
        ));

        ProductDto productDto = new ProductDto();
        productDto.setId(1L);

        when(mediator.dispatch(new GetAllProductRequest())).thenReturn(getAllProductResponse);
        when(productMapper.mapToProductDto(any(Product.class))).thenReturn(productDto);

        ResponseEntity<List<ProductDto>> response = productController.getAllProducts("5");

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());

        List<ProductDto> productDtos = response.getBody();
        Assertions.assertEquals(2, productDtos.size());

    }
}