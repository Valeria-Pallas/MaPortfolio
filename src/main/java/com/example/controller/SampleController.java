package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Models.Person;

@Controller
public class SampleController {
	
    private String personName = "AncienNom";

    @GetMapping("/helloWorld")
    @ResponseBody
    public String helloWorld() {
        return "Hello world!";
    }
    
    @GetMapping("/greet/{name}")
    @ResponseBody
    public String greet(@PathVariable String name) {
        return "Salut, " + name + "!";
    }
    
    @GetMapping("/sum")
    @ResponseBody
    public String sum(@RequestParam int a, @RequestParam int b) {
        int result = a + b;
        return a + " + " + b + " = " + result;
    }
    
    @GetMapping("/multiply/{x}/{y}")
    @ResponseBody
    public String multiply(@PathVariable int x, @PathVariable int y) {
        int result = x * y;
        return x + " * " + y + " = " + result;
    }
    
    @PostMapping("/helloWorld")
    @ResponseBody
    public String helloWorld(@RequestBody Person person) {
        return "Salut, " + person.getName() + "! Tu as " + person.getAge() + " ans.";
    }
    
    @PutMapping("/updateName")
    @ResponseBody
    public String updateName(@RequestParam String newName) {
        personName = newName;
        return "Le nom de la personne a été mis à jour. Nouveau nom : " + personName;
    }

    @GetMapping("/getPersonName")
    @ResponseBody
    public String getPersonName() {
        return "Le nom actuel de la personne est : " + personName;
    }
}
