package com.idovia.jus_de_bagarre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.idovia.jus_de_bagarre.error.WineError;
import com.idovia.jus_de_bagarre.model.wine.WineModel;
import com.idovia.jus_de_bagarre.service.*;

@Controller
public class WineController {

    @Autowired
    private WineService wineService;

    @GetMapping("/vins")
    public String displayWine (Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", auth.getName());
        model.addAttribute("rouges", wineService.findAllByCategory("Rouge"));
        model.addAttribute("blancs", wineService.findAllByCategory("Blanc"));
        model.addAttribute("roses", wineService.findAllByCategory("Rosé"));
        return "wine";
    }

    // ----------- Ajout -----------


    @GetMapping("/vin/ajouter")
    public String displayNewWine (Model model) {
        WineModel wine =  new WineModel();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        wine.setOwner(auth.getName());
        model.addAttribute("wine", wine);
        return "new_wine";
    }

    @PostMapping("/vin/ajouter")
    public String createWine (RedirectAttributes ra, @ModelAttribute WineModel wine, @RequestParam("image") MultipartFile file) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        wine.setOwner(auth.getName());

        // check if all field are filled 
        if (wine.isNotNull() && wine.isNotEmpty()) {

            // check if grade is < 0 and grade > 5)
            if (wine.isGradeCorrect()) {

                // check characteristic are ok
                if (wine.isCharacteristicsCorrect()) {

                    // check if picture is added
                    if (!file.isEmpty()) {

                        if (wineService.save(wine, file)) {
                            return "redirect:/vins";
                        }
                        return "redirect:/error";
                    }
                    return new WineError().init(ra, wine, "picture");
                }
                return new WineError().init(ra, wine, "characteristic");
            }
            return new WineError().init(ra, wine, "grade");
        }
        return new WineError().init(ra, wine, "empty");
    }
}
