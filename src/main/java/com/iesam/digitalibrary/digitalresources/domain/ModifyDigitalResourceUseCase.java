package com.iesam.digitalibrary.digitalresources.domain;

public class ModifyDigitalResourceUseCase {
    public  DigitalResourceRepository digitalResourceRepository;

    public ModifyDigitalResourceUseCase(DigitalResourceRepository digitalResourceRepository) {
        this.digitalResourceRepository = digitalResourceRepository;
    }

    public void execute(DigitalResource digitalResource) {
        digitalResourceRepository.modify(digitalResource);
    }

    // Obtener el tipo de recurso digital a partir del ID
    public static TypeDigitalResource getTypeFromId(String id) {


        char prefix = id.charAt(0);

        switch (prefix) {
            case 'E':
                return TypeDigitalResource.EBOOK;
            case 'M':
                return TypeDigitalResource.MOVIE;
            default:
                return null;// Devolver tipo desconocido si el prefijo es inválido
        }
    }

    // Cambiar el tipo de recurso digital de un ID existente
    public static String changeTypeOfId(String id, TypeDigitalResource newType) {
        if (id == null || id.isEmpty()) {
            return "InvalidID"; // Devolver un mensaje de error si el ID es nulo o vacío
        }

        // Obtener el prefijo del nuevo tipo
        String newPrefix = "";
        switch (newType) {
            case EBOOK:
                newPrefix = "E";
                break;
            case MOVIE:
                newPrefix = "M";
                break;
            default:
                return "InvalidType"; // Devolver un mensaje de error si el tipo es inválido
        }

        // Crear el nuevo ID con el nuevo prefijo
        return newPrefix + id.substring(1);
    }
}
