package rbekyarov.project.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rbekyarov.project.models.dto.BehaviorDTO;
import rbekyarov.project.models.dto.BehaviorEditDTO;
import rbekyarov.project.models.entity.Behavior;
import rbekyarov.project.repository.DogRepository;
import rbekyarov.project.service.BehaviorService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class BehaviorController extends BaseController {
    private final BehaviorService behaviorService;
    private final DogRepository dogRepository;
private final HttpSession session;

    public BehaviorController(BehaviorService behaviorService,
                              DogRepository dogRepository, HttpSession session) {
        this.behaviorService = behaviorService;
        this.dogRepository = dogRepository;

        this.session = session;
    }


    @GetMapping("/view/table/behaviorTable")
    public ModelAndView behaviorTable(ModelAndView modelAndView , @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
       if(checkValidSession()) {
           return super.redirect("/view/login");
       }
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);

        Page<Behavior> behaviors = behaviorService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        int totalPages = behaviors.getTotalPages();
        List<Integer> pageNumbers = null;
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("behaviors", behaviors);
        return super.view("/view/table/behaviorTable", "behaviors", behaviors, "pageNumbers", pageNumbers);
    }

    @GetMapping("/view/add/behaviorAdd")
    public ModelAndView behaviorAdd(ModelAndView modelAndView, BehaviorDTO behaviorDTO) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        modelAndView.addObject("behaviorDTO", behaviorDTO);


        return super.view("/view/add/behaviorAdd", "behaviorDTO", behaviorDTO);

    }

    @PostMapping("/view/add/behaviorAdd")
    public ModelAndView addBehavior(@Valid BehaviorDTO behaviorDTO ,BindingResult bindingResult,RedirectAttributes redirectAttributes ) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("behaviorDTO", behaviorDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.behaviorDTO", bindingResult);

            return super.view("/view/add/behaviorAdd");
        }
        behaviorService.addBehavior(behaviorDTO,session);
        return super.redirect("/view/table/behaviorTable");

    }

    @GetMapping("view/table/behavior/remove/{id}")
    public ModelAndView removeBehavior(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        boolean isUsed = false;
        List<Behavior> behaviors = dogRepository.listBehaviorUsed();
        for (Behavior b : behaviors) {
            if(Objects.equals(b.getId(), id)){
                redirectAttributes.addFlashAttribute("isUsed", true);
                isUsed =true;
            }
        }
        if(!isUsed){
            behaviorService.removeBehaviorById(id);
        }
        return super.redirect("/view/table/behaviorTable");
    }


    @GetMapping("/view/table/behavior/edit/{id}")
    public ModelAndView getBehaviorDetail(@PathVariable("id") Long id,
                                          ModelAndView modelAndView) throws ObjectNotFoundException {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        Behavior behaviorEditDTO =
                behaviorService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));

        modelAndView.addObject("behaviorEditDTO",behaviorEditDTO );

        return super.view("/view/edit/behaviorEdit","behaviorEditDTO",behaviorEditDTO);

    }

    @PostMapping("/view/table/behavior/edit/{id}")
    public ModelAndView editBehavior( @PathVariable("id") Long id ,
                                      @Valid BehaviorEditDTO behaviorEditDTO,
                                      BindingResult bindingResult,
                                      ModelAndView modelAndView ) {
        if(checkValidSession()) {
            return super.redirect("/view/login");
        }
        if (bindingResult.hasErrors()) {

            modelAndView.addObject("behaviorEditDTO", behaviorEditDTO);
            return super.view("/view/edit/behaviorEdit","behaviorEditDTO", behaviorEditDTO);

        }
        behaviorService.editBehavior(behaviorEditDTO.getName(), id, session);
        return super.redirect("/view/table/behaviorTable");
    }

    public boolean checkValidSession(){
        Object user = session.getAttribute("user");
        Object admin = session.getAttribute("admin");

        if(admin ==null && user==null){
         return   true;

        }
        return false;
    }
}
