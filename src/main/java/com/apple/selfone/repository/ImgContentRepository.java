package com.apple.selfone.repository;

import com.apple.selfone.entity.Image;
import com.apple.selfone.entity.ImageContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ImgContentRepository extends JpaRepository<ImageContent, UUID> {
    Optional<ImageContent> findByImageId(Image image);
}
