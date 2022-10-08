package com.usc.crud.controller;

import com.usc.crud.model.Empleado;
import com.usc.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private  UserService service;


    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> filtrar(@PathVariable Empleado empleado){

        return ResponseEntity.ok(service.finByUser(empleado));

    }


    // create employee rest api
    @PostMapping("/guardar")
    public Empleado createEmployee(@RequestBody Empleado empleado) {
        return service.guardarUser(empleado);
    }

    @GetMapping("/consultarAll")
    public ResponseEntity<?> consultarByUser(){

        return ResponseEntity.ok(service.buscarTdoso()
        );
    }

    // update employee rest api



    // delete employee rest api
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
       String msj = service.eliminarUser(id);
        return ResponseEntity.ok(msj);
    }

}
