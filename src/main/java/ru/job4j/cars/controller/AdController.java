package ru.job4j.cars.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.model.Ad;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.AdService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        addUserToModel(model, session);
        List<Ad> ads = adService.findAll();
        model.addAttribute("ads", ads);
        return "index";
    }

    @PostMapping("/add")
    public String add(HttpSession session, @ModelAttribute Ad ad, @RequestParam("file") MultipartFile file)
            throws IOException {
        ad.setPhoto(file.getBytes());
        ad.setCreated(LocalDateTime.now());
        ad.setUser((User) session.getAttribute("user"));
        adService.add(ad);
        return "redirect:/index";
    }

    @GetMapping("/photoAd/{id}")
    public ResponseEntity<Resource> downloadPhoto(@PathVariable("id") int id) {
        Ad ad = adService.getById(id);
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(ad.getPhoto().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(ad.getPhoto()));
    }

    @GetMapping("/edit/{id}")
    public String editForm(Model model, HttpSession session, @PathVariable("id") int id) {
        addUserToModel(model, session);
        Ad ad = adService.getById(id);
        model.addAttribute("ad", ad);
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Ad ad, HttpServletRequest req, HttpSession session,
                       @RequestParam("file") MultipartFile file) throws IOException {
        User user = (User) session.getAttribute("user");
        LocalDateTime created = LocalDateTime.parse(req.getParameter("created1"));
        boolean isSold = "on".equals(req.getParameter("isSold"));
        byte[] photo = file.isEmpty() ? adService.getById(ad.getId()).getPhoto() : file.getBytes();
        ad.setUser(user);
        ad.setCreated(created);
        ad.setSold(isSold);
        ad.setPhoto(photo);
        adService.update(ad);
        return String.format("redirect:/edit/%d", ad.getId());
    }

    private void addUserToModel(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("guest");
        }
        model.addAttribute("user", user);
    }
}
