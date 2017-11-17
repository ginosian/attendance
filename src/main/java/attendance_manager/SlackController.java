package attendance_manager;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/")
public class SlackController {



    @RequestMapping(value = "/slack")
    public SlackModel connectSlack(){
        return new SlackModel();
    }
}
