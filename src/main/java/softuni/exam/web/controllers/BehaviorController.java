package softuni.exam.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.models.dto.BehaviorDTO;
import softuni.exam.models.dto.BehaviorEditDTO;
import softuni.exam.models.entity.Behavior;
import softuni.exam.service.BehaviorService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BehaviorController extends BaseController {
    private final BehaviorService behaviorService;

    public BehaviorController(BehaviorService behaviorService) {
        this.behaviorService = behaviorService;
    }


    @GetMapping("/view/table/behaviorTable")
    public ModelAndView behaviorTable(ModelAndView modelAndView) {

        List<Behavior> behaviors = behaviorService.findAllBehaviorById();
        modelAndView.addObject("behaviors", behaviors);
        return super.view("/view/table/behaviorTable", "behaviors", behaviors);
    }

    @GetMapping("/view/add/behaviorAdd")
    public ModelAndView behaviorAdd(ModelAndView modelAndView) {
        BehaviorDTO behaviorDTO = new BehaviorDTO();

        modelAndView.addObject("behaviorDTO", behaviorDTO);


        return super.view("/view/add/behaviorAdd", "behaviorDTO", behaviorDTO);

    }

    @PostMapping("/view/add/behaviorAdd")
    public String addBehavior(@Valid BehaviorDTO behaviorDTO) {

        behaviorService.addBehaviors(behaviorDTO);

        return "redirect:/view/table/behaviorTable";
    }

    @GetMapping("view/table/behavior/remove/{id}")
    public String removeBehavior(@PathVariable Long id) {
        behaviorService.removeBehaviorById(id);

        return "redirect:/view/table/behaviorTable";
    }


    @GetMapping("view/table/behavior/edit/{id}")
    public ModelAndView getBehaviorDetail(@PathVariable("id") Long id,
                                          ModelAndView modelAndView) throws ObjectNotFoundException {

        var behaviorDto =
                behaviorService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));

        modelAndView.addObject("behaviorDTO", behaviorDto);

        return super.view("/view/edit/behaviorEdit", "behaviorDTO", behaviorDto);

    }

    @PostMapping("view/table/behavior/edit/{id}/edit")
    public String editBehavior(@PathVariable("id") Long id, BehaviorEditDTO behaviorEditDTO) throws ObjectNotFoundException {
        var behaviorDto =
                behaviorService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));
        behaviorService.editBehaviors(behaviorEditDTO.getName(), id);

        return "redirect:/view/table/behaviorTable";
    }
}
