package com.example.realestateads.controller;

import com.example.realestateads.dto.RealtyMAINTODO;
import com.example.realestateads.entity.Image;
import com.example.realestateads.service.*;
import com.example.realestateads.service.dataServices.ExchangeRateService;
import com.example.realestateads.service.dataServices.ImageService;
import com.example.realestateads.service.dataServices.RealtyService;
import com.example.realestateads.service.dataServices.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final RealtyService realtyService;
    private final UserService userService;
    private final ImageService imageService;
    private final FileService fileService;
    private final ExchangeRateService exchangeRateService;

    private final ConverterService converterService;
    private final CurrencyService currencyService;

    @GetMapping()
    public String index(Model model) {

        List<RealtyMAINTODO> realties = realtyService.findRealtyDTOByOpenTrue();

        List<Image> images = imageService.findImageByRealtyIdAndImageOrder(realties.stream().map(RealtyMAINTODO::getId).toList(), 1);

        for (int i = 0; i < realties.size(); i++) {
            realties.get(i).setFirstImage(fileService.getPathByFileName(images.get(i).getTitle()));
        }

        realties = currencyService.firstPriceExchangeRate(realties);

       // model.addAttribute("realties", realtyService.findAllOpen());
        model.addAttribute("realties", realties);
        model.addAttribute("key", false);
        return "main";
    }

    @GetMapping("/nedvizhimost/my-ads")
    public String showMyAds(Principal principal, Model model) {
        List<RealtyMAINTODO> realties = converterService.toRealtyMAINTODO(realtyService.findByUserEmailOrderById(principal.getName()));

        List<Image> images = imageService.findImageByRealtyIdAndImageOrder(realties.stream().map(RealtyMAINTODO::getId).toList(), 1);

        for (int i = 0; i < realties.size(); i++) {
            realties.get(i).setFirstImage(fileService.getPathByFileName(images.get(i).getTitle()));
        }

        realties = currencyService.firstPriceExchangeRate(realties);

        model.addAttribute("realties", realties);

        return "/user/my-ads";
    }
}
