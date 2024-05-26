package com.example.demo.controllers;

import com.example.demo.models.Bodega;
import com.example.demo.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;

@RestController
public class BodegaController {

    @Autowired
    private Repository repositoryBodega;

    @RequestMapping("/Bodegas")
    public ResponseEntity<?> getBodegas() {
        List<Bodega> bodegas = repositoryBodega.findAll();
        if (bodegas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay bodegas disponibles");
        } else {
            return ResponseEntity.ok(bodegas);
        }
    }

    @PostMapping("/add")
    public String save(@RequestBody Bodega bodega) {
        repositoryBodega.save(bodega);
        return "Agregado";
    }

    @PutMapping("/editar/{nombre}")
    public String update(@PathVariable String nombre, @RequestBody Bodega bodega) {
        Optional<Bodega> optionalBodega = repositoryBodega.findById(nombre);
        if (optionalBodega.isPresent()) {
            Bodega updateBodega = optionalBodega.get();
            updateBodega.setNombre(bodega.getNombre());
            updateBodega.setHistoria(bodega.getHistoria());
            updateBodega.setDescripcion(bodega.getDescripcion());
            updateBodega.setCordenadas(bodega.getCordenadas());
            updateBodega.setPeriocidad(bodega.getPeriocidad());
            updateBodega.setUltimaActualizacion(bodega.getUltimaActualizacion());
            repositoryBodega.save(updateBodega);
            return "Editado Correctamente";
        } else {
            return "No se encontró ninguna Bodega con el nombre proporcionado";
        }
    }

    @DeleteMapping("/delete/{nombre}")
    public String delete(@PathVariable String nombre) {
        Bodega deleteBodega = repositoryBodega.findById(nombre).orElse(null);
        if (deleteBodega != null) {
            repositoryBodega.delete(deleteBodega);
            return "Eliminado";
        } else {
            return "No se encontró ninguna Bodega con el nombre proporcionado";
        }
    }
}
