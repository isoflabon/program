package com.example;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
 
import com.example.entity.TestData;
import com.example.repository.TestDataRepository;
 
@RestController
@EnableAutoConfiguration
public class App {
 
    @Autowired
    TestDataRepository repository;
 
    @RequestMapping("/")
    String home(){
        return "Hello World!!";
    }
 
    @RequestMapping("/getData")
    List<TestData> testData(Model model){
 
        // DBからデータ取得
        List<TestData> list = repository.findAll();
 
        return list;
    }
 
    @RequestMapping("/getDataForHtml")
    ModelAndView testData(ModelAndView mav){
 
        // DBからデータ取得
        List<TestData> list = repository.findAll();
 
        // テンプレート名指定
        mav.setViewName("testThymeleaf");
        // 動的項目設定
        mav.addObject("messages", list);
 
        return mav;
    }
 
    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }
}