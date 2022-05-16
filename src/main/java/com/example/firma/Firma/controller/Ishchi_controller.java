package com.example.firma.Firma.controller;

import com.example.firma.Firma.DTO.Firma_dto;
import com.example.firma.Firma.servis.Firma_servise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Ishchi")
public class Ishchi_controller {
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
    @PostMapping("/create")
    public ResponseEntity<?> creat(Firma_dto firma_dto){
        boolean ishchi = firma_servise.ishchiCreat(firma_dto);
        if (ishchi){
            return ResponseEntity.status(200).body("Ma'lumotlar joylandi");
        }
        return ResponseEntity.status(208).body("Ma`lumotlar joylanmadi");
    }
    @GetMapping("/read")
    public ResponseEntity<?> read(){
        return ResponseEntity.status(200).body(firma_servise.ishchiRead());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        boolean ishchidelete = firma_servise.ishchidelete(id);
        if (ishchidelete){
            return ResponseEntity.status(200).body("O`chirildi");
        }
        return ResponseEntity.status(400).body("Bunday ishchi mavjud emas");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?>  update(@PathVariable Integer id, @Valid @RequestBody Firma_dto firma_dto){
        return ResponseEntity.status(200).body(firma_servise.ishchiUpdate(id, firma_dto));
    }
}
