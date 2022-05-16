package com.example.firma.Firma.servis;

import com.example.firma.Firma.DTO.Firma_dto;
import com.example.firma.Firma.model.Firma;
import com.example.firma.Firma.model.Ishchi;
import com.example.firma.Firma.repozitory.Firma_repozitory;
import com.example.firma.Firma.repozitory.Ishchi_repozitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Firma_servise {
    @Autowired
    Firma_repozitory firma_repozitory;
    @Autowired
    Ishchi_repozitory ishchi_repozitory;
    public boolean kiritish(Firma firma){
        boolean b = firma_repozitory.existsByNomiAndBoshqaruchiIsmi(firma.getNomi(), firma.getBoshqaruchiIsmi());
        if (b)
            return false;
        Firma firma1=new Firma();
        firma1.setNomi(firma.getNomi());
        firma1.setBoshqaruchiIsmi(firma.getBoshqaruchiIsmi());
        firma_repozitory.save(firma);
        return true;
    }
    public List<Firma> read(){
        List<Firma> firmaList=firma_repozitory.findAll();
        return firmaList;
    }
    public boolean delete(Integer id){
        Optional<Firma> byId = firma_repozitory.findById(id);
        if(byId.isPresent()){
            firma_repozitory.deleteById(id);
            return true;
        }
        return  false;
    }
    public boolean update(Integer id, Firma firma){
        boolean b = firma_repozitory.existsByNomiAndBoshqaruchiIsmi(firma.getNomi(), firma.getBoshqaruchiIsmi());
        if (b){
            return false;
        }
        Optional<Firma> byId = firma_repozitory.findById(id);
        if(!byId.isPresent()){
            return  false;
        }
        Firma firma1=new Firma();
        firma1.setNomi(firma.getNomi());
        firma1.setBoshqaruchiIsmi(firma.getBoshqaruchiIsmi());
        firma_repozitory.save(firma1);
        return true;
    }
    public boolean ishchiCreat(Firma_dto firma_dto){
        if(!ishchi_repozitory.existsByTelRaqam(firma_dto.getTel_raqam())){
            Optional<Firma> byId = firma_repozitory.findById(firma_dto.getFirmaId());
            if(!byId.isPresent()){
                return false;
            }
            Ishchi ishchi=new Ishchi();
            ishchi.setIsmi(firma_dto.getIsmi());
            ishchi.setLavoimi(firma_dto.getLavozimi());
            ishchi.setMaoshi(firma_dto.getMaoshi());
            ishchi.setManzili(firma_dto.getManzili());
            ishchi.setTelRaqam(firma_dto.getTel_raqam());
            ishchi.setFirma(byId.get());
            ishchi_repozitory.save(ishchi);
            return true;
        }
        return false;
    }
    public  List<Ishchi> ishchiRead(){
        List<Ishchi> all = ishchi_repozitory.findAll();
        return all;
    }
    public boolean ishchidelete(Integer id){
        Optional<Ishchi> byId = ishchi_repozitory.findById(id);
        if (byId.isPresent()){
            ishchi_repozitory.deleteById(id);
            return true;
        }
        return false;
    }
    public String ishchiUpdate(Integer id, Firma_dto firma_dto){
        Optional<Ishchi> byId = ishchi_repozitory.findById(id);
        if (!byId.isPresent()){
           return  "Bunday ishchi mavjud emas";
        }
        if (!ishchi_repozitory.existsByTelRaqam(firma_dto.getTel_raqam())){
            Optional<Firma> byId1 = firma_repozitory.findById(id);
            if(!byId1.isPresent()){
                return "Bunday firma mavjud emas";
            }
            Ishchi ishchi=new Ishchi();
            ishchi.setIsmi(firma_dto.getIsmi());
            ishchi.setLavoimi(firma_dto.getLavozimi());
            ishchi.setManzili(firma_dto.getManzili());
            ishchi.setMaoshi(firma_dto.getMaoshi());
            ishchi.setTelRaqam(firma_dto.getTel_raqam());
            ishchi.setFirma(byId1.get());
            ishchi_repozitory.save(ishchi);
            return "Yangilandi";
        }
        return "Bunday telefon raqam mavjud";
    }
}
