package com.ut.sn.Controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ut.sn.Modeles.Chambre;
import com.ut.sn.Repository.ChambreRepository;
import com.ut.sn.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api")
public class ChambreController {

    @Autowired
    ChambreRepository chambreRepository;

    @GetMapping("/chambres")
    public List<Chambre> getChambres() {
        return chambreRepository.findAll();
    }
    @GetMapping("/chambres/{id}")
    public ResponseEntity<Chambre> getChambreById(@PathVariable(value = "id") Integer ChambreId)
            throws ResourceNotFoundException {
        Chambre Chambre = chambreRepository.findById(ChambreId)
                .orElseThrow(() -> new ResourceNotFoundException("Chambre not found for this id :: " + ChambreId));
        return ResponseEntity.ok().body(Chambre);
    }

    @PostMapping("/chambres")
    public ResponseEntity<Chambre> create(@RequestBody Chambre chambre) {
        Chambre c = chambreRepository.save(chambre);
        return ResponseEntity.ok(c);
    }
    @DeleteMapping("/chambres/{id}")
    public HashMap<String, Boolean> deleteChambre(@PathVariable(value = "id") Integer ChambreId)
            throws ResourceNotFoundException {
        Chambre chambre = chambreRepository.findById(ChambreId)
                .orElseThrow(() -> new ResourceNotFoundException("Chambre not present for the id :: " + ChambreId));

        chambreRepository.delete(chambre);
        HashMap<String, Boolean> response = new HashMap<String , Boolean>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @PutMapping("/chambres/{id}")
    public ResponseEntity<Chambre>  updateChambre(@PathVariable(value = "id") Integer ChambreId,
                                                    @Validated @RequestBody Chambre ChambreDetails) throws    ResourceNotFoundException {
        Chambre chambre =  chambreRepository.findById(ChambreId)
                .orElseThrow(() -> new ResourceNotFoundException("Chambre not found for this id :: " + ChambreId));

        chambre.setPavillon(ChambreDetails.getPavillon());
        chambre.setNumero(ChambreDetails.getNumero());

        final Chambre updatedChambre = chambreRepository.save(chambre);
        return ResponseEntity.ok(updatedChambre);
    }
}
