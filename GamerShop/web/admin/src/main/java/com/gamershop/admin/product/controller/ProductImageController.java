package com.gamershop.admin.product.controller;

import com.gamershop.admin.product.repo.ProductImageRepo;
import com.gamershop.shared.entity.ProductImageEntity;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ProductImageController {
    ProductImageRepo productImageRepo;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    public ProductImageController(ProductImageRepo productImageRepo) {
        this.productImageRepo = productImageRepo;
    }


    @PostMapping()
    Integer uploadImage(@RequestParam MultipartFile multipartFile) throws Exception {
        ProductImageEntity dbImage = new ProductImageEntity();
        dbImage.setProductImageLink(multipartFile.getBytes());
        return productImageRepo.save(dbImage).getProductImageId();
    }

    @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    Resource downloadImage(@PathVariable Integer imageId) {
        byte[] image = productImageRepo.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).getProductImageLink();
        return (Resource) new ByteArrayResource(image);
    }
}
