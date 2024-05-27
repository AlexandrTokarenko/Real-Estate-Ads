package com.example.realestateads.controller;

import com.example.realestateads.dto.FlatFilterDto;
import com.example.realestateads.dto.HouseFilterDto;
import com.example.realestateads.dto.RealtyMAINTODO;
import com.example.realestateads.entity.Image;
import com.example.realestateads.entity.Realty;
import com.example.realestateads.facade.RealtyFilterFacade;
import com.example.realestateads.model.Property;
import com.example.realestateads.service.CurrencyService;
import com.example.realestateads.service.FileService;
import com.example.realestateads.service.dataServices.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/nedvizhimost/filter")
@RequiredArgsConstructor
public class AdFilterController {

    private final RealtyService realtyService;
    private final RegionService regionService;
    private final LocalityService localityService;
    private final DistrictService districtService;
    private final ConstructionService constructionService;
    private final RepairService repairService;
    private final TypeOfHeatingService heatingService;
    private final RealtyFilterFacade realtyFilterFacade;
    private final ImageService imageService;
    private final FileService fileService;
    private final CurrencyService currencyService;

    @GetMapping()
    public String showPropertyWindow() {

        return "/filter/property";
    }

    @GetMapping("/remove")
    public String removeFilter() {

        return "redirect:/";
    }

    @GetMapping("/property")
    public String showFilterWindowByProperty(@RequestParam String property, Model model) {

        model.addAttribute("regions",regionService.findAllSorted());
        model.addAttribute("localities",localityService.findAllSorted());
        model.addAttribute("districts",districtService.findAllSorted());
        model.addAttribute("constructions", constructionService.findAllByPropertyTitle(property));
        model.addAttribute("repairs", repairService.findAll());
        model.addAttribute("heatings", heatingService.findAll());
        model.addAttribute("property",property);

        if (property.equals(Property.FLAT.getTitle())) {
            model.addAttribute("flatFilter",new FlatFilterDto());
            return "/filter/flat";
        }

        model.addAttribute("houseFilter",new HouseFilterDto());
        return "filter/house";
    }

    @PostMapping("/house/{property}")
    public String findRealtyByFilter(Model model,@PathVariable String property,
                                     @ModelAttribute HouseFilterDto houseFilter) {

        houseFilter.setProperty(property);

        List<Realty> realties = realtyFilterFacade.findByFilter(houseFilter);

        List<RealtyMAINTODO> realtyMAINTODO = realtyService.toRealtyMAINTODO(realties);

        List<Image> images = imageService.findImageByRealtyIdAndImageOrder(realtyMAINTODO.stream().map(RealtyMAINTODO::getId).toList(), 1);

        for (int i = 0; i < realtyMAINTODO.size(); i++) {
            realtyMAINTODO.get(i).setFirstImage(fileService.getPathByFileName(images.get(i).getTitle()));
        }

        realtyMAINTODO = currencyService.firstPriceExchangeRate(realtyMAINTODO);

        //model.addAttribute("realties",realties);
        model.addAttribute("realties",realtyMAINTODO);
        model.addAttribute("key",true);
        return "/main";
    }

    @PostMapping("/flat/{property}")
    public String findRealtyByFilter(Model model, @PathVariable String property,
                                     @ModelAttribute FlatFilterDto flatFilter) {
        System.out.println("flatFilterStart = " + flatFilter);
        flatFilter.setProperty(property);

        List<Realty> realties = realtyFilterFacade.findByFilter(flatFilter);

        List<RealtyMAINTODO> realtyMAINTODO = realtyService.toRealtyMAINTODO(realties);

        List<Image> images = imageService.findImageByRealtyIdAndImageOrder(realtyMAINTODO.stream().map(RealtyMAINTODO::getId).toList(), 1);

        for (int i = 0; i < realtyMAINTODO.size(); i++) {
            realtyMAINTODO.get(i).setFirstImage(fileService.getPathByFileName(images.get(i).getTitle()));
        }

        realtyMAINTODO = currencyService.firstPriceExchangeRate(realtyMAINTODO);
        model.addAttribute("realties",realtyMAINTODO);
        model.addAttribute("key",true);
        return "/main";
    }
}
