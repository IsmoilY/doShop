package com.apple.selfone.controller;

import com.apple.selfone.entity.Image;
import com.apple.selfone.entity.ImageContent;
import com.apple.selfone.entity.Product;
import com.apple.selfone.entity.User;
import com.apple.selfone.model.ResponseData;
import com.apple.selfone.model.ResultResponse;
import com.apple.selfone.model.UserData;
import com.apple.selfone.repository.ImageRepository;
import com.apple.selfone.repository.ImgContentRepository;
import org.hibernate.service.UnknownServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ImgContentRepository imgContentRepository;

    @PostMapping("/save")
    @ResponseBody
    public ResultResponse saveImage(@RequestParam("attachment")MultipartFile file, @ModelAttribute Product product){
        ResultResponse response = new ResultResponse();

        System.out.println("Product=========================" + product.getName());

        try {
            Image image = new Image(file.getOriginalFilename(), file.getContentType(), file.getSize());
            imageRepository.save(image);
            imgContentRepository.save(new ImageContent(image, file.getBytes()));
            response.setMessage("Image successfully uploaded");
            response.setSuccess(true);
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage("Uploading image failde");
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable UUID id){
        Image image = imageRepository.getOne(id);
        ImageContent content = imgContentRepository.findByImageId(image).orElseThrow(()-> new UsernameNotFoundException("user"));
        return ResponseEntity.ok().header("Content-type", image.getContentType()).body(content.getContent());
    }

    @PostMapping("/list")
    @ResponseBody
    public ResponseData<List<Image>> getUsers(){
        List<Image> list = imageRepository.findAll();
        return new ResponseData<List<Image>>(true, null, list);
    }

}
