package com.gamershop.admin.product.service;

import com.gamershop.admin.category.service.CategoryService;
import com.gamershop.admin.exception.UserNotFoundException;
import com.gamershop.admin.product.interfaces.IProductService;
import com.gamershop.admin.product.mapper.ProductImageMapper;
import com.gamershop.admin.product.mapper.ProductParameterMapper;
import com.gamershop.admin.product.repo.ProductImageRepo;
import com.gamershop.admin.product.repo.ProductParameterRepo;
import com.gamershop.admin.product.repo.ProductRepo;
import com.gamershop.admin.product.mapper.ProductMapper;
import com.gamershop.shared.dto.ProductDTO;
import com.gamershop.shared.dto.ProductImageDTO;
import com.gamershop.shared.dto.ProductParameterDTO;
import com.gamershop.shared.entity.ProductEntity;
import com.gamershop.shared.entity.ProductImageEntity;
import com.gamershop.shared.entity.ProductParameterEntity;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    public static final int PRODUCTS_PER_PAGE = 10;

    private final CategoryService categoryService;

    private final ProductImageRepo productImageRepo;

    private final ProductParameterRepo productParameterRepo;

    private final ProductImageMapper productImageMapper;

    private final ProductParameterMapper productParameterMapper;


    public ProductService(ProductRepo productRepo,
                          ProductMapper productMapper,
                          CategoryService categoryService,
                          ProductImageRepo productImageRepo,
                          ProductParameterRepo productParameterRepo, ProductImageMapper productImageMapper, ProductParameterMapper productParameterMapper) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
        this.productImageRepo = productImageRepo;
        this.productParameterRepo = productParameterRepo;
        this.productImageMapper = productImageMapper;
        this.productParameterMapper = productParameterMapper;
    }

    @Override
    public List<ProductDTO> listProducts(){
        var productList = (List<ProductEntity>) productRepo.findAll();
        return productList.stream().map(productMapper::toDTO).toList();
    }

    public Page<ProductDTO> listByPage(int pageNum,
                                        String sortField,
                                        String sortDir,
                                        String keyword){
        Page<ProductEntity> productEntities;
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum -1, PRODUCTS_PER_PAGE, sort);
        if (keyword != null){
            keyword = "%" + keyword + "%";
            productEntities = productRepo.getProductEntitiesByProductNameLike(keyword, pageable);
        }
        else {
            productEntities = productRepo.findAll(pageable);
        }
        List<ProductDTO> products = productEntities.stream().map(productMapper::toDTO).toList();
        return new PageImpl<>(products, pageable, productEntities.getTotalElements());
    }

    public ProductDTO getProductById(Integer id){
        ProductEntity product = productRepo.findById(id)
                .orElseThrow(()-> new UserNotFoundException("Could not find any product with ID " + id));
        ProductDTO productDTO = productMapper.toDTO(product);
        List<ProductImageDTO> images = product.getProductImages().stream().map(productImageMapper::toDTO).toList();
        productDTO.setProductImages(images);
        List<ProductParameterDTO> parameters = product.getProductParameters().stream().map(productParameterMapper::toDTO).toList();
        productDTO.setProductParameters(parameters);
        return productDTO;
    }

    public void saveProduct(ProductDTO productDTO, MultipartFile[] files, String[] detailNames, String[] detailValues)
                            throws IOException, SQLException {
        ProductEntity product = productMapper.toProduct(productDTO);
        if (!productDTO.getProductCategory().isEmpty()){
            product.setProductCategory(categoryService.getOrCreateCategory(productDTO.getProductCategory()));
        }
        var savedProduct = productRepo.save(product);
        for (MultipartFile image : files){
            var resource = StreamUtils.copyToByteArray(image.getInputStream());
            productImageRepo.save(new ProductImageEntity(resource, savedProduct, image.getOriginalFilename()));
        }
        for(int i = 0; i < detailNames.length; i++){
            if(!detailNames[i].isEmpty() && !detailValues[i].isEmpty()) {
                productParameterRepo.save(new ProductParameterEntity(detailNames[i], detailValues[i], savedProduct));
            }
        }
    }



}
