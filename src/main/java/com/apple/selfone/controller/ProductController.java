package com.apple.selfone.controller;

import com.apple.selfone.entity.Image;
import com.apple.selfone.entity.ImageContent;
import com.apple.selfone.entity.Product;
import com.apple.selfone.entity.User;
import com.apple.selfone.model.ResponseData;
import com.apple.selfone.model.ResultResponse;
import com.apple.selfone.repository.ImageRepository;
import com.apple.selfone.repository.ImgContentRepository;
import com.apple.selfone.repository.ProductRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImgContentRepository imgContentRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/list")
    public @ResponseBody
    ResponseData<List<Product>> getProducts() throws Exception {
        return new ResponseData<List<Product>>(true, null, productRepository.findAll());
    }

    @PostMapping("/save")
    @ResponseBody
    public ResultResponse saveImage(@RequestParam("attachment")MultipartFile file, @ModelAttribute Product product){
        ResultResponse response = new ResultResponse();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("JPG")){

        }else {
            response.setSuccess(false);
            response.setMessage("File is not image");
            return response;
        }

        try {
            Image image = new Image(file.getOriginalFilename(), file.getContentType(), file.getSize());
            imageRepository.save(image);
            imgContentRepository.save(new ImageContent(image, file.getBytes()));

            productRepository.save(new Product(
                    image,
                    product.getName(),
                    product.getDescription(),
                    product.getPrice()
            ));

            response.setMessage("Product successfully uploaded");
            response.setSuccess(true);
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage("Saving product failed");
            e.printStackTrace();
        }
        return response;
    }
}
