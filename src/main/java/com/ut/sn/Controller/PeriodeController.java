package com.ut.sn.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ut.sn.Modeles.Periode;
import com.ut.sn.Repository.PeriodeRepository;
import com.ut.sn.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PeriodeController {
    @Autowired
    PeriodeRepository periodeRepository;

    @GetMapping("/periodes")
    public List<Periode> getPeriode() {
        return periodeRepository.findAll();
    }
    @GetMapping("/periodes/{id}")
    public ResponseEntity<Periode> getPeriodeById(@PathVariable(value = "id") Integer PeriodeId)
            throws ResourceNotFoundException {
        Periode periode = periodeRepository.findById(PeriodeId)
                .orElseThrow(() -> new ResourceNotFoundException("Periode not found for this id :: " + PeriodeId));
        return ResponseEntity.ok().body(periode);
    }
    @PostMapping("/periodes")
    public ResponseEntity<Periode> create(@RequestBody Periode periode) {
        Periode p = periodeRepository.save(periode);
        return ResponseEntity.ok(p);
    }
    @DeleteMapping("/periodes/{id}")
    public HashMap<String, Boolean> deletePeriode(@PathVariable(value = "id") Integer PeriodeId)
            throws ResourceNotFoundException {
        Periode periode = periodeRepository.findById(PeriodeId)
                .orElseThrow(() -> new ResourceNotFoundException("Periode not present for the id :: " + PeriodeId));

        periodeRepository.delete(periode);
        HashMap<String, Boolean> response = new HashMap<String , Boolean>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @PutMapping("/periodes/{id}")
    public ResponseEntity<Periode>  updatePeriode(@PathVariable(value = "id") Integer PeriodeId,
                                                    @Validated @RequestBody Periode periodeDetails) throws ResourceNotFoundException {
        Periode periode =  periodeRepository.findById(PeriodeId)
                .orElseThrow(() -> new ResourceNotFoundException("Periode not found for this id :: " + PeriodeId));

          periode.setJour(periodeDetails.getJour());
          periode.setMois(periodeDetails.getMois());
          periode.setAnnee(periodeDetails.getAnnee());

        final Periode updatedPeriode = periodeRepository.save(periode);
        return ResponseEntity.ok(updatedPeriode);
    }
}
