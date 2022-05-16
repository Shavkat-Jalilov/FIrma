package com.example.firma.Firma.controller;

import com.example.firma.Firma.model.Firma;
import com.example.firma.Firma.servis.Firma_servise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Firma")
public class Firma_controller {
    @Autowired
    Firma_servise firma_servise;
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
    @PostMapping("create")
    public ResponseEntity<?> create(@Valid @RequestBody Firma firma){
        boolean kiritish = firma_servise.kiritish(firma);
        if (kiritish){
            return ResponseEntity.status(200).body("Joylandi");
        }
        return ResponseEntity.status(208).body("Bunday firma mavjud");
    }
    @GetMapping("/read")
    public ResponseEntity<?> read(){
        return ResponseEntity.status(200).body(firma_servise.read());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        boolean delete = firma_servise.delete(id);
        if (delete){
            return ResponseEntity.status(200).body("Ma`lumotlar o'chirildi");
        }
        return ResponseEntity.status(208).body("Ma`lumotlar o'chirilmadi");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,@Valid @RequestBody Firma firma ){
        boolean update = firma_servise.update(id, firma);
        if (update){
            return ResponseEntity.status(200).body("Ma`lumotlar taxrirlandi");
        }
        return ResponseEntity.status(208).body("Ma`lumotlar taxrirlanmadi");
    }
}
