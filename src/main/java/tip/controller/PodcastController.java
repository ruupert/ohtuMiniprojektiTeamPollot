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
import tip.service.validators.PodcastValidator;

@Controller
public class PodcastController {

    @Autowired
    private TipRepository tipRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    PodcastValidator podcastValidator;

    @GetMapping("/podcast")
    public String addForm(Model model) {
        return "addPodcast";
    }

    @PostMapping("/newPodcast")
    public String addPodcast(@RequestParam String name, @RequestParam String artist, @RequestParam String url,
            @RequestParam String date, RedirectAttributes attributes) {
        if (artist == null || artist.trim().isEmpty()) {
            artist = "tuntematon";
        }
        Tip tip = new Tip(name, "podcast");
        Detail urlDetail = new Detail(url.trim());
        Detail artistDetail = new Detail(artist);
        Detail dateDetail = new Detail(date);

        tip.addDetail("url", urlDetail);
        tip.addDetail("artist", artistDetail);
        tip.addDetail("date", dateDetail);

        List<String> errors = podcastValidator.validate(tip);
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

    @PostMapping("/podcast/{tipId}")
    public String editPodcast(Model model, @PathVariable Long tipId, @RequestParam String artist,
            @RequestParam String name, @RequestParam String url, @RequestParam String date,
            @RequestParam int read, RedirectAttributes attributes) {

        Tip tip = tipRepository.findOne(tipId);
        tip.setName(name);
        if (read == 1) {
            tip.setRead(true);
        } else {
            tip.setRead(false);
        }
        Detail isbnDetail = tip.getDetails().get("url");
        isbnDetail.setValue(url.trim());

        if (artist == null || artist.trim().isEmpty()) {
            artist = "tuntematon";
        }
        Detail artistDetail = tip.getDetails().get("artist");
        artistDetail.setValue(artist.trim());

        List<String> errors = podcastValidator.validate(tip);
        if (errors.isEmpty()) {
            tipRepository.save(tip);
            attributes.addFlashAttribute("message", "tip has succesfully been modified olalala");
            return "redirect:/";
        }
        attributes.addFlashAttribute("errors", errors);
        return "redirect:/podcast/" + tipId;

    }

}
