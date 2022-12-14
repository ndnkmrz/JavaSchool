package com.gamershop.customer.product.interfaces;

import com.gamershop.shared.dto.ProductDTO;
import org.springframework.data.domain.Page;

public interface IProductService {
    Page<ProductDTO> listByCategory(int pageNum, Integer categoryId);
    ProductDTO getProductById(Integer productId);
    Page<ProductDTO> listByPage(int pageNum,
                                String keyword,
                                Integer categoryId);
}
