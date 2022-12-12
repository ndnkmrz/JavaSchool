package com.gamershop.admin.product.interfaces;

import com.gamershop.shared.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IProductService {
    List<ProductDTO> listProducts();
    Page<ProductDTO> listByPage(int pageNum,
                                String sortField,
                                String sortDir,
                                String keyword);
    ProductDTO getProductById(Integer id);
    void saveProduct(ProductDTO productDTO, MultipartFile[] files, String[] detailNames, String[] detailValues) throws IOException, SQLException;
}
