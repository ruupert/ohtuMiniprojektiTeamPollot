package tip.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tip.domain.Detail;
import tip.domain.Tip;
import tip.repository.DetailRepository;
import tip.repository.TagRepository;
import tip.repository.TipRepository;
import tip.service.validators.BlogpostValidator;

@Controller
public class BlogpostController {

    @Autowired
    private TipRepository tipRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    BlogpostValidator blogpostValidator;

    @GetMapping("/blogpost")
    public String addForm(Model model) {
        return "addBlogpost";
    }

    @PostMapping("/newBlogpost")
    public String addBlogpost(@RequestParam String name, @RequestParam String artist, @RequestParam String url,
            @RequestParam String date, RedirectAttributes attributes) {
        if (artist == null || artist.trim().isEmpty()) {
            artist = "tuntematon";
        }
        Tip tip = new Tip(name, "blogpost");
        Detail urlDetail = new Detail(url.trim());
        Detail artistDetail = new Detail(artist);
        Detail dateDetail = new Detail(date);
        
        tip.addDetail("url", urlDetail);
        tip.addDetail("artist", artistDetail);
        tip.addDetail("date", dateDetail);

        List<String> errors = blogpostValidator.validate(tip);
        if (errors.isEmpty()) {
            detailRepository.save(urlDetail);
            detailRepository.save(artistDetail);
            detailRepository.save(dateDetail);

            this.tipRepository.save(tip);
        } else {
            attributes.addFlashAttribute("errors", errors);
        }

        return "redirect:/";
    }

    @PostMapping("/blogpost/{tipId}")
    public String editBlogpost(Model model, @PathVariable Long tipId, @RequestParam String artist,
            @RequestParam int read, @RequestParam String name, @RequestParam String url, @RequestParam String date,
            RedirectAttributes attributes) {

        Tip tip = tipRepository.findOne(tipId);
        tip.setName(name);

        if (read == 1) {
            tip.setRead(true);
        } else {
            tip.setRead(false);
        }

        Detail url_type = tip.getDetails().get("url");
        url_type.setValue(url.trim());

        if (artist == null || artist.trim().isEmpty()) {
            artist = "tuntematon";
        }
        Detail artistDetail = tip.getDetails().get("artist");
        artistDetail.setValue(artist.trim());

        List<String> errors = blogpostValidator.validate(tip);
        if (errors.isEmpty()) {
            tipRepository.save(tip);
            attributes.addFlashAttribute("message", "tip has succesfully been modified olalala");
            return "redirect:/";
        }
        attributes.addFlashAttribute("errors", errors);
        return "redirect:/blogpost/" + tipId;

    }

}
