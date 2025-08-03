package com.javier.productsapi.product.domain.port;

import com.javier.productsapi.product.domain.entity.Product;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para el repositorio de productos.
 * <p>
 * Define las operaciones básicas para acceder y modificar productos en la capa de dominio.
 * <ul>
 *   <li><b>upsert(Product product)</b>: inserta o actualiza un producto.</li>
 *   <li><b>findById(Long id)</b>: busca un producto por su ID, devuelve Optional por si no existe.</li>
 *   <li><b>findAll()</b>: obtiene la lista de todos los productos.</li>
 *   <li><b>deleteById(Long id)</b>: elimina un producto por su ID.</li>
 * </ul>
 * Esta interfaz permite desacoplar la lógica de acceso a datos de la lógica de negocio.
 */
public interface ProductRepository {

    void upsert(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    void deleteById(Long id);


}
