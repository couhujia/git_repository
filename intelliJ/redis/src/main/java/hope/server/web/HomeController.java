package hope.server.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/4/14.
 */
@RestController
public class HomeController {

    protected static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public String Home() {
        return "wlecome come here!";
    }

    @RequestMapping("/err")
    public String Error() {
        return "I am so sorry. There is an error.\n please ensure you last action!";
    }

}
