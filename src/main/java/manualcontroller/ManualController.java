package manualcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ↓↓↓↓↓↓↓↓↓↓描述↓↓↓↓↓↓↓↓↓↓
 *
 * @author zhangxin.zhang created on 15-11-26.
 */

@Controller
@RequestMapping("/temp")
public class ManualController {

    @RequestMapping(value="/jar",method= RequestMethod.GET)
    public String helloGet(Model model){
        System.out.println("ManualControllerTest");
        return "temp";
    }
}

