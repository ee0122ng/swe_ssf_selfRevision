package iss.ssf.selfRevisionws02.controller;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import iss.ssf.selfRevisionws02.model.Input;

@Controller
@RequestMapping(path={"/number"})
public class NumberController {

    private Logger logger = Logger.getLogger(NumberController.class.getName());
    private Input input;

    @GetMapping(produces="text/html")
    public String getNumber(Model model) {

        input = new Input();

        model.addAttribute("input", input);

        return "number";
    }

    // Via GetMapping
    /*
    @GetMapping(path="/totalImage")
    public String getImage(@RequestParam("num") Integer value, Model model) {

        List<Integer> imageList = new ArrayList<Integer>();
        Random rand = new SecureRandom();

        for(int i=0; i < value; i++) {
            imageList.add(rand.nextInt(31));
        }

        // use logger to display out
        String clientInput = "Client input number: " + value;
        String image = "Random image list: " + imageList;

        logger.log(Level.INFO, clientInput);
        logger.log(Level.INFO, image);

        model.addAttribute("imageList", imageList);

        return "image";
    }
    */

    // Via PostMapping with MutliValueMap
    /*
         @PostMapping(path="/totalImage")
    public String getImage(@RequestBody MultiValueMap<String, String> input, Model model) {

        Integer value = Integer.valueOf(input.getFirst("num"));

        List<Integer> imageList = new ArrayList<Integer>();
        Random rand = new SecureRandom();

        for(int i=0; i < value; i++) {
            imageList.add(rand.nextInt(31));
        }

        // use logger to display out
        String clientInput = "Client input number: " + value;
        String image = "Random image list: " + imageList;

        logger.log(Level.INFO, clientInput);
        logger.log(Level.INFO, image);

        model.addAttribute("imageList", imageList);

        return "image";
    }
     */

     // Via PostMapping with ModelAttribute
    @PostMapping(path="/totalImage")
    public String getImage(@ModelAttribute("input") Input input, Model model) {

        Integer value = input.getNum();

        List<Integer> imageList = new ArrayList<Integer>();
        Random rand = new SecureRandom();

        for(int i=0; i < value; i++) {
            imageList.add(rand.nextInt(31));
        }

        // use logger to display out
        String clientInput = "Client input number: " + value;
        String image = "Random image list: " + imageList;

        logger.log(Level.INFO, clientInput);
        logger.log(Level.INFO, image);

        model.addAttribute("imageList", imageList);

        return "image";
    }


    
}
