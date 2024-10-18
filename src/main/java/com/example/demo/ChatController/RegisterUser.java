package com.example.demo.ChatController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Service.RegisterService;

@RestController
public class RegisterUser {

    private final RegisterService dynamicTableService;
   
    public RegisterUser(RegisterService dynamicTableService) {
        this.dynamicTableService = dynamicTableService;
    }

    
    @GetMapping("/createNewUser")
    
    public String createTable() {
        dynamicTableService.createNewTable();
        return "Usuário criado com sucesso";
    }
    
    @GetMapping("arduino/getInfo")
    public String arduino() {
    
    	return "aqui";
    }
    
    

}

    
