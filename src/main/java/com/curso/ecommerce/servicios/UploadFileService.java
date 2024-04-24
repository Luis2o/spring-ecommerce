package com.curso.ecommerce.servicios;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFileService {

    private String folder = "img//";//ubicacion de las img

    public String saveImg(MultipartFile file) {// guardar imagen
        if (!file.isEmpty()) {//validar si trae la img debido a que sera un null
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(folder + file.getOriginalFilename());
                Files.write(path, bytes);
                return file.getOriginalFilename();
            } catch (IOException e) {
                     e.printStackTrace();
            }
        }//en caso no se quiere agregar una img desde el frame se carga una img por defaul
        return "default.jpg";
    }

    public void eliminarImg(String nombreImg) {//elimina la img del producto eliminado
        String url = "img//";
        File file = new File(url + nombreImg);
        file.delete();
    }
}
