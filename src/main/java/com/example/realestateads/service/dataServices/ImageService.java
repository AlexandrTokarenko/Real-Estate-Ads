package com.example.realestateads.service.dataServices;

import com.example.realestateads.entity.Image;
import com.example.realestateads.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public int save(Image image) {
        return imageRepository.save(image).getId();
    }

    public List<Image> findAllByRealtyIdOrderByImageOrder(Integer realtyId) {

        return imageRepository.findByRealty_IdOrderByImageOrderAsc(realtyId);
    }

    public List<String> findAllTitleByRealtyIdOrderByImageOrder(int realtyId) {

        return findAllByRealtyIdOrderByImageOrder(realtyId).stream().map(Image::getTitle).toList();
    }

    public Image findImageByRealtyIdAndImageOrder(int realtyId, int imageOrder) {

        return imageRepository.findImageByRealtyIdAndImageOrder(realtyId,1).orElse(null);
    }

    @Transactional
    public List<String> deleteByRealtyId(Integer realtyId) {

       // imageRepository.deleteByRealty_Id(realtyId);
        return imageRepository.deleteImageByRealty_Id(realtyId).stream().map(Image::getTitle).toList();
      //  return imageRepository.deleteByRealty()
    }

    public List<Image> findImageByRealtyIdAndImageOrder(List<Integer> realitiesId, int imageOrder) {

        return realitiesId.stream().map(x->findImageByRealtyIdAndImageOrder(x,imageOrder)).toList();
    }
}
