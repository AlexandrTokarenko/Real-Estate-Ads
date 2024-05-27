package com.example.realestateads.controller;

import com.example.realestateads.entity.Realty;
import com.example.realestateads.model.Property;
import com.example.realestateads.service.BookmarkManager;
import com.example.realestateads.service.FileService;
import com.example.realestateads.service.dataServices.ImageService;
import com.example.realestateads.service.dataServices.RealtyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/nedvizhimost/information/realty")
@RequiredArgsConstructor
public class AdDetailController {

    private final RealtyService realtyService;
    private final BookmarkManager bookmarkManager;
    private final ImageService imageService;
    private final FileService fileService;

    @GetMapping("/{realtyId}")
    public String showInformationAboutRealty(Model model, @PathVariable Integer realtyId, HttpServletResponse response,
                                             HttpServletRequest request, Principal principal) {

        Realty realty;

        if (principal == null) {
            realty = realtyService.findByIdAndAdvertisementsOpenTrue(realtyId);
            if (realty == null) return "redirect:/";
        } else {
            String userName = principal.getName();
            realty = realtyService.findById(realtyId);
            if (!realty.getAdvertisement().getUserEmail().getEmail().equals(userName)) {
                return "redirect:/";
            }
        }

        List<String> paths = fileService.getPathByFileName(
                imageService.findAllTitleByRealtyIdOrderByImageOrder(realtyId));

        model.addAttribute("files", paths);
        model.addAttribute("bookmark", bookmarkManager.isBookmarkInCookie(realtyId,request,response));
        model.addAttribute("realty",realty);

        if (realty.getProperty().getTitle().equals(Property.FLAT.getTitle())) {
            return "/information/flat";
        } else {
            return "/information/house";
        }
    }
}
